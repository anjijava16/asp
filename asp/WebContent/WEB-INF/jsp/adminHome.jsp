<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.iwinner.wts.asp.form.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ASAP - Home - Administrator</title>
	<link rel="stylesheet" href="css/asap.css">
</head>
<body>
 <%
 if(null!=(UserASPVO)session.getAttribute("userVO")){
 UserASPVO userVO=(UserASPVO)session.getAttribute("userVO");
 if(userVO.getUsername()!=null || !"".equals(userVO.getUsername())) 
 %>
 <%{ %>
 	<%@include file="aboutASAP.jsp"%><br/><br/>
	<fieldset>
	<legend class="heading">Welcome ${sessionScope.user}</legend>
	<table cellspacing="5" cellpadding="5">
		<tr>
			<td><img src="images/group.png"/></td>
			<td class="label"><a href="CreateGroupPage.action">Add a Group</a></td>
			<td rowspan="3" width="40px">&nbsp;</td>
			<td><img src="images/user.png"/></td>
			<td class="label"><a href="AddCandidate.action">Add a Candidate</a></td>
			<td rowspan="3" width="40px">&nbsp;</td>
			<td><img src="images/assigngroup.png"/></td>
			<td class="label"><a href="assignGroup.action">Assign Group to a Candidate</a></td>
		</tr>
		<tr>
			<td><img src="images/assessment.png"/></td>
			<td class="label"><a href="addProblemPage.action">Add an Assessment</a></td>
			<td><img src="images/testcase.png"/></td>
			<td class="label"><a href="testCase.action">Add a Test Case to an Assessment</a></td>
			<td><img src="images/schedule.png"/></td>
			<td class="label"><a href="ScheduleAssessment.action">Schedule an Assessment</a></td>
		</tr>
		<tr>
			<td><img src="images/activate.png"/></td>
			<td class="label"><a href="UpdateAssessment.action">Activate an Assessment</a></td>
			<td><img src="images/reports.png"/></td>
			<td class="label"><a href="ViewAssessmentReports.action">View Assessment Reports</a></td>
			<td><img src="images/logout.png"/></td>
			<td class="label"><a href="Logout.action" onclick="timedRefresh(2)">Logout</a></td>
		</tr>
		<tr>
			<td colspan="8" align="center"><span class="errClass">${requestScope.message}</span></td>
		</tr>
	</table>
	</fieldset>
	<%} %>
<%} else {%>
	<jsp:forward page="login.jsp"/>
<%} %>
</body>
</html>