<%-- 
    Document   : index
    Created on : Jul 12, 2020, 3:10:30 PM
    Author     : alexl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("Iniciar Sesion.jsp");

%>
