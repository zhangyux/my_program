<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jsp内置对象 request测试</title>
</head>
<body>
	<%
		//request是jsp其中的一个内置对象，是 httpservletrequest类的实例，似于out
		//设置post提交过来的数据编码，get不起作用，
		//如果是get提交方式那么需要在tomcat的server.xml中设置<Connector  URIEncoding="UTF8"/>
		request.setCharacterEncoding("UTF-8");
	%>
	用户名：<%= request.getParameter("userName") %><br>
	密码：<%= request.getParameter("passWord") %><br>
	爱好：
	<%
		String[] hobbys = request.getParameterValues("hobby");
		if(hobbys!=null)
		{
				//打印输出过来的数组信息
				out.println(Arrays.toString(hobbys));
				//循环输出每一个爱好
				for(int i=0; i<hobbys.length; i++)
				{
					out.println(hobbys[i]+"&nbsp;&nbsp;");
				}	
		}
	%>
</body>
</html>