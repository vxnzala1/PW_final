<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="userBean" scope="session" class="es.uco.pw.display.javabean.userBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Gestión de Karts</title>
			<link rel="stylesheet" href= "style.css" ></link>
	</head>
	<body>
		<%if (request.getParameter("reset") != null) {%>
			<jsp:setProperty property="email" value="" name="userBean"/>
		<%}if (userBean == null || userBean.getEmail()==""){%>
			<form method="post" action="/ProjectPW/mvc/control/user/registerController.jsp">
				<input type="submit" value="Registrarse">
			</form>
			
			<form method="post" action="/ProjectPW/mvc/control/user/loginController.jsp">
				<input type="submit" value="Acceder">
			</form>
							
			
			 <!-- <a href="/ProjectPW/mvc/control/user/loginController.jsp">Acceder</a>
			<a href = "/ProjectPW/mvc/control/user/registerController.jsp">Registrarse</a> -->
		<%} else { %>
			¡¡Bienvenido <jsp:getProperty property="email" name="userBean"/>!! 
			<a href="/ProjectPW/mvc/control/user/logoutController.jsp">Salir</a>
		<% } %>
	</body>
</html>