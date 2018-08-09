<%@page import="java.util.List"%>
<%@page import="com.zycus.banking.customer.Customer"%>
<%@page import="com.zycus.banking.customer.CustomerDAO"%>
<%@page import="java.sql.Date"%>
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
        
        .reg{        
        	color: green;
        }
        
        .unreg{
        	color: red;
        }
        
    </style>
    <title>Unregisterd Customers | XYZ Bank</title>
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
        <div class="row table-responsive">
        <table class="table ">
        	<thead>
        		<tr>
        			<th>Customer ID</th>
        			<th>Title</th>
        			<th>Firstname</th>
        			<th>Lastname</th>
        			<th>Date of Birth</th>
        			<th>Registration Status</th>
        			<th>Options</th>
        		</tr>
        	</thead>
        	<tbody>
        	
           <%
           
           		List<Customer> customers = new CustomerDAO().findUnregistered();
           		try{           			
	        		for(Customer customer : customers){
	        			if(customer == null)
	        				break;
	        			out.println("<tr>");
	        			out.println("<td>"+ customer.getId() +"</td>");
	        			out.println("<td>"+ customer.getTitle() +"</td>");
	        			out.println("<td>"+ customer.getFirstName() +"</td>");
	        			out.println("<td>"+ customer.getLastName() +"</td>");
	        			out.println("<td>"+ customer.getDob() +"</td>");
	        			out.println("<td class="+ (customer.getRegStatus()?"reg": "unreg") + "><strong>"+ (customer.getRegStatus()?"REGISTERED": "UNREGISTERED") +"</strong></td>");
	        			out.println("<td>" + (customer.getRegStatus()?"<button class=\"btn btn-default\">View Customer</button>": "<a href=\"create-account.jsp?cust_id="+ customer.getId() +"\"><button class=\"btn btn-success\">Register</button></a>") + "</td>");
	        			out.println("</tr>");
	        		}
           		}catch(NullPointerException e){
           			out.println("<tr><td colspan=5>No unregistered customer found</td></tr>");
           		}
           	
           %>
        	</tbody>
        </table> 
        </div>
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

</html>