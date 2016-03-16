package com.lxf;
import java.util.Arrays;
/*
 * java练习二 
 * @author  liangxifeng
 * date 2016-03-14
 */
public class practice2 {
	/*-------------------------------- java 方法的使用---------------------------------------------------*/
	// 访问修饰符　返回值类型　方法名（参数列表）｛方法体｝
	//方法返回值的类型，如果方法不返回任何值，则返回值类型指定为 void ( 也就是无参无返回值方法 )
	public void print()
	{
		System.out.println("Hello World");
	}
	//无参带返回值方法,返回值为整型
	public int getNum()
	{
		int a=5;
		int b=12;
		int sum = a+b;
		return sum; //有返回值必须用return, 如果方法的返回值是void 则不允许在方法内使用return
	}
	//有参数有返回值的方法, 调用带参方法时，必须保证实参的数量、类型、顺序与形参一一对应
	public void printArr(int[] scores)
	{
		//使用Arrays类toAstring方法将数组转换为字符串输出
		System.out.println(Arrays.toString(scores));
	}
	
	public static void main(String[] args) {
		practice2 test = new practice2();
		//调用test方法
		test.print();
		//调用getNum方法
		int num = test.getNum();
		System.out.println(num);
		//调用printArr方法
		int[]  scores = {84,91,74,62};
		test.printArr(scores); 
	}
}
