package com.lxf;
import java.util.Arrays; //导入数组操作类
/*
 * java练习一 基本语法变量，循环到数组的使用
 * @author  liangxifeng
 * date 2016-03-05
 */
public class HelloWorld{
	public static void main(String[] args){
		/*----------------------------------------------基本变量定义--------------------------------------*/
		//字符串变量的定义
		String love = "liangxifeng";
		//变量之间用+好连接
		love = love + " too";
		//字符型变量,　存储单个字符，声明用char,　2个字节空间
		char sex = '男';
		//整型变量用 int, ４个字节空间
		int num=20;
		//单精度浮点 float 4个字节空间,比如身高，
		float height=1.75f;
		//双精度浮点 double 8个字节空间, 比如价格／成绩分数平均分等
		double totalPrice=2000.22;
		//布尔　boolean
		boolean isOk=true;
		System.out.println("以上变量连接后: "+love+','+sex+','+num+','+height+','+totalPrice+','+isOk);
		
		/*----------------------------------------------自动类型转换--------------------------------------*/
		//整型转换为双精度浮点, 整型可以转换为浮点型，但是浮点型却不能转换为整形，因为浮点型占用空间比整型大
		int score1=88;
		double score2=score1;
		System.out.println(score2);
		
		/*----------------------------------------------强制类型转换--------------------------------------*/
		double avg1 = 75.8;
		//浮点型转换为整型
		int avg2 = (int)avg1;
		System.out.println(avg1);
		System.out.println(avg2);
		
		/*---------------------------------------------常量的定义使用--------------------------------------*/
		final String NAME = "liangxifeng";
		final double PI=3.14;
		System.out.println(NAME+PI);
		
		/*---------------------------------------------运算符---------------------------------------------------*/
		//１．算数运算符, 加＋，减－，乘＊，除／，求余％，自增++,  自减--
		int a=5;
		int b=a++; //b=5
		int c=++a; //c=7
		System.out.println("b="+b+",c="+c);
		//２．赋值运算符，c=a+b; c+=a => c=c+a; c-=a => c=c-a; c*=a => c=c*a; c/=a =>c=c/a; c%=a => c=c%a;
		/*３．比较运算符，两个数之间的比较
					> 、 < 、 >= 、 <= 只支持左右两边操作数是数值类型
					== 、 != 两边的操作数既可以是数值类型，也可以是引用类型
		*/
		int one=16;
		double two=9.5;
		String str1="hello";
		String str2="world";
		System.out.println("one等于two : "+(one == two)); // false
		System.out.println("one大于two : "+(one > two));   //true
		System.out.println("one<=two : "+(one <= two));    //false
		System.out.println("str1等于str2 : "+(str1 == str2));//false
		//４．罗辑运算符， 与：a&&b, 或a||b, 非：!a,异或：a^b (有且只能有一个人投票同意，才可以通过某议题);
		boolean b1=true;
		boolean b2=false;
		System.out.println(b1^b2); //true
		b2=true;
		System.out.println(b1^b2); //false
		b1=false; b2=false;
		System.out.println(b1^b2); //false
		//５． 条件运算符（ ? : ）也称为 “三元运算符”。
		String res = (8>5)  ?  "8>5" : "8<5";
		System.out.println(res);
		
		/*---------------------------------------------条件语句---------------------------------------------------*/
		int num1 = 3;
		if( num1 % 2 ==0 )
		{
			System.out.println("num1是偶数");
		}else
		{
			System.out.println("num1是奇数");
		}
		switch(num1){
			case 1:
				System.out.println("奖励笔记本一台");
				break;
			case 2: case 3:
				System.out.println("奖励移动电源一个");
				break;
			default:
				System.out.println("奖励U盘一个");
				break;
		}
		/*---------------------------------------------循环语句---------------------------------------------------*/
		while(num1<=1000)
		{
			System.out.println("我爱运动");
			num1+=500;
		}
		//do...while 与php语法一致
		for(int i = 1; i <=100; i++)
		{
			System.out.println(i);
			if( (i>2) &&( i %3 ==0))
			{
				break; //结束循环, continue 结束本次循环，break: 直接退出循环
			}
		}
		
		/*---------------------------------------------数组的定义---------------------------------------------------*/
		//声明数组　数据类型[] 数组名 或 数据类型　数组名[]
		int[] scores;   
		double heights[];
		String[] names;
		//分配空间
		scores = new int[5];
		heights = new double[5];
		names = new String[5];
		//也可以声明的同时，分配空间
		int[] scores2 = new  int[5];
		// 定义一个整型数组，并赋初值
		int[] nums = new int[] { 61, 23, 4, 74, 13, 148, 20 };
		
		//使用 Arrays 类操作 Java 中的数组
		int[] score3 = {78,29,12,90,34,88};
		//使用Arrays类的sort方法对数组排序
		Arrays.sort(score3);
		System.out.println("排序后数组中元素的值是：");
		for (int i = 0; i < score3.length; i++)
		{
				System.out.println(score3[i]);
		}
		
		//使用 foreach 操作数组
		String[] hobbys = {"跑步","健身","游泳","篮球","台球"};
		System.out.println("***使用for循环输出数组中的元素***");
		for(int i = 0; i < hobbys.length; i++)
		{
			System.out.println(hobbys[i]);
		}
		System.out.println("***使用foreach循环输出数组中的元素***");
		for( String  hobby : hobbys)
		{
			System.out.println(hobby);
		}
		
		//二维数组定义
		int[][] num2 = {{1,2,3},{4,5,6}};
	}
}
