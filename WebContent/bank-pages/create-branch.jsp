<%@page import="com.zycus.banking.branch.BranchMap"%>
<%@page import="com.zycus.banking.bank.BankDAO"%>
<%@page import="com.zycus.banking.bank.Bank"%>
<%@page import="com.zycus.banking.branch.BranchDAO"%>
<%@page import="com.zycus.banking.branch.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "create-branch";%>
<%!String role = "bank";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->

<div class="row">
	<span class="error"></span> <span class="success"></span>

	<%
		try {
			if (request.getParameter("origin") != null) {
				new BranchDAO().create(new BranchMap(request.getParameter("bankCode"), Integer.parseInt(request.getParameter("branchCode"))));
				out.println("<span class=\"success\">New branch added successfully!</span>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<span class=\"error\">Error occured while adding new branch.</span>");
		}
	%>

	<br />
	<hr>
	<div class="form-inline col-md-12">
		<form class="form row col-md-12" action="create-branch"
			method="get">

			<div class="form-group col-md-5">
				<label for="bankCode">Select Bank</label> <select
					class="select-control form-control" name="bankCode" id="bankCode">
					<%
						List<Bank> banks = (new BankDAO()).findAll();
						try {
							for (Bank bank : banks) {
								if (bank == null)
									break;
								out.print("<option value=\"" + bank.getBankCode() + "\">");
								out.print(bank);
								out.print("</option>");
							}
						} catch (NullPointerException e) {
							out.println("<tr><td colspan=5>No branches registerd</td></tr>");
						}
					%>
				</select>
			</div>

			<div class="form-group col-md-4">
				<label class="label-control">Branch Code</label> <input
					class="form-control" type="text" name="branchCode" id="branchCode" />
			</div>
			<input type="hidden" name="origin" value="true" />
			<div class="form-group col-md-1">
				<input type="submit" value="Create Branch" class="btn btn-success" style="padding-right: 130px;" />
			</div>
		</form>

	</div>
	<br />


	<div class="row table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Bank Code</th>
					<th>Branch Code</th>

				</tr>
			</thead>
			<tbody>

				<%
					List<Branch> branches = (new BranchDAO()).findAll();
					try {
						for (Branch branch : branches) {
							if (branch == null)
								break;
							out.println("<tr>");
							out.println("<td>" + branch.getBankCode() + "</td>");
							out.println("<td>" + branch.getBranchCode() + "</td>");

							out.println("</tr>");
						}
					} catch (NullPointerException e) {
						out.println("<tr><td colspan=5>No branches registerd</td></tr>");
					}
				%>
			</tbody>
		</table>
	</div>
</div>

<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
