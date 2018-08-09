<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>State Bank of India</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
		<style>
			html{
				width: 100%;
				height: 100%;
				margin: 0;
				padding: 0;
			}
			body{
				width: 100%;
				height: 100%;
				margin: 0;
				padding: 0;
			}
			.container{
				min-height: 100%;
				box-sizing: border-box;
				border: 1px solid #CCCCCC;
				padding: 0 !important;
				position: relative;
				overflow-y: auto;
				overflow-x: hidden;
			}
			nav{
				width: 100%;
				background-color: #f2f2f2 !important;
			}
			img.img-fluid{
				height: 20px;
				margin-right: 20px;
			}
			div.footer{
				width: 100%;
				background-color: #f2f2f2;
				position: absolute;
				bottom: 0px;
				left: 0px;
				padding-top: 20px;
				padding-bottom: 20px;
			}
			li.nav-item{
				padding-left: 10px;
				padding-right: 10px;
			}
			#profile{
				color: rgba(0, 0, 0, 1);
			}
			#transfer, #new_acc, #home{
				color: rgba(0, 0, 0, 0.5);
			}
			div.main_row{
				padding: 40px;
			}
			img#placeholder{
				width: 150px;
				height: 150px;
			}
			div.profile_container{
				box-sizing: border-box;
				margin-left: 20px;
			}
			div.profile_row{
				box-sizing: border-box;
				padding: 20px;
				border-bottom: 1px solid rgba(0, 0, 0, 0.125);
			}
			div.col-heading{
				font-weight: bold;
			}
		</style>
		<script src="jquery-3.3.1.min.js"></script>
		<script src="popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	</head>
	<body>
		<div class="container">
			<nav class="navbar navbar-expand-lg navbar-light">
				<a class="navbar-left" href="#"><img src="logo.png" class="img-fluid" alt="Responsive image"></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active">
							<a class="nav-link" href="home.html" id="home">Home</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#" id="profile">My Profile</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#" id="new_acc">New Account</a>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" id="payment">
								Payment & Transfer
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">Fund Transfer</a>
								<a class="dropdown-item" href="#">Pay Bills</a>
								<a class="dropdown-item" href="#">Recharge</a>
							</div>
						</li>
					</ul>
				</div>
			</nav>
			<div class="row main_row">
				<div class="col col-xl-2">
					<img class="img-thumbnail" src="user_placeholder.png" alt="Thumbnail image" id="placeholder">
				</div>
				<div class="col col-xl-9 profile_container">
					<div class="row profile_row">
						<div class="col col-xl-3 col-heading">Name</div>
						<div class="col col-xl-9">Amar Ashok Pawar</div>
					</div>
					<div class="row profile_row">
						<div class="col col-xl-3 col-heading">Address</div>
						<div class="col col-xl-9">Kandivali East, Mumbai</div>
					</div>
					<div class="row profile_row">
						<div class="col col-xl-3 col-heading">Mobile No</div>
						<div class="col col-xl-9">8286454274</div>
					</div>
					<div class="row profile_row">
						<div class="col col-xl-3 col-heading">Email Id</div>
						<div class="col col-xl-9">pawaramar58@gmail.com</div>
					</div>
					<div class="row profile_row">
						<div class="col col-xl-3 col-heading">PAN Number</div>
						<div class="col col-xl-9">ABCD12345</div>
					</div>
					<div class="row profile_row">
						<div class="col col-xl-3 col-heading">Aadhar Number</div>
						<div class="col col-xl-9">12345678912345</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<div class="footer-copyright text-center py-3">&copy; 2018 Copyright:
					<a href="#">Amar Pawar</a>
				</div>
			</div>
		</div>
	</body>
</html>