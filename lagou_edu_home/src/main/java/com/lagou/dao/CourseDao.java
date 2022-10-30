package com.lagou.dao;

import com.lagou.pojo.Course;

import java.sql.SQLException;
import java.util.List;

//课程模块 dao层接口
public interface CourseDao {
	//查询课程
	public List<Course> findCourseList() throws SQLException;

	//根据条件查询课程信息
	public List<Course> findByCourseNameAndStatus(String courseName, String status);

	//新键课程信息
	public int savecourseSalesInfo(Course course);

	//根据id查询信息(回显)
	public List<Course> findCourseById(int id);

	//修改课程营销信息
	public int updateCourseSalesInfo(Course course);

	//修改课程状态
	int updateCourseStatus(Course course);

}
