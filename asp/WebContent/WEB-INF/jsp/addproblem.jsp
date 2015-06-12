<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add an Assessment</title>
	<link rel="stylesheet" href="css/asap.css">
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.query-2.1.7.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#problemName").focus();
			$("#problemName").blur(
					function() {
						var problemName=($("#problemName").val());
						$.ajax({
						 url: "problemChecking.action?problemName="+problemName,
						 success: function(result){
							 $("#test").html(result);
					    }});
					});
			$("#addProb").click(function() {
				$("#description").focus();
				if ($.trim($("#description").val()) == '') {
					alert("Description is can't be empty ");
					return false;
				}
				$("#inputData").focus();
				if ($.trim($("#inputData").val()) == '') {
					alert("Input data is can't be empty ");
					return false;
				}
				$("#outputData").focus();
				if ($.trim($("#outputData").val()) == '') {
					alert("OutPut expected value can't be empty ");
					return false;
				}
				$("#timeLimit").focus();
				if ($.trim($("#timeLimit").val() ) == '') {
					alert("TimeLimit is can't be empty ");
					return false;
				}
				if(isNaN($('#timeLimit').val())){
					alert("TimeLimit only allow numers(0 to 9) ");
					return false;
					
				}
				return true;
				});

		});

</script>
<body>
	<%@include file="aboutASAP.jsp"%><br/><br/>
	<fieldset>
		<legend class="heading">Add an Assessment</legend>
		<form action="addProblemIn.action" method="post">
		<table cellspacing="4" cellpadding="4">
			<tr>
				<td rowspan="6" valign="top"><img alt="assessment" src="images/assessment.png"/></td>
				<td class="label">Title&nbsp;</td>
				<td>
					<input type="text" name="problemName" id="problemName" />
				</td>
				<td>
				<font color='red'><div id="test"></div></font>
				</td>
			</tr>
			<tr>
				<td class="label" valign="top">Description&nbsp;</td>
				<td>
				<textarea rows="3" cols="40" name="description" id="description"></textarea>
				</td>
			</tr>
			<tr>
				<td class="label" valign="top">Description of the Input&nbsp;</td>
				<td>
				<textarea rows="3" cols="40" name="inputData" id="inputData"></textarea>
				</td>
			</tr>
			<tr>
				<td class="label" valign="top">Description of the Output&nbsp;</td>
				<td>
				<textarea rows="3" cols="40" name="outputData" id="outputData"></textarea>
<!-- 					<textarea name="outputData" rows="3" cols="40"  />
 -->				</td>
			</tr>
			<tr>
				<td class="label">Time Limit (in minutes)&nbsp;</td>
				<td>
						<input type="text" name="timeLimit"  id="timeLimit"/>
				</td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					<input type="submit" name="command" value="Save" class="control" id="addProb"/>
					<input type="button" value="Cancel" onclick="location.href='<%="AdminHomePage.action"%>'" class="control" />
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<!-- <td  align="right" colspan="2"> -->
	<center>
		<%
			if (request.getAttribute("problemExists") != null) {
				out.println("<font color='red'>"+request.getAttribute("problemExists")+"<a href=''></a></font>");
			}
		%>
	</center><!-- </td> -->
			</tr>
		</table>
		</form>
	</fieldset>
</body>
</html>