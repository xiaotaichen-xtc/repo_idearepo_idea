package com.lagou.web.servlet;

import com.lagou.utils.UUIDUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/uplaod2")
public class FileUploadServlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		//1.创建磁盘文件工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2.创建文件上传的核心类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//2.1设置上传文件的编码
		upload.setHeaderEncoding("utf-8");
		//2.2判断表单是否是文件上传表单
		boolean multipartContent = upload.isMultipartContent(req);
		//2.3是文件上传表单
		if(multipartContent){
			//3.解析request  获取表单集合
			List<FileItem> list = upload.parseRequest(req);
			if(list != null){
				//4.遍历集合 获取表单项
				for(FileItem item : list){
					//5.判断当前表单项 是不是普通表单项
					boolean formField = item.isFormField();
					if(formField){
						//普通表单项
						String fieldName = item.getFieldName();
						String string = item.getString();
						System.out.println(fieldName+"_"+string);
					}else {
						//文件上传项
						//获取文件名
						String name = item.getName();

						//拼接新的文件名 使用UUID保证不重复
						String s = UUIDUtils.getUUID()+"_"+ name;

						//获取输入流   从本地上传文件
						InputStream in = item.getInputStream();

						//3.拼接输出路径,将图片保存到 upload
						//String separator = File.separator+"uplaod/"+s;
						//3.1获取项目的运行目录
						String realPath = this.getServletContext().getRealPath("/");
						String webappsPath = realPath.substring(0, realPath.indexOf("lagou_edu_home_war"));



						//拼接
						FileOutputStream out = new FileOutputStream(webappsPath+"upload/"+s);

						//使用IOUtils完成 文件的copy
						IOUtils.copy(in,out);

						//关闭流
						out.close();
						in.close();

					}

				}
			}


		}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req,resp);
	}
}
