<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heart Analysis - View Training Data</title>
</head>
<body>
<div class="center">
<a href="adminHomepage.jsp">Goto HomePage</a><div align="right"><a href="logout.jsp"><img src="C:\Users\avs23\Desktop\Neon\HeartAnalysis\logout.jpg" height="50" width="50"></a></div>

<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%
String username = session.getAttribute("username").toString();
int count = 0;
try {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartdb", "root", "Mysuccess@26");
	java.sql.Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from persontest");
	while(rs.next()){
		if(count == 0){
			count++;
%>
<table align="center" cellpadding="4" cellspacing="4">
<tr bgcolor='008000'>
<td><b>ID</b></td>
<td><b>CP</b></td>
<td><b>TRESTBPS</b></td>
<td><b>CHOL</b></td>
<td><b>FBS</b></td>
<td><b>RESTECG</b></td>
<td><b>THALACH</b></td>
<td><b>EXANG</b></td>
<td><b>OLDPEAK</b></td>
<td><b>SLOPE</b></td>
<td><b>CA</b></td>
<td><b>THAL</b></td>
<td><b>HEARTNUM</b></td>
<tr bgcolor="#8FBC8F">
<% }%>
<td><%=rs.getString("id")%></td>
<td><%=rs.getString("cp")%></td>
<td><%=rs.getString("trestbps")%></td>
<td><%=rs.getString("chol")%></td>
<td><%=rs.getString("fbs")%></td>
<td><%=rs.getString("restecg")%></td>
<td><%=rs.getString("thalach")%></td>
<td><%=rs.getString("exang")%></td>
<td><%=rs.getString("oldpeak")%></td>
<td><%=rs.getString("slope")%></td>
<td><%=rs.getString("ca")%></td>
<td><%=rs.getString("thal")%></td>
<td><%=rs.getString("num")%></td>

</tr>
	
<% 		
	}
	if(count == 0){
		out.println("No Training Data. Goto admin homepage to add.");
	}
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e){
	e.printStackTrace();
}
%>
</table>
</div>
</body>
</html>