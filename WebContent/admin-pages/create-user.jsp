<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%! String pageId = "create-user";  %>
<%! String role = "admin";  %>

<%= Template.getHeader(pageId) %>
<%= Template.getMenu(role, pageId) %>
<!-- MAIN BODY -->

<div class="row">
	<form method="post">
		<input type="file" name="file" id="file" placeholder="File for bulk upload" />
		<input type="submit" class="btn btn-default" value="Upload" />
	</form>
</div>

<!-- END OF MAIN BODY -->
<%= Template.getFooter() %>
