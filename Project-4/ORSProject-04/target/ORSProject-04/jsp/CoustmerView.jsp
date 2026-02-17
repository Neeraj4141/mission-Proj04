<%@page import="in.co.rays.proj4.controller.CoustmerCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>
<head>
<title>Add Customer</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>

<body>

	<form action="<%=ORSView.COUSTMER_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean"
			class="in.co.rays.proj4.bean.CoustmerBean" scope="request"></jsp:useBean>

		<div align="center">

			<h1 style="color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>
				Customer
			</h1>

			<h1>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h1>

			<!-- Hidden Fields -->
			<input type="hidden" name="id" value="<%=bean.getId()%>">
			<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
			<input type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>

				<tr>
					<th align="left">Customer Name<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="Enter Customer Name"></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Product<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> productMap = new HashMap<String, String>();
							productMap.put("washing machine", "Washing Machine");
							productMap.put("mixture", "Mixture");
							productMap.put("freeze", "Freeze");
							productMap.put("cooler", "Cooler");
							productMap.put("microvev", "Microvev");

							String productList =
									HTMLUtility.getList("product", bean.getProduct(), productMap);
						%>
						<%=productList%>
					</td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("product", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Address</th>
					<td><input type="text" name="address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"
						placeholder="Enter Address"></td>
					<td></td>
				</tr>

				<tr>
					<th align="left">Mobile No<span style="color: red">*</span></th>
					<td><input type="text" name="mobileno"
						value="<%=DataUtility.getStringData(bean.getMobileno())%>"
						placeholder="Enter Mobile No"></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Rate<span style="color: red">*</span></th>
					<td><input type="text" name="rate"
						value="<%=DataUtility.getStringData(bean.getRate())%>"
						placeholder="Enter Rate"></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("rate", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Payment Status<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> payMap = new HashMap<String, String>();
							payMap.put("Paid", "Paid");
							payMap.put("Pending", "Pending");

							String payList =
									HTMLUtility.getList("paymentstatus",
											bean.getPaymentstatus(), payMap);
						%>
						<%=payList%>
					</td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("paymentstatus", request)%></font></td>
				</tr>

				<tr>
					<td align="center" colspan="3">
						<%
							if (bean != null && bean.getId() > 0) {
						%>
						<input type="submit" name="operation"
							value="<%=CoustmerCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
							value="<%=CoustmerCtl.OP_CANCEL%>">
						<%
							} else {
						%>
						<input type="submit" name="operation"
							value="<%=CoustmerCtl.OP_SAVE%>">
						<input type="submit" name="operation"
							value="<%=CoustmerCtl.OP_RESET%>">
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
