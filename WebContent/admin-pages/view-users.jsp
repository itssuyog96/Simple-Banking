<%@page import="com.zycus.banking.bank.Bank"%>
<%@page import="com.zycus.banking.bank.BankDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.zycus.banking.util.ConnectionUtil"%>
<%@page import="java.util.LinkedList"%>
<%@page import="com.zycus.banking.customer.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.zycus.banking.util.userType"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.zycus.banking.customer.CustomerDAO"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "view-users";%>
<%!String role = "admin";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->
<script>
	getBranch = function(id, val) {
		console.log($('#' + id + '_bankcode').val());

		$.ajax({
			url : "/get-branch-by-bank-code.do",
			method : "GET",
			data : {
				"bankCode" : $('#' + id + '_bankcode').val()
			},
			success : function(data) {
				data = JSON.parse(data);
				$('#' + id + '_branchcode').html("");
				$('#' + id + '_branchcode').append(data.data);
				$('#' + id + '_branchcode').val(val);
			}
		})
	}
	
	updateCustomer = function(id) {
		
		if($('#' + id + '_username').val() == "")
			alert("Username should be assigned before regsitration!");
		
		$.ajax({
			url : "/update-customer.do",
			method : "POST",
			data : {
				"id" : id,
				"username" : $('#' + id + '_username').val(),
				"role" : $('#' + id + '_role').val(),
				"bankCode" : $('#' + id + '_bankcode').val(),
				"branchCode" : $('#' + id + '_branchcode').val(),
				"accountType" : $('#accType').text()
			},
			success : function(data) {
				location.reload();
			}
		})
	}
	
	enableBank  = function(id, enabled){
		$('#' + id+'_bankcode').prop('disabled', !enabled);
	}
	enableBranch  = function(id, enabled){
		$('#' + id+'_branchcode').prop('disabled', !enabled);
	}
	
	setDisable = function(id){
		console.log($('#' + id + '_role option:selected').text());
		switch($('#' + id + '_role option:selected').text()){
		case 'BANK':			
			enableBank(id, true);
			enableBranch(id, false);
			break;
		case 'BRANCH':
		case 'CUSTOMER':
			enableBank(id, true);
			enableBranch(id, true);
			break;
		case 'null':
		case 'ADMIN':
			enableBank(id, false);
			enableBranch(id, false);
			break;
			default: 
				enableBank(id, false);
				enableBranch(id, false);
				
		}
	}
