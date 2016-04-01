package com.lxf.collection;
import java.util.Arrays;
import java.util.Iterator;
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
		
		//并通过调用add方法，想list的指定位置添加元素
		Course cr2 = new Course("2","C语言");
		courseToSelect.add(0, cr2); //在list集合的指定的0位置添加对象，此时原0位置的对象会被挤到1位置
		Course temp2 = (Course) courseToSelect.get(0);
		System.out.println("添加了课程2：" + temp2.id + " : " + temp2.name);
		
		Course cr3 = new Course("3","php语言");
		courseToSelect.add(2, cr3); //在list集合的指定的2位置添加对象
		Course temp3 = (Course) courseToSelect.get(2);
		System.out.println("添加了课程3：" + temp3.id + " : " + temp3.name);
		
		//用addAll方法，向list序列集合中以数组的形式添加两个元素
		Course[] course = {new Course("4","离散数学"), new Course("5", "汇编语言")};
		courseToSelect.addAll(Arrays.asList(course)); //将课程数组course转换为list，添加到courseToSelect备选课程容器中
		Course temp4 = (Course) courseToSelect.get(3);
		Course temp5 = (Course) courseToSelect.get(4);
		System.out.println("添加了两门课程：" + temp4.id + " : " + temp4.name + ";" + temp5.id + ":" + temp5.name);
		
		//用addAll方法，向list序列集合中添加指定位置元素, 代替以上刚刚添加 离散数学和汇编语言的位置，离散和汇编自动被挤到5,6位置
		Course[] course2 = {new Course("６","高等数学"), new Course("7", "大学英语")};
		courseToSelect.addAll(3, Arrays.asList(course2));
		Course temp6 = (Course) courseToSelect.get(3);
		Course temp7 = (Course) courseToSelect.get(4);
		System.out.println("添加了两门课程：" + temp6.id + " : " + temp6.name + ";" + temp7.id + ":" + temp7.name);
	}
	/**
	 * 获取List中所有元素
	 */
	public void testGet()
	{
		//获取list的长度
		int size = courseToSelect.size();
		System.out.println("有如下课程待选(通过list.size遍历访问)：");
		for(int i = 0; i < size; i++)
		{
			Course cr = (Course) courseToSelect.get(i);
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * 通过迭代器来遍历 List
	 */
	public void testIterator()
	{
		Iterator it = courseToSelect.iterator();
		System.out.println("有如下课程待选(通过迭代器访问)：");
		while(it.hasNext())
		{
			Course cr = (Course) it.next();
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * 通过for　each方法访问集合元素
	 */
	public void tesForEach()
	{
		System.out.println("有如下课程待选(通过foreach访问)：");
		for(Object obj: courseToSelect)
		{
			Course cr = (Course) obj;
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListTest lt = new ListTest();
		lt.testAdd();
		lt.testGet();
		lt.testIterator();
		lt.tesForEach();
	}
}
