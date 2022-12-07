<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="userBean" scope="session" class="es.uco.pw.display.javabean.userBean"></jsp:useBean>

<%
	//customerBean = null;
	String nextPage = "../../../index.jsp";
	String mensajeNextPage = "hola";
	
%>
<jsp:setProperty property="email" value="" name="userBean"/>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>