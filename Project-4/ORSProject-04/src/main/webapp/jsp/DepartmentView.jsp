<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.DepartmentCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Add Department</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.DEPARTMENT_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.DepartmentBean"
			scope="request"></jsp:useBean>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>
				Department
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</h3>
				<h3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</h3>
			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Department Code<span style="color: red">*</span></th>
					<td align="center">
						<input type="text" name="departmentcode"
						placeholder="Enter Department Code"
						value="<%=DataUtility.getStringData(bean.getDepartmentcode())%>">
					</td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("departmentcode", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Department Name<span style="color: red">*</span></th>
					<td align="center">
						<input type="text" name="departmentname"
						placeholder="Enter Department Name"
						value="<%=DataUtility.getStringData(bean.getDepartmentname())%>">
					</td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("departmentname", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Head Name<span style="color: red">*</span></th>
					<td align="center">
						<input type="text" name="headname"
						placeholder="Enter Head Name"
						value="<%=DataUtility.getStringData(bean.getHeadname())%>">
					</td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("headname", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Department Status<span style="color: red">*</span></th>
					<td align="center">
						<select name="departmentstatus">
							<option value="">--Select--</option>
							<option value="Active"
								<%= "Active".equals(DataUtility.getStringData(bean.getDepartmentstatus())) ? "selected" : "" %>>
								Active</option>
							<option value="Inactive"
								<%= "Inactive".equals(DataUtility.getStringData(bean.getDepartmentstatus())) ? "selected" : "" %>>
								Inactive</option>
						</select>
					</td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("departmentstatus", request)%>
					</font></td>
				</tr>

				<tr>
					<th></th>
					<td></td>
				</tr>

				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2">
						<input type="submit" name="operation" value="<%=DepartmentCtl.OP_UPDATE%>"> 
						<input type="submit" name="operation" value="<%=DepartmentCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2">
						<input type="submit" name="operation" value="<%=DepartmentCtl.OP_SAVE%>"> 
						<input type="submit" name="operation" value="<%=DepartmentCtl.OP_RESET%>">
					</td>
					<%
						}
					%>
				</tr>
			</table>
		</div>
	</form>
	<%@include file="Footer.jsp"%>
</body>
</html>
