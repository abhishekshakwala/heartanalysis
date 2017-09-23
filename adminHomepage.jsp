<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heart Analysis - Admin Homepage</title>
</head>
<body>
<div class="center">
<% String username = session.getAttribute("username").toString(); 
if(username == null){
	response.sendRedirect("admin.html");
} else{
%>
<div align="right"><a href="logout.jsp"><img src="C:\Users\avs23\Desktop\Neon\HeartAnalysis\logout.jpg" height="50" width="50"></a></div>
<% } %>
<h1 class="Heading">Welcome <%= username %></h1>
</br>
<a href="addTrainingData.jsp">Add Training Data</a></br>
<a href="addSmokingData.jsp">Add Smoking History Data</a></br>
<a href="viewTrainingData.jsp">View Data</a></br>
</div>
</body>
</html>