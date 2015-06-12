<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add a Test Case to an Assessment</title>
	<link rel="stylesheet" href="css/asap.css">
</head>
<%

List<String> listOfProblems=(List<String>)session.getAttribute("");

%>
<body>
	<%@include file="aboutASAP.jsp"%><br/><br/>
	<fieldset>
		<legend class="heading">Add a Test Case to an Assessment</legend>
		<form  action="AddTestCase.action">
		<table cellspacing="4" cellpadding="4">
			<tr>
				<td rowspan="6" valign="top"><img alt="testcase" src="images/testcase.png"/></td>
				<td class="label">Assessment&nbsp;</td>
				<td>
					<select path="problem.problemId" cssClass="control">
					<%for(String probName:listOfProblems) {%>
                		<options items="${probName}"/>
                		<%} %>
            		</select>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="label" valign="top">Description&nbsp;</td>
				<td>
						<textarea rows="2" cols="40" name="testCaseDescription" id="testCaseDescription"></textarea>
				</td>
			
			</tr>
			<tr>
				<td class="label" valign="top">Input&nbsp;</td>
				<td>
						<textarea rows="2" cols="40" name="inputData" id="inputData"></textarea>
				</td>
				
			</tr>
			<tr>
				<td class="label" valign="top">Expected Output&nbsp;</td>
				<td>
						<textarea rows="2" cols="40" name="expectedOutput" id="expectedOutput"></textarea>
				</td>
				
			</tr>
			<tr>
				<td class="label">Score&nbsp;</td>
				<td>
					<input id ="text"   name="score" />
				</td>
			
			</tr>
			<tr>
				<td align="right" colspan="2">
					<input type="submit" name="testCaseSubmission" value="Save" class="control"/>
					<input type="button" value="Cancel" onclick="location.href='<%="AdminHomePage.action"%>'" class="control"/>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="errClass" align="right" colspan="2">${requestScope.message}</td>
			</tr>
		</table>
		</form>
	</fieldset>
</body>
</html>