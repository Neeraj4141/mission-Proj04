<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.LoanCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Add Loan</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.LOAN_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.LoanBean"
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
				Loan
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
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> 
			<input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th align="left">Date of Birth<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="dob"
						placeholder="Enter DOB (dd/mm/yyyy)"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("dob", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Customer Id<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="custmerId"
						placeholder="Enter Customer Id"
						value="<%=DataUtility.getStringData(bean.getCustmerId())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("custmerId", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Loan Amount<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="loanAmount"
						placeholder="Enter Loan Amount"
						value="<%=DataUtility.getStringData(bean.getLoanAmount())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("loanAmount", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Interest<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="interst"
						placeholder="Enter Interest"
						value="<%=DataUtility.getStringData(bean.getInterst())%>"></td>
					<td style="position: fixed;"><font color="red"> 
						<%=ServletUtility.getErrorMessage("interst", request)%>
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
						<input type="submit" name="operation" value="<%=LoanCtl.OP_UPDATE%>"> 
						<input type="submit" name="operation" value="<%=LoanCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2">
						<input type="submit" name="operation" value="<%=LoanCtl.OP_SAVE%>"> 
						<input type="submit" name="operation" value="<%=LoanCtl.OP_RESET%>">
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
