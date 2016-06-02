<%@ page language="java" import="java.io.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>my first</title>
</head>
<body>
<h1>Hello, This is my web First application!</h1>
<%-- jsp脚本注释,客户端不可见 --%>
<%!
	/*-----------------声明部分-------------------*/
	//声明一个字符串变量
	String s="张三";
	//声明了一个加法，返回整数的函数
	int add(int x, int y)
	{
		return x+y;
	}
	//输出html九九乘法表
	String print()
	{
		String s="";
		for(int i=1; i <= 9; i++)
		{
			for(int j=1; j <= i; j++)
			{
				s += i+"*"+j + "=" + (i*j) + "&nbsp;&nbsp;&nbsp:&nbsp;";
			}
			s+="<br />";
		}
		return s;
	}
	//利用jsp中的内置out对象直接输出
	void printNew(JspWriter out)
	{
		
	}
%>
<%--调用表达式 --%>
你好，<%= s %>
1+2 = <%= add(1,2)%>
<br />
<h2>九九乘法表</h2>
<%-- 使用表达式方式输出九九乘法表 --%>
<%= print() %>

<%-- 使用脚本输出九九乘法表 --%>

</body>
</html>