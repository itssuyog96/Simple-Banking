<%@page import="java.util.List"%>
<%@page import="com.zycus.banking.bank.Bank"%>
<%@page import="com.zycus.banking.bank.BankDAO"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "create-bank";%>
<%!String role = "admin";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->
<style>
	.error{
		color: red;
	}
	.success{
		color: green;
	}
</style>

<div class="row">
	<%
		try {
			if (request.getParameter("origin") != null) {
				if(request.getParameter("bankCode") == null || request.getParameter("bankCode").toString().length() < 3)
					throw new Exception("Minimum length of bank code should be 3 characters.");
				if(request.getParameter("bankName") == null || request.getParameter("bankName").toString().length() < 5)
					throw new Exception("Minimum length of bank name should be 5 characters.");
				new BankDAO().create(new Bank(request.getParameter("bankCode"), request.getParameter("bankName")));
				out.println("<span class=\"success\">New bank added successfully!</span>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<span class=\"error\">"+ e.getMessage() +"</span><hr>");
		}
	%>
	<br />



	<div class="form-inline col-md-12">
		<form class="form row col-md-12" action="create-bank" method="get">


			<div class="form-group col-md-3">
				<label class="label-control">Bank Code</label> <input
					class="form-control" type="text" name="bankCode" id="bankCode" />
			</div>

			<div class="form-group col-md-3">
				<label class="label-control">Bank Name</label> <input
					class="form-control" type="text" name="bankName" id="bankName" />
			</div>
			<input type="hidden" name="origin" value="true" />
			<div class="form-group col-md-3">
				<input type="submit" value="Create Bank" class="btn btn-success" style="margin-top:30px;" />
			</div>
		</form>

	</div>
	<br /> <br />

	<div class="row table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Bank Code</th>
					<th>Bank Name</th>
					<th>Options</th>

				</tr>
			</thead>
			<tbody>

				<%
					List<Bank> banks = (new BankDAO()).findAll();
					try {
						for (Bank bank : banks) {
							if (bank == null)
								break;
							out.println("<tr>");
							out.println("<td>" + bank.getBankCode() + "</td>");
							out.println("<td>" + bank.getBankName() + "</td>");
							out.println("<td><button onclick=\"deleteBank('"+ bank.getBankCode() +"', '"+ bank.getBankName() +"')\" class=\"btn btn-danger\">Delete</button></td>");

							out.println("</tr>");
						}
					} catch (NullPointerException e) {
						out.println("<tr><td colspan=5>No banks registerd</td></tr>");
					}
				%>
			</tbody>
		</table>
	</div>
</div>


<script>

old_url = window.location.href;  
var new_url = old_url.substring(0, old_url.indexOf('?'));

function deleteBank(bankCode, bankName){
	$.ajax({
		url : '/delete-bank.do',
		method: 'POST',
		data: {'bankCode': bankCode, 'bankName': bankName},
		success: function(data){
			console.log("deleted : " + data);
			location.replace(new_url);
		},
		error: function(error){
			console.log(error.responseText);
		}
	});
	
}



</script>
<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
