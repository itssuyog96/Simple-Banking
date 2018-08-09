package com.zycus.banking.util;

import static java.lang.System.getProperty;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Template {

	private static JSONParser parser = new JSONParser();
	public static JSONObject obj;

	public static String getHeader(String title) {
		try {
			obj = (JSONObject) parser.parse(new FileReader(getProperty("user.home") + getProperty("file.separator")
					+ "web_workspace" + getProperty("file.separator") + "Banking" + getProperty("file.separator")
					+ "/json/title.json"));

			JSONArray objArray = (JSONArray) obj.get("pages");

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(
					"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
			strBuilder.append("<html>\r\n");
			strBuilder.append("<head>\r\n");
			strBuilder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
			strBuilder.append(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\r\n");
			strBuilder.append(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\r\n");
			strBuilder.append("<link rel=\"stylesheet\" href=\"../../style.css\" />\r\n");
			strBuilder.append("<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"></script>\r\n");
			strBuilder.append("<script src=\"https://unpkg.com/popper.js\"></script>\r\n");
			strBuilder.append(
					"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>");

			strBuilder.append("<title>");
			// Inject title
			for (Object object : objArray) {
				JSONObject jObject = (JSONObject) object;
				if (jObject.get("id").toString().equalsIgnoreCase(title.trim())) {
					strBuilder.append(jObject.get("name"));
					break;
				}
			}

			strBuilder.append("</title>\r\n");
			strBuilder.append("</head>\r\n");
			strBuilder.append("<body>\r\n");
			strBuilder.append("<div class=\"container\">\r\n");

			return strBuilder.toString();

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error occured!";
		}

	}

	public static String getMenu(String role, String page) {

		try {
			obj = (JSONObject) parser.parse(new FileReader(getProperty("user.home") + getProperty("file.separator")
					+ "web_workspace" + getProperty("file.separator") + "Banking" + getProperty("file.separator")
					+ "/json/title.json"));

			JSONArray objArray = (JSONArray) obj.get("menu");

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("<nav class=\"navbar navbar-expand-lg navbar-light\">\r\n");
			strBuilder.append(
					"				<a class=\"navbar-left\" href=\"#\"><img src=\"../../logo.png\" class=\"img-fluid\" alt=\"Responsive image\"></a>\r\n");
			strBuilder.append(
					"				<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
			strBuilder.append("					<span class=\"navbar-toggler-icon\"></span>\r\n");
			strBuilder.append("				</button>\r\n");
			strBuilder.append("\r\n");
			strBuilder.append(
					"				<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n");
			strBuilder.append("					<ul class=\"navbar-nav mr-auto\">\r\n");

			// Inject menu-items
			for (Object object : objArray) {
				JSONObject jObject = (JSONObject) object;
				if (jObject.get("role").toString().equalsIgnoreCase(role.trim())) {
					JSONArray menuItems = (JSONArray) jObject.get("menu-items");

					for (Object menuItem : menuItems) {
						JSONObject menuItemJSON = (JSONObject) menuItem;
						strBuilder.append("<li class=\"nav-item");
						if (menuItemJSON.get("id").toString().equals(page)) {
							strBuilder.append(" active ");
						}
						strBuilder.append("\">\r\n");
						strBuilder.append("<a class=\"nav-link\" href=\"" + menuItemJSON.get("url") + "\" id=\""
								+ menuItemJSON.get("id") + "\">" + menuItemJSON.get("name") + "</a>\r\n");
						strBuilder.append("</li>\r\n");
					}
					break;
				}
			}

			strBuilder.append("					</ul>\r\n");
			strBuilder.append("				</div>\r\n");
			strBuilder.append("			</nav>");

			return strBuilder.toString();

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error occured!";
		}
	}

	public static String getFooter() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("	<div class=\"footer\">\r\n");
		strBuilder.append("<div class=\"footer-copyright text-center py-3\">&copy; 2018 Copyright:\r\n");
		strBuilder.append("<a href=\"#\">Amar Pawar</a>\r\n");
		strBuilder.append("</div>\r\n");
		strBuilder.append("</div>\r\n");
		strBuilder.append("</div>\r\n");
		strBuilder.append("</body>\r\n");
		strBuilder.append("</html>");
		strBuilder.append("</body>\r\n");
		strBuilder.append("</html>");

		return strBuilder.toString();
	}

}
