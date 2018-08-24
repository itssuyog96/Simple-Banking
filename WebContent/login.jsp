<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "login";%>
<%!String role = "default";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->


<%
 	String role = (String) request.getParameter("role");
	String redirectUrl = (String) request.getParameter("redirect-url");
%>

<div class="form">
	<h3>Please Sign In</h3>
	<form action="login" method="post">
		<input type="hidden" name="role" value="<%= role %>" />
		<input type="hidden" name="redirect-url" value="<%= redirectUrl %>" />
		<input type="text" class="form-control" name="username" id="username"
			placeholder="Username"> <input type="password" name="password"
			class="form-control" id="password" placeholder="Password">
		<button type="submit" class="btn btn-primary">Login</button>
		<p class="new_user">
			You agree to <a href="#">T&C</a>
	</form>
</div>

<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
