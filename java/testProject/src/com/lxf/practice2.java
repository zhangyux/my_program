package com.lxf;
import java.util.Arrays;
/*
 * java练习二 
 * @author  liangxifeng
 * date 2016-03-14
 */
public class practice2 {
	double score1 = 90;
	static double score2=99;
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
	/*-------------------------------- Java 中的 static 使用之静态变量-----------------------------------------------------------------------*/
	/*
	 *  static 修饰的成员称为静态成员或类成员
	 *  它属于整个类所有，而不是某个对象所有,即被类的所有对象所共享
	 * 静态成员可以使用类名直接访问，也可以使用对象名进行访问
	 * 使用 static 可以修饰变量、方法和代码块
	 *  静态方法中可以直接调用同类中的静态成员，但不能直接调用非静态成员，
	 *  如果希望在静态方法中调用非静态变量，可以通过创建类的对象，然后通过对象来访问非静态变量
	 *  在普通成员方法中，则可以直接访问同类的非静态变量和静态变量
	 */
	//使用 static 声明静态变量
	static  String className = "java 开发一班";
	//使用 static 声明静态方法
	public  static   void print2()
	{
		/*
		practice2 test = new practice2();
		System.out.println("在静态方法中访问非静态方法："+test.score1);
		System.out.println("在静态方法中访问静态方法："+practice2.score2);
		*/
	}
	/*
	 *  在类的声明中，可以包含多个初始化块，当创建类的实例时，就会依次执行这些代码块。
	 *  静态初始化块只在类加载时执行，且只会执行一次，同时静态初始化块只能给静态变量赋值，
	 *  不能初始化普通的成员变量。
	 */
	{ //普通初始化块，
		score1 = 1222222;
		score2 = 133333;
		System.out.println(score1+score2);
	}
	static { //静态初始化块，该初始化在内存中的执行顺序要先于以上普通初始化块，只能给静态变量赋值
		score2 = 12333;
		System.out.println(score2);
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
		
		// 访问静态变量，输出班级名称
		System.out.println(practice2.className);
		//使用类名访问静态方法
		practice2.print2();
		// 使用变量名访问静态方法
		test.print2();
		
	}
}
