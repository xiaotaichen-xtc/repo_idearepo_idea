package com.lagou.testFastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	//可以通过name指定输出名称  ordinal指定输出的顺序   serialize该字段不进行序列号输出
	@JSONField(name = "USERNAME",ordinal = 1)
	private String userName;
	@JSONField(name = "AGE",ordinal = 2)
	private int age;
	@JSONField(serialize = false)
	private String birthday;
}
