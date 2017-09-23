<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heart Analysis - User</title>
</head>
<body>
<div class="center">
<h4>Please wait...</h4>
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
session.setAttribute("username", username);
System.out.println(username + " " + password);
try {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartdb", "root", "Mysuccess@26");
	java.sql.Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from persondetails where username = '" + username+ "'");
	if(rs.next()){
		if(rs.getString("password").equals(password)){
			response.sendRedirect("userHomepage.jsp");
		} else{
			response.sendRedirect("users.html");
		}
	} else{
		response.sendRedirect("users.html");
	}
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e){
	e.printStackTrace();
}
%>
</div>
</body>
</html>