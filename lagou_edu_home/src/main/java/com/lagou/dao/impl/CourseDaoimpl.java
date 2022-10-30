package com.lagou.dao.impl;

import com.lagou.dao.CourseDao;
import com.lagou.pojo.Course;
import com.lagou.utils.DruidUtils;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//课程模块dao层的实现类
public class CourseDaoimpl implements CourseDao {


	@Override
	public List<Course> findCourseList()  {
		//1.创建QUeryRunner
		QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

		//2.编写sql 判断是否删除 取出is_del = 0 的未删除数据
		String sql = "SELECT id,course_name,price,sort_num,STATUS FROM course WHERE is_del =?";

		//3.执行查询  sql,封装的方式,参数就是代表传进？里面的值
		List<Course> courseList = null;
		try {
			courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);
			return courseList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	//根据条件查询课程信息
	@Override
	public List<Course> findByCourseNameAndStatus(String courseName, String status) {
		try {
			//1.创建QUeryRunner
			QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

			//2.1创建sql对象，将sql字符串添加进缓冲区
			StringBuilder sb = new StringBuilder("SELECT id,course_name,price,sort_num,STATUS FROM course WHERE 1=1 and is_del =?");

			//2.2创建list集合
			List<Object> list = new ArrayList<>();
			list.add(0);
			//2.3判断传入的参数是否为空
			if(courseName !=null && courseName !=""){
				sb.append(" AND course_name LIKE ?");
				//like查询 需要拼接%
				courseName = "%"+courseName+"%";
				//将传进来的条放进list
				list.add(courseName);

			}

			if(status != null && status !="" ){
				sb.append(" AND STATUS = ?");
				//将字符串转换为数字 int
				int i = Integer.parseInt(status);
				list.add(i);
			}

			//执行查询
			List<Course> courseList = queryRunner.query(sb.toString(), new BeanListHandler<Course>(Course.class), list.toArray());
			for (int i=0;i<=list.toArray().length-1;i++)
				System.out.println("list.toArray()的值为:"+list.toArray()[i]);
			return courseList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}



	}


	//新键课程信息
	@Override
	public int savecourseSalesInfo(Course course) {
		try {
			QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
			String sql = "INSERT INTO course(\n" +
					"course_name,\n" +
					"brief,\n" +
					"teacher_name,\n" +
					"teacher_info,\n" +
					"preview_first_field,\n" +
					"preview_second_field,\n" +
					"discounts,\n" +
					"price,\n" +
					"price_tag,\n" +
					"share_image_title,\n" +
					"share_title,\n" +
					"share_description,\n" +
					"course_description,\n" +
					"course_img_url,\n" +
					"STATUS,\n" +
					"create_time,\n" +
					"update_time\n" +
					") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//3.准备参数
			Object[] param = {course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
			course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),
			course.getPrice_tag(),course.getShare_image_title(),course.getShare_title(),course.getShare_description(),
			course.getCourse_description(),course.getCourse_img_url(),course.getStatus(),course.getCreate_time(),course.getUpdate_time()};

			//4.执行插入操作
			int update = queryRunner.update(sql, param);
			return update;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//根据id查询信息(回显)
	@Override
	public List<Course> findCourseById(int id){
		QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
		String sql = "SELECT * FROM course WHERE id=?";
		//3.执行查询  sql,封装的方式,参数
		List<Course> courseList = null;

		try {
			courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class),id);
			return courseList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//修改课程营销信息
	@Override
	public int updateCourseSalesInfo(Course course) {
		try {
			QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
			String sql = "UPDATE course SET \n" +
					"course_name = ?,\n" +
					"brief = ?,\n" +
					"teacher_name = ?,\n" +
					"teacher_info = ?,\n" +
					"preview_first_field = ?,\n" +
					"preview_second_field = ?,\n" +
					"discounts = ?,\n" +
					"price = ?,\n" +
					"price_tag = ?,\n" +
					"share_image_title = ?,\n" +
					"share_title = ?,\n" +
					"share_description = ?,\n" +
					"course_description = ?,\n" +
					"course_img_url = ?,\n" +
					"update_time = ? \n" +
					"WHERE id = ?";

			//3.准备参数
			Object[] param = {course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
					course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),
					course.getPrice_tag(),course.getShare_image_title(),course.getShare_title(),course.getShare_description(),course.getCourse_description(),
					course.getCourse_img_url(),course.getUpdate_time(),course.getId()};
			//3.执行查询  sql,封装的方式,参数
			int row = qr.update(sql, param);
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}



	}

	//修改课程状态
	@Override
	public int updateCourseStatus(Course course) {
		try {
			QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

			String sql ="UPDATE course SET STATUS =?, update_time=? WHERE id= ?";
			//准备参数   就是输入输出参数
			Object[] param = {course.getStatus(), course.getUpdate_time(), course.getId()};

			int  row = qr.update(sql, param);


			return  row;
		} catch (SQLException e) {
			e.printStackTrace();
			return  0;
		}
	}
}
