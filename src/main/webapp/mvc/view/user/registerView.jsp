<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="userBean" scope="session" class="es.uco.pw.display.javabean.userBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
	</head>
	<body>
	<%
		String nextPage = "../../control/user/registerController.jsp";
		String messageNextPage = request.getParameter("message");
		if(messageNextPage == null) messageNextPage = "";

		if(userBean != null && !userBean.getEmail().equals("")) {
			nextPage = "../../../index.jsp";
		} else {
	%>
	<%= messageNextPage %><br/><br/>
	<form method="post" action="../../control/user/registerController.jsp">
		<label for="name">Name: </label>
			<input type="text" name="name" value=""><br/>
		<label for="primerApe">Fisrt Surname: </label>
			<input type="text" name="primerApe" value=""><br/>
		<label for="segundoApe">Second Surname: </label>
			<input type="text" name="segundoApe" value=""><br/>
		<label for="nacimiento">Birthdate: </label>
			<input type="text" name="nacimiento" value=""><br/>
		<label for="email">Email: </label>
			<input type="text" name="email" value="">	
		<br/>
			<input type="submit" value="Submit">
	</form>
	<% } %>

	</body>
</html>