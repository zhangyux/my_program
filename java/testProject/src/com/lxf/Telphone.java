package com.lxf;
/*
 * 定义类练习
 */
public class Telphone {
	//定义属性，手机屏幕尺寸, 以下三个称为成员变量，如果不手动赋初值，java默认成员变量初值为０
	float screen;
	//cpu
	float cpu;
	//内存
	float mem;
	
	//定义方法
	public void showConfig()
	{
		int age; //在定义局部变量后，如果不赋值就时候java是不允许的
		System.out.println("我是手机，我的配置是:");
		System.out.println("屏幕大小："+screen+" CPU:"+cpu+"内存:"+mem);
	}
}
