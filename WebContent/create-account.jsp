
<%@page import="com.zycus.banking.customer.CustomerDAO"%>
<%@page import="com.zycus.banking.account.accountType"%>
<%@page import="java.sql.Date"%>
<%@page import="com.zycus.banking.customer.Customer"%>
<%@page import="com.zycus.banking.branch.BranchMap"%>
<%@page import="com.zycus.banking.account.Account"%>
<%@page import="com.zycus.banking.account.AccountDAO"%>
<%@page import="com.zycus.banking.branch.BranchDAO"%>
<%@page import="com.zycus.banking.branch.Branch"%>
<%@page import="com.zycus.banking.bank.BankDAO"%>
<%@page import="com.zycus.banking.bank.Bank"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <style>
        .form-group {
            margin: 20px;
        }

        .label-control {
            margin-right: 20px;
        }

        .error {
            padding-left: 25%;
            color: red;
        }

        .success {
            padding-left: 25%;
            color: green;
        }
    </style>
    <title>Welcome | XYZ Bank</title>
</head>

<body>
    <div class="container">
        <h2 class="title">Open New Account | Iron Bank of Braavos</h2>

        <hr>


        <ul class="nav">
            <li></li>
        </ul>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="../Banking/">Home</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="new-account.html">New Account
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="pay-bills.html">Pay Bills</a>
                    </li>

                </ul>
            </div>
        </nav>

        <br/>
        <span class="error"></span>
        <span class="success"></span>
        <br/>
        <form id="account-form" action="create-account.jsp" class="form-inline col-md-12" method="GET">
        <div class="row col-md-offset-2 col-md-10">
                <div class="row">
                    <div class="form-group">
                        <label for="cust_id">Customer ID</label>
                        <input type="text" name="cust_id" id="cust_id" 
                        
                        <%
                        	String originStr = request.getParameter("origin");
                        	boolean origin = Boolean.parseBoolean(originStr);
                        		
                        	if(!origin){
	                        	String customerId = request.getParameter("cust_id");
	                        	if(customerId != null){
	                        		out.println("value=\""+customerId+"\"");
	                        	}                        		
                        	} else if(originStr != null && origin) {
                        		//Process account creation
                        		String bankCode = request.getParameter("bankCode");
                        		int branchCode = Integer.parseInt(request.getParameter("branchCode"));
                        		accountType accountTypex = accountType.valueOf(request.getParameter("accountType"));
                        		String cust_id = request.getParameter("cust_id");
                        		new AccountDAO().create((new BranchMap(bankCode, branchCode)).openNewAccount(accountTypex, 0f, cust_id));
                        		new CustomerDAO().setRegistered(cust_id);
                        	}
                        %>
                        
                        
                         />
                        <button class="btn btn-default fetch-customer-btn">Fetch Customer</button>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="form-group">
                        <label for="title" class="label-control">Title</label>
                        <select name="title" id="title" class="form-control select-control select">
                            <option value="Mr">Mr</option>
                            <option value="Ms">Ms</option>
                            <option value="Mrs">Mrs</option>
                            <option value="Dr">Dr</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="label-control">First Name</label>
                        <input type="text" name="firstname" id="firstname" class="form-control" style="padding:10px;">
                    </div>

                    <div class="form-group">
                        <label for="lastname" class="label-control">Last Name</label>
                        <input type="text" name="lastname" id="lastname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="dob" class="label-control">Date of Birth</label>
                        <input type="text" name="dob" id="dob" placeholder="yyyy-mm-dd" class="form-control">
                    </div>
                    <hr/>
                    <br/>

                </div>

        </div>
        <br />
        <hr />
        <br />
        
        <div class="row col-md-offset-2 col-md-10">        
    	 <div class="form-group col-md-5">
                <label for="bankCode">Select Bank</label>
                <select class="select-control form-control" name="bankCode" id="bankCode">
                    <%
					boolean flag = false;
               		List<Bank> banks = (new BankDAO()).findAll();
               		try{           			
    	        		for(Bank bank : banks){
    	        			if(bank == null)
    	        				break;
    	        			out.print("<option value=\"" + bank.getBankCode() + "\" "+ (flag?"":"selected") +">");
    	        			flag = true;
								out.print(bank);    	       
    	        			out.print("</option>");
    	        		}
               		}catch(NullPointerException e){
               			out.println("<tr><td colspan=5>No banks available</td></tr>");
               		}
                    %>
                </select>
            </div>

			<div class="form-group col-md-3">
                <label for="branchCode">Select Branch</label>
                <select class="select-control form-control" name="branchCode" id="branchCode">
                  
                </select>
            </div>
            
            <div class="form-group col-md-3">
                <label for="accountType">Select Branch</label>
                <select class="select-control form-control" name="accountType" id="accountType">
                  <option value="SAVINGS" selected>SAVINGS</option>
                  <option value="CURRENT">CURRENT</option>
                </select>
            </div>
            
               <input type="hidden" name="origin" value="true" />
	        <div class="form-group">
	            <button class="btn btn-success btn-md" id="account-form-submit" type="submit">Create Account</button>
	        </div>
        </div>
        
        </form>


        <div class="row" style="margin-top:120px;"></div>
        <hr/>
        <div class="footer">
            Wish you happy banking
        </div>
        <br />
        <br />
        <br />
    </div>
</body>



<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
    crossorigin="anonymous"></script>

<script>

	getBranch = function(){
		console.log($('#bankCode').val());
		
		$.ajax({
			url : "get-branch-by-bank-code.do",
			method: "GET",
			data: {"bankCode":$('#bankCode').val()},
			success: function(data){
				data = JSON.parse(data);
				$('#branchCode').html("");
				$('#branchCode').append(data.data);
			}
		})	
	}
	
	getBranch();
	
	$('#bankCode').on('change', function(e){
		getBranch();
	})


    $('.fetch-customer-btn').on('click', function (e) {
        e.preventDefault();
        $('.error').css('display', 'none');
        $('.success').css('display', 'none');

        $.ajax({
            url: 'fetch-customer.do',
            method: 'GET',
            data: $('#account-form').serialize(),
            success: function (data) {
            	console.log(data);
            	data = JSON.parse(data);
                $('#firstname').val(data.firstname);
                $('#lastname').val(data.lastname);
                $('#title').val(data.title);
                $('#dob').val(data.dob);
                $('.success').html(data.message);
                $('.success').css('display', 'block');

            },
            error: function (error) {
                console.log(error);
                if(error.status == 400)
                {
                	error.responseText = JSON.parse(error.responseText);
                    $('.error').html(error.responseText.message);    
                }
                // $('.error').html(error.responseText.message);
                $('.error').css('display', 'block');

            }
        })
    });


</script>

</html>