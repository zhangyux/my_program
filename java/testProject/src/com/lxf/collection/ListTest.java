package com.lxf.collection;
import java.util.List;            //引入list集合接口
import java.util.ArrayList;  //引入list集合接口的实现类

/**
 * 被选课程类
 * @author lxf
 *
 */
public class ListTest {
	//用于存放备选课程的list容器
	public List  courseToSelect;
	public ListTest()
	{
		this.courseToSelect = new ArrayList();
	}
	
	/**
	 * 用于往courseToSelect中添加备选课程
	 */
	public void testAdd()
	{
		//创建一个课程对象, 并通过调用add方法，添加到备选课程list中
		Course cr1 = new Course("1","数据结构");
		courseToSelect.add(cr1);
		Course temp = (Course) courseToSelect.get(0);
		System.out.println("添加了课程1：" + temp.id + " : " + temp.name);
		
		Course cr2 = new Course("2","C语言");
		courseToSelect.add(0, cr2); //在list集合的指定的0位置添加对象，此时原0位置的对象会被挤到1位置
		Course temp2 = (Course) courseToSelect.get(0);
		System.out.println("添加了课程2：" + temp2.id + " : " + temp2.name);
		
		Course cr3 = new Course("3","php语言");
		courseToSelect.add(2, cr3); //在list集合的指定的2位置添加对象
		Course temp3 = (Course) courseToSelect.get(2);
		System.out.println("添加了课程3：" + temp3.id + " : " + temp3.name);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListTest lt = new ListTest();
		lt.testAdd();
		
	}
}
