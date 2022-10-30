package com.lagou.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
	//	doGet作为一个调度器
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取参数 要访问的方法名
		String methodName = req.getParameter("methodName");
		//2.判断执行对应的方法
		if(methodName != null){
			//通过反射，优化代码
			try {
				//1.获取字节码文件对象
				Class c = this.getClass();

				//2.根据传入的方法名获取对应的方法对象
				Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
				//3.调用method对象的invoke方法，执行对应的功能   当前对象和俩个方法参数  this = TestServlet
				method.invoke(this, req,resp);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("请求的功能不存在！！");
			}


		}



	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req,resp);
	}
}
