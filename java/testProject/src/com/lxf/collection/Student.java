package com.lxf.collection;
import java.util.Set;             //引入set集合接口
import java.util.HashSet;   //引入set集合接口的实现类

/**
 * 学生类
 * @author lxf
 */

public class Student {
	public String id;
	public String name;
	public Set<Course> course;
	public Student(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.course = new HashSet<Course>(); //HashSet是Set接口的一个非常重要的实现类
	}
	
}
