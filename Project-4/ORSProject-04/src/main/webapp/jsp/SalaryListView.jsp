<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.SalaryListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.SalaryBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Salary List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.SalaryBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Salary
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.SALARY_LIST_CTL%>" method="post">
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List<SalaryBean> list = (List<SalaryBean>) ServletUtility.getList(request);
				Iterator<SalaryBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Salary Slip Code : </b></label> <input
						type="text" name="salaryslipcode"
						value="<%=DataUtility.getStringData(bean.getSalaryslipcode())%>">

						&nbsp;&nbsp; <label><b>Employee Name : </b></label> <input
						type="text" name="employeename"
						value="<%=DataUtility.getStringData(bean.getEmployeename())%>">

						&nbsp;&nbsp; <label><b>Basic Salary : </b></label> <input
						type="text" name="basicsalary"
						value="<%=DataUtility.getStringData(bean.getBasicsalary())%>">

						&nbsp;&nbsp; <label><b>Bonus : </b></label> <input type="text"
						name="bonus"
						value="<%=DataUtility.getStringData(bean.getBonus())%>">

						&nbsp;&nbsp; <input type="submit" name="operation"
						value="<%=SalaryListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=SalaryListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="15%">Slip Code</th>
					<th width="20%">Employee Name</th>
					<th width="15%">Basic Salary</th>
					<th width="10%">Bonus</th>
					<th width="15%">Salary Date</th>
					<th width="5%">Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (SalaryBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center;"><%=bean.getSalaryslipcode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getEmployeename()%></td>
					<td style="text-align: center;"><%=bean.getBasicsalary()%></td>
					<td style="text-align: center;"><%=bean.getBonus()%></td>
					<td style="text-align: center;"><%=DataUtility.getDateString(bean.getSalarydate())%></td>
					<td style="text-align: center;"><a
						href="SalaryCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=SalaryListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=SalaryListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=SalaryListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=SalaryListCtl.OP_NEXT%>"
						<%=nextListSize != 0 ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
				}
				if (list.size() == 0) {
			%>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=SalaryListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>