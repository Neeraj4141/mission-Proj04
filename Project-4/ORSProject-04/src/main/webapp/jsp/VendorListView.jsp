<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.VendorListCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.bean.VendorBean"%>
<html>
<head>
<title>Vendor List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.VENDOR_LIST_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.VendorBean"
			scope="request"></jsp:useBean>

		<div align="center">
			<h1 style="color: navy">Vendor List</h1>

			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>

			<table width="100%">
				<tr>
					<td align="center">
						<label>Vendor Code :</label>
						<input type="text" name="vendorecode"
							value="<%=DataUtility.getStringData(bean.getVendorecode())%>">

						<label>Vendor Name :</label>
						<input type="text" name="vendorename"
							value="<%=DataUtility.getStringData(bean.getVendorename())%>">

						<input type="submit" name="operation"
							value="<%=VendorListCtl.OP_SEARCH%>">
						<input type="submit" name="operation"
							value="<%=VendorListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>

			<br>

			<table border="1" width="100%" cellpadding="5" cellspacing="0">
				<tr style="background-color: lightgray">
					<th>Select</th>
					<th>S.No.</th>
					<th>Vendor Code</th>
					<th>Vendor Name</th>
					<th>Service Type</th>
					<th>Contact Number</th>
					<th>Edit</th>
				</tr>

				<%
					List list = ServletUtility.getList(request);
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					for (int i = 0; i < list.size(); i++) {
						VendorBean vb = (VendorBean) list.get(i);
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=vb.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=vb.getVendorecode()%></td>
					<td><%=vb.getVendorename()%></td>
					<td><%=vb.getServicetype()%></td>
					<td><%=vb.getContactnumber()%></td>
					<td><a
						href="<%=ORSView.VENDOR_CTL%>?id=<%=vb.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<br>

			<table width="100%">
				<tr>
					<td>
						<input type="submit" name="operation"
							value="<%=VendorListCtl.OP_PREVIOUS%>"
							<%= (pageNo == 1) ? "disabled" : "" %>>
					</td>

					<td align="center">
						<input type="submit" name="operation"
							value="<%=VendorListCtl.OP_NEW%>">
						<input type="submit" name="operation"
							value="<%=VendorListCtl.OP_DELETE%>">
					</td>

					<td align="right">
						<input type="submit" name="operation"
							value="<%=VendorListCtl.OP_NEXT%>"
							<%= (request.getAttribute("nextListSize") == null || (Integer)request.getAttribute("nextListSize") == 0) ? "disabled" : "" %>>
					</td>
				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>">
			<input type="hidden" name="pageSize" value="<%=pageSize%>">

		</div>
	</form>
	<%@include file="Footer.jsp"%>
</body>
</html>