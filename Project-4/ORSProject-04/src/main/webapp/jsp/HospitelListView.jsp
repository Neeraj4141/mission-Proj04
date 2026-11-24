<%@page import="in.co.rays.proj4.controller.HospitelListCtl"%>

<%@page import="in.co.rays.proj4.controller.ORSView"%>

<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<%@page import="in.co.rays.proj4.util.DataUtility"%>

<%@page import="in.co.rays.proj4.bean.HospitelBean"%>

<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>

<html>

<head>

<title>Hospitel List</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>

<body>

	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.HospitelBean"
		scope="request"></jsp:useBean>

	<div align="center">

		<h1 style="color: navy;">Hospitel List</h1>

		<h3>

			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>

		</h3>

		<h3>

			<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>

		</h3>

		<form action="<%=ORSView.HOSPITEL_LIST_CTL%>" method="post">

			<%

				int pageNo = ServletUtility.getPageNo(request);

				int pageSize = ServletUtility.getPageSize(request);

				int index = ((pageNo - 1) * pageSize) + 1;

				int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List list = ServletUtility.getList(request);

				Iterator<HospitelBean> it = list.iterator();

			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>">

			<input type="hidden" name="pageSize" value="<%=pageSize%>">

			<table width="100%">

				<tr>

					<td align="center">

						<b>Hospitel Code :</b>

						<input type="text" name="hospitelcode"
							value="<%=ServletUtility.getParameter("hospitelcode", request)%>"
							placeholder="Enter Hospitel Code">

						&nbsp;&nbsp;

						<b>Hospitel Name :</b>

						<input type="text" name="hospitelname"
							value="<%=ServletUtility.getParameter("hospitelname", request)%>"
							placeholder="Enter Hospitel Name">

						&nbsp;&nbsp;

						<b>Contact Number :</b>

						<input type="text" name="contactnumber"
							value="<%=ServletUtility.getParameter("contactnumber", request)%>"
							placeholder="Enter Contact Number">

						&nbsp;&nbsp;

						<input type="submit" name="operation"
							value="<%=HospitelListCtl.OP_SEARCH%>">

						<input type="submit" name="operation"
							value="<%=HospitelListCtl.OP_RESET%>">

					</td>

				</tr>

			</table>

			<br>

			<table border="1" width="100%">

				<tr style="background-color: #e1e6f1e3;">

					<th width="5%"><input type="checkbox" id="selectall"></th>

					<th width="5%">S.No</th>

					<th width="25%">Hospitel Code</th>

					<th width="30%">Hospitel Name</th>

					<th width="25%">Contact Number</th>

					<th width="10%">Edit</th>

				</tr>

				<%

					while (it.hasNext()) {

						HospitelBean hbean = it.next();

				%>

				<tr>

					<td style="text-align: center;">

						<input type="checkbox" class="case" name="ids"
							value="<%=hbean.getId()%>">

					</td>

					<td align="center"><%=index++%></td>

					<td align="center"><%=hbean.getHospitelcode()%></td>

					<td align="center"><%=hbean.getHospitelname()%></td>

					<td align="center"><%=hbean.getContactnumber()%></td>

					<td align="center">

						<a href="<%=ORSView.HOSPITEL_CTL%>?id=<%=hbean.getId()%>">Edit</a>

					</td>

				</tr>

				<%

					}

				%>

			</table>

			<br>

			<table width="100%">

				<tr>

					<td width="25%">

						<input type="submit" name="operation"
							value="<%=HospitelListCtl.OP_PREVIOUS%>"
							<%=pageNo > 1 ? "" : "disabled"%>>

					</td>

					<td width="25%" align="center">

						<input type="submit" name="operation"
							value="<%=HospitelListCtl.OP_NEW%>">

					</td>

					<td width="25%" align="center">

						<input type="submit" name="operation"
							value="<%=HospitelListCtl.OP_DELETE%>">

					</td>

					<td width="25%" align="right">

						<input type="submit" name="operation"
							value="<%=HospitelListCtl.OP_NEXT%>"
							<%=nextListSize != 0 ? "" : "disabled"%>>

					</td>

				</tr>

			</table>

		</form>

	</div>

	<%@include file="Footer.jsp"%>

</body>

</html>