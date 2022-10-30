package com.lagou.web.servlet;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceimpl;
import com.lagou.utils.DateUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "courseServlet", value = "/course")
public class CourseServlet extends BaseServlet {


	//查询课程信息列表
	public void findCourseList(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		try {
			//1.接收参数


			//2.业务处理
			CourseService cs = new CourseServiceimpl();

			List<Course> courseList = cs.findCourseList();

			//3.响应结果  需要将查出来的对象转换为json格式   哪个对象,字段
			//SimplePropertyPreFilter 来指定转换的字段
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name","price","sort_num","status");
			String result = JSON.toJSONString(courseList,filter);
			resp.getWriter().println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	//根据条件查询课程信息
	public void findByCourseNameAndStatus(HttpServletRequest req, HttpServletResponse resp){
		try {
			//1.接收前台参数
			String course_name = req.getParameter("course_name");
			String status = req.getParameter("status");
			CourseService courseService = new CourseServiceimpl();
			List<Course> byCourseList = courseService.findByCourseNameAndStatus(course_name,status);

			//定义过滤器
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name","price","sort_num","status");
			//将对象转换为JSON格式
			String result = JSON.toJSONString(byCourseList,filter);
			//发送给前端
			resp.getWriter().println(result);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("值为空");
		}
	}

	//根据id查询信息
	public void findCourseById(HttpServletRequest req, HttpServletResponse resp){
		//1.接收参数
		String id = req.getParameter("id");

		//2.业务处理
		CourseService courseService = new CourseServiceimpl();
		List<Course> courseById = courseService.findCourseById(Integer.parseInt(id));

		//过滤器 指定输出的字段SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id",需要输出的字段);
		//然后把 filter传进JSON.toJSONString(x,filter);

		//3.返回结果
		courseById.stream().forEach(x ->{
			String result = JSON.toJSONString(x);
			try {
				resp.getWriter().println(result);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("显示失败");
			}

		});



	}

	//修改信息
	public void updateCourseSalesInfo(Course course){
		if(course !=null){
			CourseService courseService = new CourseServiceimpl();
			String s = courseService.updateCourseSalesInfo(course);
		}
	}

	//修改课程状态
	public void updateCourseStatus(HttpServletRequest req, HttpServletResponse resp){
			//1.获取参数
		String id = req.getParameter("id");
		//2.业务处理
		CourseService courseService = new CourseServiceimpl();
		//2.2先根据id查询出来
		List<Course> courseById = courseService.findCourseById(Integer.parseInt(id));
		courseById.stream().forEach(x ->{

				//判断课程信息状态，进行取反的设置
				if(x.getStatus() == 0){
					//如果是0就设置为1
					x.setStatus(1);
				}else {
					//如果是1就设置为0
					x.setStatus(0);
				}

				//设置跟新时间
				x.setUpdate_time(DateUtils.getDateFormart());

				//6.调用修改状态方法
				Map<String, Integer> map = courseService.updateCourseStatus(x);
				//7.响应结果 先转JSON格式
				String s = JSON.toJSONString(map);
			try {
				resp.getWriter().println(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});



	}

}
