package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDao;
import com.lagou.dao.impl.CourseDaoimpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//课程管理模块
public class CourseServiceimpl implements CourseService {
	//创建Coursedao
	CourseDao courseDao = new CourseDaoimpl();

	@Override
	public List<Course> findCourseList() throws SQLException {

		List<Course> courseList = courseDao.findCourseList();

		return courseList;
	}

	@Override
	public List<Course> findByCourseNameAndStatus(String courseName, String status) {
		List<Course> courseList = courseDao.findByCourseNameAndStatus(courseName,status);

		return courseList;
	}

	@Override
	public String savecourseSalesInfo(Course course) {
		//1.补全课程营销信息
		String strDate = DateUtils.getDateFormart();
		course.setCreate_time(strDate);
		course.setUpdate_time(strDate);
		course.setStatus(1);

		//2.执行插入操作
		int row = courseDao.savecourseSalesInfo(course);

		//3.判断
		if(row > 0){
			String result = StatusCode.SUCCESS.toString();
			return result;
		}else {
			String result = StatusCode.FAIL.toString();
			return result;
		}
	}

	@Override
	public List<Course> findCourseById(int id) {
		if(id != 0){
			List<Course> courseById = courseDao.findCourseById(id);
			return courseById;
		}else {
			return null;
		}
	}

	@Override
	public String updateCourseSalesInfo(Course course) {
		int i = courseDao.updateCourseSalesInfo(course);
		if(i ==1){
			String result = StatusCode.SUCCESS.toString();
			return result;
		}else {
			//
			String result = StatusCode.FAIL.toString();
			return result;
		}


	}

	//修改课程状态
	@Override
	public Map<String, Integer> updateCourseStatus(Course course) {
			//调用dao
		int row = courseDao.updateCourseStatus(course);
		Map<String,Integer> map =new HashMap<>();
		if(row > 0){
			if(course.getStatus() == 0){
					map.put("status",0);
			}else {
				map.put("status",1);
			}
		}
		return map;
	}
}
