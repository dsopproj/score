<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3>init</h3>
	<a href="./init.jsp?reset=true">init.jsp?reset=true</a>
	<br />
	<a href="./init.jsp?init=true">init.jsp?init=true</a>

	<h3>account</h3>
	<a href="./account/balance/change?username=wolf&balance=10&oper=inc">api:account/balance/change?username=wolf&balance=10&oper=inc</a>
	<br />
	<a href="./account/score/change?username=wolf&score=10&oper=inc">api:account/score/change?username=wolf&score=10&oper=inc</a>
	<br />
	<a href="./account/user/value?username=wolf">api:account/user/value?username=wolf</a>

	<h3>products</h3>
	<a href="./products?username=wolf">api:products?username=wolf</a>
	<br />
	<a
		href="./product/publish?username=wolf&title=enter some short message&content=enter detile message&cash=100">api:product/publish?username=wolf&title=xxx&content=xxx.xxx&cash=100</a>

	<h3>advertisement</h3>
	<a href="./advertisement/config?username=wolf&interval=10&speed=10">api:advertisement/config?username=wolf&interval=10&speed=10</a>
	<br />
	<a href="./advertisement/config/value?username=wolf">api:advertisement/config/value?username=wolf</a>
	<br />
	<a href="./advertisements?username=wolf">api:advertisements?username=wolf</a>
	<br />
	<a
		href="./advertisement/publish?username=wolf&title=testimg&imagePath=e://tmp//test.jpg">api:advertisement/publish?username=wolf&title=testimg&imagePath=e://tmp//test.jpg</a>
	<br />

	<h3>order</h3>
	<a href="./ShoppingOrder/droporder?username=wolf&shoppingid=1">api:ShoppingOrder/droporder?username=wolf&shoppingid=1</a>
	<br />
	<a href="./ShoppingOrder/findorderbyuser?username=wolf">api:/ShoppingOrder/findorderbyuser?username=wolf</a>

</body>
</html>