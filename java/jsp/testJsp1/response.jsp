<%@ page language="java"  import="java.util.*, java.io.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- jsp内置对象response测试--%>
<%
	response.setContentType("text/html;charset=utf-8"); //设置响应MIMI类型
	out.println("<h1>response内置对象</h1>");
	out.println("<hr>");
	//out.flush(); //将缓冲区内容刷新到客户端, 这样内置对象out的输出信息就在输出流体对象信息之前输出了
	
	PrintWriter outer = response.getWriter(); //获取输出流对象, 
	//默认输出流体对象信息要在内置对象out输出之前输出
	outer.println("大家好,我是response对象输出流outer对象"); 
	
	//请求重定向
	response.sendRedirect("http://www.baidu.com");

%>