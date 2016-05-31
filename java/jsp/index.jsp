<%@ page language="java" contentType="text/html; charset=UTF-8"
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
%>
<%--调用表达式 --%>
你好，<%= s %>
1+2 = <%= add(1,2)%>
<%
	
	int res = add(1,2);
	//jsp脚本
	out.println("欢迎大家学习javaEE开发!");
	int test()
	{
		return 1;
	}
%>
</body>
</html>