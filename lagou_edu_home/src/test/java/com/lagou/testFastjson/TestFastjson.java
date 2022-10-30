package com.lagou.testFastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFastjson {
	@Test
	public void javaBeanToJSON(){
		//1.创建Person对象
		Person person = new Person("小斌",25, DateUtils.getDateFormart());

		//2.使用json对象转换为json数据
		String s = JSON.toJSONString(person);
		System.out.println(s);//{"age":25,"birthday":"2022-01-09 02:54:05","userName":"小斌"}
	}

	//2.java中的集合转集合
	@Test
	public void ListToJSON(){
		Person person1 = new Person("小斌",25, DateUtils.getDateFormart());
		Person person2 = new Person("小斌",25, DateUtils.getDateFormart());
		Person person3 = new Person("小斌",25, DateUtils.getDateFormart());

		//2.创建List集合
		List<Person> list = new ArrayList<>();
		//3.将对象封装到集合
		Collections.addAll(list,person1,person2,person3);

		//4.使用JSON对象 toJSONString（）方法
		String s = JSON.toJSONString(list);
		System.out.println(s);
	}

	//JSON转对象
	@Test
	public void JSONTojavaBean(){
		String json ="{\"age\":25,\"birthday\":\"2022-01-09 02:54:05\",\"userName\":\"小斌\"}";
		//需要抓换的变量，转成的对象类型
		Person person = JSON.parseObject(json, Person.class);
		System.out.println(person);
	}

	//JSON转list集合
	@Test
	public void JSONToList(){
		String json = "[{\"age\":25,\"birthday\":\"2022-01-09 02:54:05\",\"userName\":\"小斌\"},{\"age\":25,\"birthday\":\"2022-01-09 02:54:05\",\"userName\":\"小斌\"}]";
		List<Person> list = JSON.parseArray(json, Person.class);
		System.out.println(list);

	}
}
