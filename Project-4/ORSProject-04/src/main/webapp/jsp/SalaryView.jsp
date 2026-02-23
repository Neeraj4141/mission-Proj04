<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.SalaryCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Add Salary</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.SALARY_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.SalaryBean"
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
				Salary
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
			<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
			<input type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th align="left">Salary Slip Code<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="salaryslipcode"
						placeholder="Enter Salary Slip Code"
						value="<%=DataUtility.getStringData(bean.getSalaryslipcode())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("salaryslipcode", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Employee Name<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="employeename"
						placeholder="Enter Employee Name"
						value="<%=DataUtility.getStringData(bean.getEmployeename())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("employeename", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Basic Salary<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="basicsalary"
						placeholder="Enter Basic Salary"
						value="<%=DataUtility.getStringData(bean.getBasicsalary())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("basicsalary", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Bonus<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="bonus"
						placeholder="Enter Bonus"
						value="<%=DataUtility.getStringData(bean.getBonus())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("bonus", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Salary Date<span style="color: red">*</span></th>
					<td align="center"><input type="date" name="salarydate"
						value="<%=DataUtility.getDateString(bean.getSalarydate())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("salarydate", request)%>
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
						<input type="submit" name="operation" value="<%=SalaryCtl.OP_UPDATE%>"> 
						<input type="submit" name="operation" value="<%=SalaryCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2">
						<input type="submit" name="operation" value="<%=SalaryCtl.OP_SAVE%>"> 
						<input type="submit" name="operation" value="<%=SalaryCtl.OP_RESET%>">
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