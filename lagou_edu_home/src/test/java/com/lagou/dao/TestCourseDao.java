package com.lagou.dao;

import com.lagou.dao.impl.CourseDaoimpl;
import com.lagou.pojo.Course;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TestCourseDao {

	CourseDao courseDao = new CourseDaoimpl();
	//测试课程查询方法

	@Test
	public void testFindCourseList() throws SQLException {
		List<Course> courseList = courseDao.findCourseList();
		for (int i = 0; i <= courseList.size()-1; i++) {
			System.out.println(courseList.get(i));

		}
	}

	//测试条件查询
	@Test
	public void testFindByCourseNameAndStatus(){
		List<Course> courseList = courseDao.findByCourseNameAndStatus("32","1");
		courseList.stream().forEach(x ->{
			System.out.println(x.getId()+""+x.getCourse_name() +""+x.getStatus());
		});
	}

	//新键课程信息
	@Test
	public void testsavecourseSalesInfo(){
		Course course = new Course();
		course.setCourse_name("成功学");
		course.setBrief("大师带你学习");
		int i = courseDao.savecourseSalesInfo(course);
		if(i != 0){
			System.out.println("数据插入成功："+i);
		}else {
			System.out.println("数据插入失败！！！！！！");
		}

	}

	//根据id查询信息
	@Test
	public void findCourseById(){
		List<Course> courseById = courseDao.findCourseById(1);
		courseById.stream().forEach(x ->{
			System.out.println("id查询的数据为:"+x.getId()+"_"+x.getCourse_name()+"_"+x.getBrief()+"_"+x.getStatus());
		});

	}

	//测试修改课程营销信息
	@Test
	public void updateCourseSalesInfo(){
		//1.执行信息的一个回显操作
		List<Course> courseById = courseDao.findCourseById(1);
		courseById.stream().forEach(x ->{
			System.out.println(x);

			//修改课程的营销信息
			x.setCourse_name("测试修改课程营销信息");
			x.setTeacher_name("肖老师");
			x.setDiscounts(88.8);

			int i = courseDao.updateCourseSalesInfo(x);
			System.out.println(i);
		});
	}

	//测试修改课程状态
	@Test
	public void TestupdateCourseStatus() {
		//1.执行信息的一个回显操作
		List<Course> courseById = courseDao.findCourseById(1);
		courseById.stream().forEach(x -> {
			System.out.println(x);

			//修改课程的营销信息
			x.setStatus(1);

			int i = courseDao.updateCourseStatus(x);
			System.out.println(i);
		});
	}
}
