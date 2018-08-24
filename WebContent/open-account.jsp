<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "open-account";%>
<%!String role = "default";%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->

<div class="row">
	<span class="error"></span> <span class="success"></span> <br />
	<div class="form-inline col-md-12">
		<form id="account-form" action="new-account.do"
			class="form col-md-12" method="POST">
			<div class="row">
			<div class="form-group col-md-3">
				<label for="title" class="label-control">Title</label> <select
					name="title" id="title" class="form-control">
					<option value="Mr">Mr</option>
					<option value="Ms">Ms</option>
					<option value="Mrs">Mrs</option>
					<option value="Dr">Dr</option>
				</select>
			</div>
			<div class="form-group col-md-3">
				<label for="firstname" class="label-control">First Name</label> <input
					type="text" name="firstname" id="firstname" class="form-control"
					style="padding: 10px;">
			</div>

			<div class="form-group col-md-3">
				<label for="lastname" class="label-control">Last Name</label> <input
					type="text" name="lastname" id="lastname" class="form-control">
			</div>
			<div class="form-group col-md-3">
				<label for="dob" class="label-control">Date of Birth</label> 
				<input type="date" name="dob" id="dob" placeholder="yyyy-mm-dd"
					class="form-control">
			</div>
			</div>
			<div class="form-group col-md-12">
				<button class="btn btn-success" id="account-form-submit"
					type="submit">Create Account</button>
			</div>
		</form>
	</div>

</div>


<script>

    $('#account-form-submit').on('click', function (e) {
        e.preventDefault();
        $('.error').css('display', 'none');
        $('.success').css('display', 'none');

        $.ajax({
            url: "/new-account.do",
            method: "POST",
            data: $("#account-form").serialize(),
            success: function (data) {
                console.log(data);
                data = JSON.parse(data);
                $('.success').html(data.message);
                $('.success').css('display', 'block');
            },
            error: function (error) {
                console.log(error); 
                error.responseText = JSON.parse(error.responseText);
                console.log(error); 
                $('.error').html(error.responseText.message);
                $('.error').css('display', 'block');
            }
        })

    })


</script>

<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
