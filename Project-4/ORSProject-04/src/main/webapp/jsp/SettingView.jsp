<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.SettingCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Add Setting</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.SETTING_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.SettingBean"
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
				Setting
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
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			<table>
				<tr>
					<th align="left">Setting Name<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="settingName"
						placeholder="Enter Setting Name"
						value="<%=DataUtility.getStringData(bean.getSettingName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("settingName", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Setting Value<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="settingValue"
						placeholder="Enter Setting Value"
						value="<%=DataUtility.getStringData(bean.getSettingValue())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("settingValue", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Setting Type<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="settingType"
						placeholder="Enter Setting Type"
						value="<%=DataUtility.getStringData(bean.getSettingType())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("settingType", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Status<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="settingstatus"
						placeholder="Enter Status"
						value="<%=DataUtility.getStringData(bean.getSettingstatus())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("settingstatus", request)%>
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
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=SettingCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=SettingCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=SettingCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=SettingCtl.OP_RESET%>">
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