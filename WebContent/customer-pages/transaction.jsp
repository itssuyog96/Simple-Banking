<%@page import="java.util.stream.Collector"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.zycus.banking.account.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "transaction";%>
<%!String role = "customer";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->

<%
	List<Account> accounts;
	try {
		accounts = (List<Account>) request.getAttribute("accounts");

	} catch (Exception e) {
		request.setAttribute("error-message", "500 : Unable to load accounts!");
		request.setAttribute("role", role);
		request.getRequestDispatcher("error.jsp").forward(request, response);
		return;
	}
%>

<style>
div {
	max-width: 100%;
}

tr {
	padding: 20px;
	margin-top: 30px;
}

.actions {
	text-align: -webkit-center;
}

.error {
	color: red;
	display: none;
}

.success {
	color: green;
	display: none;
}
</style>

<span class="error"></span>
<span class="success"></span>
<div class="row">
	<div class="col-md-4 actions">
		<h3>WITHDRAW</h3>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>SELECT ACCOUNT</th>
					</tr>
				<thead>
				<tbody>
					<tr>
						<td>
							<form id="withdraw-form">
								<select id="w-account"
									class="form-control select-control select">
									<%
										for (Account account : accounts) {
											out.println("<option value=\"" + account.getAccountNumber() + "-" + account.getBranchCode() + "-"
													+ account.getBankCode() + "\">" + account.getBankCode() + "-" + account.getBranchCode() + "-"
													+ account.getAccountNumber() + "</option>");
										}
									%>
								</select> <br> <br> <strong>AMOUNT</strong><br> <input
									type="text" class="form-control" id="w-amount" name="amount" /><br>
								<hr>
								<input type="submit" class="btn" value="WITHDRAW" />
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-4 actions">
		<h3>DEPOSIT</h3>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>SELECT ACCOUNT</th>
					</tr>
				<thead>
				<tbody>
					<tr>
						<td>
							<form id="deposit-form">
								<select id="d-account"
									class="form-control select-control select">
									<%
										for (Account account : accounts) {
											out.println("<option value=\"" + account.getAccountNumber() + "-" + account.getBranchCode() + "-"
													+ account.getBankCode() + "\">" + account.getBankCode() + "-" + account.getBranchCode() + "-"
													+ account.getAccountNumber() + "</option>");
										}
									%>
								</select> <br> <br> <strong>AMOUNT</strong><br> <input
									type="text" class="form-control" id="d-amount" name="amount" /><br>
								<hr>
								<input type="submit" class="btn" value="DEPOSIT" />
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-4 actions">
		<h3>TRANSFER</h3>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>SELECT ACCOUNT</th>
					</tr>
				<thead>
				<tbody>
					<tr>
						<td>
							<form id="transfer-form">
								<select id="f-account"
									class="form-control select-control select">
									<%
										for (Account account : accounts) {
											out.println("<option value=\"" + account.getAccountNumber() + "-" + account.getBranchCode() + "-"
													+ account.getBankCode() + "\">" + account.getBankCode() + "-" + account.getBranchCode() + "-"
													+ account.getAccountNumber() + "</option>");
										}
									%>
								</select> <br> <br> <strong>BENEFICIARY ACCOUNT</strong><br>

								<input type="text" class="form-control" id="t-account"
									name="t-account" /> <br> <br> <strong>BENEFICIARY
									IFSC</strong><br> <input type="text" class="form-control" id="ifsc"
									name="amount" /> <br>` <br> <strong>AMOUNT</strong><br>
								<input type="text" class="form-control" id="t-amount"
									name="amount" /> <br>
								<hr>
								<input type="submit" class="btn" value="TRANSFER" />
							</form>
						</td>

					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script>
	doTransaction = function(data) {
		$('.error').css('display', 'none');
		$('.success').css('display', 'none');
		$.ajax({
			url : '/transaction.do',
			method : 'POST',
			data : data,
			success : function(data) {
				$('.success').text(data.data);
				$('.success').css('display', 'block');
			},
			error : function(error) {
				console.log(error.responseText);
				$('.error').css('display', 'block');
				$('.error').text(error.responseText);
			}
		});
	}

	$('#withdraw-form').on('submit', function(e) {
		e.preventDefault();
		var data = {
			'account' : $('#w-account').val().split('-')[0],
			'amount' : $('#w-amount').val(),
			'branch' : $('#d-account').val().split('-')[1],
			'bank' : $('#d-account').val().split('-')[2],
			'type' : 'w'
		}

		doTransaction(data);
	})

	$('#deposit-form').on('submit', function(e) {
		e.preventDefault();
		var data = {
			'account' : $('#d-account').val().split('-')[0],
			'amount' : $('#d-amount').val(),
			'branch' : $('#d-account').val().split('-')[1],
			'bank' : $('#d-account').val().split('-')[2],
			'type' : 'd'
		}
		doTransaction(data);
	})

	$('#transfer-form').on('submit', function(e) {
		e.preventDefault();
		var data = {
			'f-account' : $('#f-account').val().split('-')[0],
			't-account' : $('#t-account').val(),
			'amount' : $('#t-amount').val(),
			'branch' : $('#f-account').val().split('-')[1],
			'bank' : $('#f-account').val().split('-')[2],
			'ifsc' : $('#ifsc').val(),
			'type' : 't'
		}
		doTransaction(data);
	})
</script>


<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
