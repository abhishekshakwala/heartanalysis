<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heart Analysis- Register</title>
</head>
<body>
<div class="center">
<h4>Please wait...</h4>
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
int age = Integer.parseInt(request.getParameter("age"));
int sex = Integer.parseInt(request.getParameter("gender"));
try {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartdb", "root", "Mysuccess@26");
	java.sql.Statement st = con.createStatement();
	st.executeUpdate("insert into persondetails (username, password, age, sex) values('"+ username +"', '"+ password +"', "+ age +", "+ sex +")");
	session.setAttribute("username", username);
	response.sendRedirect("userHomepage.jsp");
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