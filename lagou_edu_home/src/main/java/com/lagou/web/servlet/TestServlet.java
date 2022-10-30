package com.lagou.web.servlet;

import com.lagou.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//当前的servlet 对应的是课程管理模块
@WebServlet("/test")
public class TestServlet extends BaseServlet{

	//添加课程功能
	public void addCourse(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("新建课程");
	}
	//根据课程名查询课程
	public void findByName(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("根据课程名进行查询");
	}

	//根据课程状态查询课程
	public void findByStatus(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("根据课程状态查询课程");
	}
}
