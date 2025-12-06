<%@page import="java.util.Map"%>
<%@page import="in.co.rays.proj4.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>
		<div align="center">
			<h1 style="color: darkblue">UserRegistration</h1>
			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
				<h3 align="center">
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
			</div>
			<table>
				<tr>
					<th>FirstName</th>
					<td><input type="text" name="firstName"
						value="<%=DataUtility.getString(bean.getFirstName())%>"
						placeholder="enter your firstname"></td>
				</tr>
				<tr>
					<th>LastName</th>
					<td><input type="text" name="lastName"
						value="<%=DataUtility.getString(bean.getLastName())%>" 
						placeholder="enter your lastname"></td>
				</tr>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login"
						value="<%=DataUtility.getString(bean.getLogin())%>"
						placeholder="enter your login"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password"
						value="<%=DataUtility.getString(bean.getPassword())%>"
						placeholder="enter your password"></td>
				</tr>
				<tr>
					<th>Confirm Password</th>
					<td><input type="password" name="confirmpassword"
						value="<%=DataUtility.getString(bean.getConfirmPassword())%>"
						placeholder="enter your password"></td>
				</tr>
				<tr>
					<th>DOB</th>
					<td><input type="date" name="dob"
						value="<%=DataUtility.getDateString(bean.getDob())%>"
						style="width: 98%"></td>
				</tr>

				<tr>
					<th>Gender</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("male", "male");
							map.put("female", "female");
						%><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map)%>
					</td>
				</tr>
				<tr>
					<th>MobileNo</th>
					<td><input type="text" name="mobileno"
						value="<%=DataUtility.getString(bean.getMobileno())%>"
						placeholder="enter your mobileno"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"
						placeholder="enter your mobileno"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>