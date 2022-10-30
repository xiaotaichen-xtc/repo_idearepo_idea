package com.lagou.testBeanUtils;

import com.lagou.pojo.Course;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TestBeanUtils {

	@Test
	public void tset01() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		//1.创建course对象
		Course course = new Course();

		//2.创建MAP
		Map<String,Object> map = new HashMap<>();

		//3.向map集合中添加数据，key要与course的属性名保持一致，value的数据类型与course的属性的类型保持一致  才能使用工具类
		map.put("id",1);
		map.put("course_name","大数据");
		map.put("brief","包含大数据流行的技术");
		map.put("teacher_name","周星星");
		map.put("teacher_info","非著名演员");

		//4.将map数据封装到course中，populate(对象,map);方法
		BeanUtils.populate(course,map);
		System.out.println(course.getId()+"_"+course.getCourse_name()+"_"+course.getBrief()+"_"+course.getTeacher_name()+"_"+course.getTeacher_info());

		//设置属性   哪个对象的哪个字段设置成什么
		BeanUtils.setProperty(course,"price",100.0);
		BeanUtils.getProperty(course,"price");
		System.out.println(course.getPrice());

	}
}