</script>
<div class="row table-responsive">
	<table class="table">
		<thead>
			<%
				String SQL_SELECT = "";
				JSONObject obj = new JSONObject();
				boolean flag = true;
				Connection con;
				try {
					con = ConnectionUtil.getConnection();
					userType rolex = userType.valueOf(role.toUpperCase());
					switch (rolex.ordinal()) {
					case 0:
						SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, role, bankcode, branchcode, reg_status FROM customer";
						break;
					case 1:
						SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, role, branchcode, reg_status FROM customer WHERE ROLE='BRANCH' OR ROLE='CUSTOMER'";
						break;
					case 2:
						SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, reg_status FROM customer WHERE ROLE='CUSTOMER'";
						break;
					case 3:
						flag = false;
					default:
						flag = false;
					}
					if (flag) {
						PreparedStatement ps = con.prepareStatement(SQL_SELECT);
						ResultSet rs = ps.executeQuery();
						List<Bank> banks = (new BankDAO()).findAll();

						out.println("<tr>");
						for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
							if (rs.getMetaData().getColumnName(j).equalsIgnoreCase("reg_status"))
								continue;
							out.println("<th>" + rs.getMetaData().getColumnName(j) + "</th>");
						}
						out.println("<th>OPTIONS</th>");
						out.println("</tr>");
			%>
		</thead>
		<tbody>
			<%
				while (rs.next()) {
							String id = rs.getString("id");
							out.println("<tr id=\"" + id + "\">");
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("role")) {
									out.println("<td><select id=\"" + id
											+ "_role\" class=\"form-control select-control select\">");
									out.println("<option value=\"null\">None</option>");
									for (userType types : userType.values()) {
										out.println("<option value=\"" + types + "\" "
												+ ((types.toString().equalsIgnoreCase(rs.getString(i))) ? "selected" : "")
												+ ">" + types + "</option>");
									}
									out.println("</select></td>");
									

								} else if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("reg_status")) {
									/* out.println("<td>" + rs.getString(i) + "</td>"); */
									out.println("<td>" + (Boolean.parseBoolean(rs.getString(i))
											? "<button id=\""+ id +"_register\" class=\"btn btn-sm btn-default btn-table btn-register\" onclick=\"register('"
													+ rs.getString("id") + "', )\">UPDATE</button>"
											: "<button id=\""+ id +"_register\" class=\"btn btn-sm btn-success btn-table btn-register\" onclick=\"register('"
													+ rs.getString("id") + "', )\">UPDATE</button>")
											+ "</td>");
								} else if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("username")) {
									if (!Boolean.parseBoolean(rs.getString("reg_status")) || rs.getString(i) == null) {
										out.println("<td><input type=\"text\" id=\"" + id + "_username\"/></td>");
									} else {
										out.println("<td>" + rs.getString(i) + "</td>");
									}
								} else if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("BANKCODE")) {
									out.println("<td><select id=\"" + id
											+ "_bankcode\" class=\"form-control select-control select\">");
									out.println("<option value=\"null\">None</option>");
									for (Bank bank : banks) {
										out.println("<option value=\"" + bank.getBankCode() + "\" " 
												+ ((bank.getBankCode().equalsIgnoreCase(rs.getString(i))) ? "selected" : "")
												+ ">"
												+ bank.getBankName() + "</option>");
									}
									out.println("</select></td>");
									out.println("<script>$('#" + id
											+ "_bankcode').on('change', function(e){ getBranch($(this).attr('id').split('_')[0]) }); getBranch('"
											+ id + "', '"+ rs.getString("BRANCHCODE") +"')</script>");

								} else if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("BRANCHCODE")) {
									out.println("<td><select id=\"" + id
											+ "_branchcode\" class=\"form-control select-control select\">");
									out.println("</select></td>");
									out.println("<script>$('#" + id
											+ "_role').on('change', function(e){ setDisable($(this).attr('id').split('_')[0]) }); setDisable('"
											+ id + "')</script>");

								} else {
									out.println("<td>" + rs.getString(i) + "</td>");
								}
							}
							out.println("</tr>");
						}

					}
			%>




			<%
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			%>
		</tbody>
	</table>
</div>
<span id="accType" style="display:none;"></span>
<span id="accId" style="display:none;"></span>

<div class="cover" style="height:100%; width:100%; position:absolute; left:0; top:0; background-color:rgba(0,0,0,0.5);  z-index:999; display:none;">
	<div style="text-align:center; position:absolute; background-color: #FFFFFF; left:50%; top:50%; height:auto; width:auto; padding:50px; margin-left:-100px; margin-top:-100px;">
		<style>
			.btn-x{
				margin:10px;
			}
		</style>
		<span>Type of Account?</span><br/>
		<hr/>
		<button id="btn-savings" class="btn btn-default btn-x">SAVINGS</button>
		<button id="btn-current" class="btn btn-default btn-x">CURRENT</button>
	</div>
</div>

<script>

	$('.btn-x').on('click', function(){
		$('#accType').text($(this).attr('id').split('-')[1]);
		$('.cover').css('display', 'none');
		updateCustomer($('#accId').text());
	});
	
	$('.btn-register').on('click', function(){
		$('#accId').text($(this).attr('id').split('_')[0]);
		if($('#' + $('#accId').text() + '_role').val() == 'CUSTOMER')
			$('.cover').css('display', 'block');
		else
			updateCustomer($('#accId').text());
	});

</script>

<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
