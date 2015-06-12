<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Group to a Candidate</title>
<link rel="stylesheet" href="css/asap.css">
</head>
<%
	Map<Integer, String> assignMap = (Map<Integer, String>) session
			.getAttribute("assignGroupList");
%>
<body>
	<%@include file="aboutASAP.jsp"%><br />
	<br />
	<fieldset>
		<legend class="heading">Assign Group to a Candidate</legend>
		<form action="AssociateGroupWithCandidate.action" method="post">
			<table cellspacing="4" cellpadding="4">
				<tr>
					<td rowspan="4" valign="top"><img alt="assigngroup"
						src="images/assigngroup.png" /></td>
					<td class="label">Candidate Name&nbsp;</td><td>	<select name="candNameAndId">
					<option value="-1">Please Select</option>
					<%
						Set set = assignMap.entrySet();
						Iterator it = set.iterator();
						while (it.hasNext()) {
							Map.Entry mapEn = (Map.Entry) it.next();
					%>
							<option value="<%=mapEn.getKey()%>"><%=mapEn.getKey()%></option>
							<%
								}
							%>
					</select></td>
				</tr>

				<tr>
				<td class="label">Group Name&nbsp;</td><td>	<select name="groupName">
					<option value="-1">Please Select</option>
					<%
						Set setV = assignMap.entrySet();
						Iterator itV = setV.iterator();
						while (itV.hasNext()) {
							Map.Entry mapEnV = (Map.Entry) itV.next();
					%>
							<option value="<%=mapEnV.getValue()%>"><%=mapEnV.getValue()%></option>
							<%
								}
							%>
					</select></td>
				
				</tr>
				<tr>
					<td align="right" colspan="2"><input type="submit"
						name="command" value="Save" class="control" /> <input
						type="button" value="Cancel"
						onclick="location.href='<%="AdminHome.action"%>'" class="control" />
					</td>
				</tr>
				<tr>
					<td class="errClass" align="right" colspan="2">
						<%
							if (request.getAttribute("message") != null) {
								out.println("<font color='red'>"
										+ request.getAttribute("message") + "</font>");
							}
						%>
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>