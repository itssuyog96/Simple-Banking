<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%! String pageId = "error";  %>
<%! String role = "default";  %>

	
<% if(request.getAttribute("role") != null){ 
	role = request.getAttribute("role").toString();  %>
	
<%= Template.getHeader(pageId) %>
<%= Template.getMenu(role, pageId) %>
<!-- MAIN BODY -->
	
<div class="row">
	<span><h5>Error <% out.println(request.getAttribute("error-message")); %></h5></span>
</div>

<% } else { %>


<div class="row">
	<span><h5>Error <%= response.getStatus() %></h5></span>
</div>

<% } %>

<!-- END OF MAIN BODY -->
<%= Template.getFooter() %>
