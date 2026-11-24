<%@page import="in.co.rays.proj4.controller.HospitelCtl"%>

<%@page import="in.co.rays.proj4.controller.ORSView"%>

<%@page import="in.co.rays.proj4.util.DataUtility"%>

<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>

<head>

<title>Add Hospitel</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>

<body>

	<form action="<%=ORSView.HOSPITEL_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.HospitelBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1 style="color: navy">

				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>

				Hospitel

			</h1>

			<h1>

				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>

				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>

			</h1>

			<!-- Hidden fields -->

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<input type="hidden" name="createdBy"
				value="<%=bean.getCreatedBy()%>">

			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>">

			<input type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>

				<tr>

					<th align="left">Hospitel Code<span style="color: red">*</span></th>

					<td><input type="text" name="hospitelcode"
						value="<%=DataUtility.getStringData(bean.getHospitelcode())%>"
						placeholder="Enter Hospitel Code"></td>

					<td><font color="red"><%=ServletUtility.getErrorMessage("hospitelcode", request)%></font></td>

				</tr>

				<tr>

					<th align="left">Hospitel Name<span style="color: red">*</span></th>

					<td><input type="text" name="hospitelname"
						value="<%=DataUtility.getStringData(bean.getHospitelname())%>"
						placeholder="Enter Hospitel Name"></td>

					<td><font color="red"><%=ServletUtility.getErrorMessage("hospitelname", request)%></font></td>

				</tr>

				<tr>

					<th align="left">Contact Number<span style="color: red">*</span></th>

					<td><input type="text" name="contactnumber"
						value="<%=DataUtility.getStringData(bean.getContactnumber())%>"
						placeholder="Enter Contact Number"></td>

					<td><font color="red"><%=ServletUtility.getErrorMessage("contactnumber", request)%></font></td>

				</tr>

				<tr>

					<td align="center" colspan="3">

						<%
							if (bean != null && bean.getId() > 0) {
						%>

						<input type="submit" name="operation"
							value="<%=HospitelCtl.OP_UPDATE%>">

						<input type="submit" name="operation"
							value="<%=HospitelCtl.OP_CANCEL%>">

						<%
							} else {
						%>

						<input type="submit" name="operation"
							value="<%=HospitelCtl.OP_SAVE%>">

						<input type="submit" name="operation"
							value="<%=HospitelCtl.OP_RESET%>">

						<%
							}
						%>

					</td>

				</tr>

			</table>

		</div>

	</form>

	<%@ include file="Footer.jsp"%>

</body>

</html>