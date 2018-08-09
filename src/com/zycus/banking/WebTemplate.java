package com.zycus.banking;

public class WebTemplate {
	
	private static String header = "<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\">\r\n" + 
			"\r\n" + 
			"<head>\r\n" + 
			"    <meta charset=\"UTF-8\">\r\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
			"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\"\r\n" + 
			"        crossorigin=\"anonymous\">\r\n" + 
			"    <title>%s | XYZ Bank</title>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"        <h2 class=\"title\">Iron Bank of Braavos</h2>\r\n" + 
			"\r\n" + 
			"        <hr>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"        <ul class=\"nav\">\r\n" + 
			"            <li></li>\r\n" + 
			"        </ul>\r\n" + 
			"        <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\r\n" + 
			"            \r\n" + 
			"            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\"\r\n" + 
			"                aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
			"                <span class=\"navbar-toggler-icon\"></span>\r\n" + 
			"            </button>\r\n" + 
			"\r\n" + 
			"            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n" + 
			"                <ul class=\"navbar-nav mr-auto\">\r\n" + 
			"                    <li class=\"nav-item active\">\r\n" + 
			"                        <a class=\"nav-link\" href=\"/Banking/\">Home\r\n" + 
			"                            <span class=\"sr-only\">(current)</span>\r\n" + 
			"                        </a>\r\n" + 
			"                    </li>\r\n" + 
			"                    <li class=\"nav-item\">\r\n" + 
			"                        <a class=\"nav-link\" href=\"new-account.html\">New Account</a>\r\n" + 
			"                    </li>\r\n" + 
			"                    <li class=\"nav-item\">\r\n" + 
			"                        <a class=\"nav-link\" href=\"pay-bills.html\">Pay Bills</a>\r\n" + 
			"                    </li>\r\n" + 
			"                    \r\n" + 
			"                </ul>\r\n" + 
			"            </div>\r\n" + 
			"        </nav>\r\n" + 
			"        \r\n" + 
			"        <br/>\r\n" + 
			"\r\n" + 
			"        <div class=\"row\">";
	private static String footer = "</div>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n" + 
			"    crossorigin=\"anonymous\"></script>\r\n" + 
			"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\"\r\n" + 
			"    crossorigin=\"anonymous\"></script>\r\n" + 
			"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\"\r\n" + 
			"    crossorigin=\"anonymous\"></script>\r\n" + 
			"\r\n" + 
			"</html>";
	
	
	public static String getHeaderFor(String title) {
		return String.format(header, title);
	}
	
	public static String getFooter() {
		return footer;
	}
}
