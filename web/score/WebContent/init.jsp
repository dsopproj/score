<%@page import="java.io.OutputStream"%>
<%@page import="org.me.services.SystemService"%>
<%@page import="org.me.mo.SystemInitialize"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>init system</title>
</head>
<body>

	<%
		//OutputStream out = response.getOutputStream();
		String reset = request.getParameter("reset");
		String init = request.getParameter("init");
		if ("true".equals(reset)) {
			boolean ret = SystemService.reset(SystemInitialize.pool);
			out.println("reset:" + ret);
			ret = SystemService.create(SystemInitialize.pool);
			out.println("create:" + ret);
		}
		if ("true".equals(init)) {
			boolean ret = SystemService.initTestData(SystemInitialize.pool);
			out.println("init:" + ret);
		}
	%>

</body>
</html>