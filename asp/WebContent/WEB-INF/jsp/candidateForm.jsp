<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Form</title>
<link rel="stylesheet" href="css/asap.css">
</head>
<%
	Map<Integer, String> groupDetails = (Map<Integer, String>) session
			.getAttribute("mapGroup");
%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.query-2.1.7.js"></script>
<script type="text/javascript">
function nameVerfication()
{
	var username = document.getElementById("username").value;

	alert(username);
	var url="userVerfication.action?usename="+username;
	alert("URL "+url);
	 var xmlhttp;
	if (window.XMLHttpRequest)
	{
	 // code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("welcometext").innerHTML = xmlhttp.responseText;
		}
	};
	xmlhttp.open("POST", "userVerfication.action?usename="+username, true);
	xmlhttp.send(); 
}
</script>
<body>
	<%@include file="aboutASAP.jsp"%><br />
	<br />
	<fieldset>
		<legend class="heading">Add a Candidate</legend>
		<form action="AddCandidateInsert.action" method="post">
			<table cellspacing="4" cellpadding="4">
				<tr>
					<td rowspan="4" valign="top"><img alt="login"
						src="images/user.png" /></td>
					<td class="label">Iwinner Id&nbsp;</td>
					<td><input type="text" name="employeeId" /></td>
				</tr>
				<tr>
					<td class="label">Name&nbsp;</td>
					<td><input type="text" name="username" id="username" onblur="nameVerfication()" /></td>
					<td><div id="welcometext"></div></td>
				</tr>
				<tr>
					<td class="label">Group&nbsp;</td>
					<td><select id=groupId name="groupId">
							<option value="-1">Please Select</option>
							<%
								Set set = groupDetails.entrySet();
								Iterator it = set.iterator();
								while (it.hasNext()) {
									Map.Entry mapEn = (Map.Entry) it.next();
							%>
							<option value="<%=mapEn.getKey()%>"><%=mapEn.getValue()%></option>
							<%
								}
							%>
					</select></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right" colspan="2"><input type="submit"
						name="command" value="Save" class="control" /> <input
						type="button" value="Cancel"
						onclick="location.href='<%="AdminHomePage.action"%>'"
						class="control" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="errClass" align="center">
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