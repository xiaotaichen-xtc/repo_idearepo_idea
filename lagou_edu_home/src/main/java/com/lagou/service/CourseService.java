package com.lagou.service;

import com.lagou.pojo.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//课程管理模块 service层
public interface CourseService {

	public List<Course> findCourseList() throws SQLException;

	public List<Course> findByCourseNameAndStatus(String courseName, String status);

	public String savecourseSalesInfo(Course course);

	//根据id查询信息(回显)
	public List<Course> findCourseById(int id);

	//修改课程营销信息
	public String updateCourseSalesInfo(Course course);

	public Map<String,Integer> updateCourseStatus(Course course);

}
