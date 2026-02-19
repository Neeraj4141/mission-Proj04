<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.DepartmentListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.DepartmentBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Department List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.DepartmentBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Department
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.DEPARTMENT_LIST_CTL%>" method="post">
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List<DepartmentBean> list = (List<DepartmentBean>) ServletUtility.getList(request);
				Iterator<DepartmentBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Department Code : </b></label> <input
						type="text" name="departmentcode"
						value="<%=DataUtility.getStringData(bean.getDepartmentcode())%>">

						&nbsp;&nbsp; <label><b>Department Name : </b></label> <input
						type="text" name="departmentname"
						value="<%=DataUtility.getStringData(bean.getDepartmentname())%>">

						&nbsp;&nbsp; <label><b>Head Name : </b></label> <input type="text"
						name="headname"
						value="<%=DataUtility.getStringData(bean.getHeadname())%>">

						&nbsp;&nbsp; <input type="submit" name="operation"
						value="<%=DepartmentListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation"
						value="<%=DepartmentListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="20%">Department Code</th>
					<th width="20%">Department Name</th>
					<th width="20%">Head Name</th>
					<th width="20%">Department Status</th>
					<th width="5%">Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (DepartmentBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center;"><%=bean.getDepartmentcode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getDepartmentname()%></td>
					<td style="text-align: center;"><%=bean.getHeadname()%></td>
					<td style="text-align: center;"><%=bean.getDepartmentstatus()%></td>
					<td style="text-align: center;"><a
						href="DepartmentCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=DepartmentListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=DepartmentListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=DepartmentListCtl.OP_DELETE%>">
					</td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=DepartmentListCtl.OP_NEXT%>"
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
						value="<%=DepartmentListCtl.OP_BACK%>"></td>
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
