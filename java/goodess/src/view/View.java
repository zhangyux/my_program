package view;

import java.util.Scanner;

/**
 * view层测试
 */

public class View {
	
	private static final String CONTXT = "欢迎来到女神禁区: \n" + 
			"下面是女神禁区的功能列表：\n" + 
			"[MAIN/M]:主菜单" + 
			"[QUERY/Q]:查看女神信息\n" +
			"[GET/G]:查看单个女神信息\n" + 
			"[ADD/A]:添加女神信息\n" +
			"[UPDATE/U]:更新女神信息\n" + 
			"[DELETE/D]: 删除女神信息\n" + 
			"[EXIT/E]: 退出女神禁区\n" + 
			"[BREAK/B]: 退出当前功能，返回主菜单";
	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CONTXT);
		//键盘输入对象
		Scanner console = new Scanner(System.in);
		//如果键盘有输入值的情况
		while(console.hasNext())
		{
			String in = console.next().toString();
			System.out.println("您输入的信息是" + in);
			if(OPERATION_EXIT.equals(in.toUpperCase()) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase()))
			{
				System.out.println("您已成功退出" );
				break;
			}
		}
	}

}
