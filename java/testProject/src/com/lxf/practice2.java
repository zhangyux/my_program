package com.lxf;
/*
 * java练习二 
 * @author  liangxifeng
 * date 2016-03-14
 */
public class practice2 {
	/*-------------------------------- java 方法的使用---------------------------------------------------*/
	// 访问修饰符　返回值类型　方法名（参数列表）｛方法体｝
	//方法返回值的类型，如果方法不返回任何值，则返回值类型指定为 void
	public void print()
	{
		System.out.println("Hello World");
	}
	public static void main(String[] args) {
		practice2 test = new practice2();
		test.print();
	}
}
