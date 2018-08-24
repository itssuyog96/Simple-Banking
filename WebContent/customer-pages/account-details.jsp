<%@page import="com.zycus.banking.account.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.zycus.banking.account.AccountDAO"%>
<%@page import="com.zycus.banking.customer.Customer"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "account-details";%>
<%!String role = "customer";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<% 
	Customer customer = (Customer)session.getAttribute("user");
	List<Account> accounts = new AccountDAO().findByCustomer(customer.getId());
	
%>
<!-- MAIN BODY -->

<div class="row">
	<div class="col col-xl-3">
		<div class="card" id="profile_card">
			<p>
				<span class="username"><%= customer.getFirstName() +" "+customer.getLastName()  %></span>
			</p>
			<img class="img-thumbnail" src="../../user_placeholder.png"
				alt="Thumbnail image" id="placeholder">
			<button type="button" class="btn"><a style="color:black; display:block;" class="btn" href="../../logout.jsp">Logout</a></button>
		</div>
	</div>
	<div class="col col-xl-9">
		<div class="card" id="accounts_card">
			<div class="card" id="balance_card">
				<div class="card-header">Total Balance</div>
				<div class="card-body">
					<i class="fas fa-user" id="icon"></i> <span id="savings">Savings</span>
					<span>&#8377 0.0</span>
				</div>
			</div>
			<div class="card" id="all_accounts_card">
				<div class="card-header">Your Accounts</div>
				<div class="card-body">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Bank</th>
								<th>Branch</th>
								<th>Account Number</th>
								<th>Available Balance</th>
								<th>Type</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
						<%
						for(Account account: accounts){
							if(account == null)
								break;
							out.println("<tr>");
							out.println("<td>"+ account.getBankCode() +"</td>");
							out.println("<td>"+ account.getBranchCode() +"</td>");
							out.println("<td>"+ account.getAccountNumber() +"</td>");
							out.println("<td>&#8377; "+ account.getBalance() +"</td>");
							out.println("<td>"+ account.getAccountType() +"</td>");
							out.println("<td>"+ account.getAccountStatus() +"</td>");
							out.println("</tr>");
						}
							
						%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
