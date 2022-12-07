<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "es.uco.pw.business.user.UserDTO ,es.uco.pw.data.dao.user.UserDAO" %>
<jsp:useBean  id="userBean" scope="session" class="es.uco.pw.display.javabean.userBean"></jsp:useBean>
<%@ page import = "java.util.Date" %>

<%
String nextPage = "../../../index.jsp";
String mensajeNextPage = "";

if (userBean == null || userBean.getEmail().equals("")) {
	String nameUser = request.getParameter("name");
	String fisrtSurnameUser = request.getParameter("primerApe");
	String secondSurnameUser = request.getParameter("segundoApe");
	String birthdate = request.getParameter("nacimiento");
	String emailUser = request.getParameter("email");

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (emailUser != null) {
		//Se accede a bases de datos para obtener el usuario
		UserDAO userDAO = new UserDAO();
		UserDTO user = new UserDTO();
		user = userDAO.obtenerUsuarioEmail(emailUser);
		String email = user.getEmail_();

		//Aquí sólo comprobamos que exista el usuario
		if (user != null && emailUser.equalsIgnoreCase(email)) { //lo del email que lo pilla null por la cara
			boolean comprobacion = userDAO.insertarUsuario(user);		
%>

<jsp:setProperty property="email" value="<%=emailUser%>" name="userBean"/>

<%
		} else {
			// Usuario no válido
			nextPage = "../../view/user/registerView.jsp";
			mensajeNextPage = "El usuario que ha indicado no existe o no es v&aacute;lido";
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../../view/user/registerView.jsp";		
	}
}
%>

<jsp:forward page="<%=nextPage%>">
<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>
