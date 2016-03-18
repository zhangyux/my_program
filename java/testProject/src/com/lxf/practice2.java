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
	public double calcAvg(double score1, double score2)
	{
		double res =  (score1+score2)/2;
		 return res;
	}
	
	/*
	 * java 中方法的重载
	 * １，必须是在同一个类中
	 * ２， 方法名相同
	 * ３，方法参数的个数、顺序或类型不同
	 * ４，与方法的修饰符或返回值没有关系　
	 *  ５，当调用被重载的方法时， Java 会根据参数的个数和类型来判断应该调用哪个重载方法，
	 *  　　参数完全匹配的方法将被执行。
	 */
	public void show()
	{
		System.out.println("无参的show方法");
	}
	public void show(String name)
	{
		System.out.println("带有一个字符串参数的show方法，参数值为"+name);
	}
	public String show(String name, int age)
	{
		System.out.println("带有两个字符串参数的show方法，参数值为"+name+age);
		return name+age;
	}
	/*
	 * 功能：创建指定长度的int型数组，并生成100以内随机数为数组中的每个元素赋值
	 * 定义一个带参带返回值的方法，通过参数传入数组的长度，返回赋值后的数组
	 */
	public int[] getArray(int length)
	{
		int [] nums = new int[length];
		for(int i = 0; i < nums.length; i++)
		{
			nums[i] = (int)(Math.random()*100); //100以内的随机数
		}
		return nums;
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
		double avgScore = test.calcAvg(94,81);
		System.out.println("成绩的平均分为"+avgScore);
		
		//重载调用
		test.show();
		test.show("liangxifeng");
		test.show("liangxifeng", 20);
		
		//练习
		// 调用方法并将返回值保存在变量中
		int nums[] = test.getArray(10);
		// 将数组转换为字符串并输出
		System.out.println(Arrays.toString(nums));
		
		//实例化Telphone类为对象, 实例化其他文件中的类，注意与php不同点，不需要include文件
		Telphone	 phone = new  Telphone();
		phone.screen = 15.2f;
		phone.cpu = 1.4f;
		phone.mem = 2.5f;
		phone.showConfig();
		
		//实例化Telphone类为对象, 使用构造方法实现初始化操作
		Telphone phone2 = new Telphone();
		//实例化Telphone类为对象, 使用构造方法实现初始化操作,　并传递参数，使用重载的有参构造方法
		Telphone phone3 = new Telphone(18.2f,1.5f, 2.8f);	
		
	}
}
