<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<div align="center" style="margin-top: 50px"> -->
<!-- 		<h1> -->
<%-- 			<a href="<%=ORSView.WELCOME_CTL%>">Online Result System</a> --%>
<!-- 		</h1> -->
<!-- 	</div> -->
<!-- </body> -->
<!-- </html> -->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Online Result System</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: linear-gradient(135deg, #4a90e2, #9013fe);
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	color: white;
}

.container {
	text-align: center;
	background: rgba(255, 255, 255, 0.15);
	padding: 40px 60px;
	border-radius: 20px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
	backdrop-filter: blur(8px);
}

h1 {
	font-size: 40px;
	margin-bottom: 20px;
	letter-spacing: 1px;
}

a {
	text-decoration: none;
	color: #fff;
	padding: 12px 28px;
	background: #00000040;
	border-radius: 10px;
	font-size: 20px;
	transition: 0.3s;
}

a:hover {
	background: #ffffff80;
	color: #000;
}
</style>
</head>
<body>


	<div class="container">
		<h1>Online Result System</h1>
		<a href="<%=ORSView.WELCOME_CTL%>">Enter Portal</a>
	</div>


</body>
</html>