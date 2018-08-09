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
        padding-left:25%;
            color: red;
        }

        .success {
        padding-left:25%;
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
        <div class="row col-md-offset-2 col-md-10">
            <form id="account-form" action="new-account.do" class="form-inline col-md-12" method="POST">
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
                    <div class="form-group mb-2">
                        <hr>
                        <button class="btn btn-success btn-md" id="account-form-submit" type="submit">Create Account</button>
                    </div>

                </div>
            </form>

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

<script>

    $('#account-form-submit').on('click', function (e) {
        e.preventDefault();
        $('.error').css('display', 'none');
        $('.success').css('display', 'none');

        $.ajax({
            url: "new-account.do",
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

</html>