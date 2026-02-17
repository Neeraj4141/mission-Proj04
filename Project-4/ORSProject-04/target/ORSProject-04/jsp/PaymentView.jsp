<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.PaymentCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>
<head>
<title>Add Payment</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>

<body>
<form action="<%=ORSView.PAYMENT_CTL%>" method="post">

	<%@ include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.PaymentBean"
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
			Payment
		</h1>

		<h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
		<h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>

		<input type="hidden" name="id" value="<%=bean.getId()%>">

		<table>

			<!-- Booking Id -->
			<tr>
				<th align="left">Booking Id<span style="color:red">*</span></th>
				<td>
					<input type="text" name="bookingId"
					placeholder="Enter Booking Id"
					value="<%=DataUtility.getStringData(bean.getBookingId())%>">
				</td>
				<td><font color="red">
					<%=ServletUtility.getErrorMessage("bookingId", request)%>
				</font></td>
			</tr>

			<!-- Amount -->
			<tr>
				<th align="left">Amount<span style="color:red">*</span></th>
				<td>
					<input type="text" name="amount"
					placeholder="Enter Amount"
					value="<%=DataUtility.getStringData(bean.getAmount())%>">
				</td>
				<td><font color="red">
					<%=ServletUtility.getErrorMessage("amount", request)%>
				</font></td>
			</tr>

			<!-- Payment Mode -->
			<tr>
				<th align="left">Payment Mode<span style="color:red">*</span></th>
				<td>
					<input type="text" name="paymentMode"
					placeholder="Cash / UPI / Card"
					value="<%=DataUtility.getStringData(bean.getPaymentMode())%>">
				</td>
				<td><font color="red">
					<%=ServletUtility.getErrorMessage("paymentMode", request)%>
				</font></td>
			</tr>

			<!-- Payment Status -->
			<tr>
				<th align="left">Payment Status<span style="color:red">*</span></th>
				<td>
					<input type="text" name="paymentStatus"
					placeholder="Paid / Pending"
					value="<%=DataUtility.getStringData(bean.getPaymentStatus())%>">
				</td>
				<td><font color="red">
					<%=ServletUtility.getErrorMessage("paymentStatus", request)%>
				</font></td>
			</tr>

			<tr>
				<th></th>
				<td colspan="2">
					<%
						if (bean != null && bean.getId() > 0) {
					%>
						<input type="submit" name="operation" value="<%=PaymentCtl.OP_UPDATE%>">
						<input type="submit" name="operation" value="<%=PaymentCtl.OP_CANCEL%>">
					<%
						} else {
					%>
						<input type="submit" name="operation" value="<%=PaymentCtl.OP_SAVE%>">
						<input type="submit" name="operation" value="<%=PaymentCtl.OP_RESET%>">
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
