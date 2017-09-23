<%@page import="com.classify.LoadAndSave"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heart Status</title>
</head>
<body>
<div class="center">
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<% 
String username = session.getAttribute("username").toString();
double cp = Integer.parseInt(request.getParameter("cp"));
double trestbps = Integer.parseInt(request.getParameter("trestbps"));
double chol = Integer.parseInt(request.getParameter("chol"));
double fbs = Integer.parseInt(request.getParameter("fbs"));
double restecg = Integer.parseInt(request.getParameter("restecg"));
double thalach = Integer.parseInt(request.getParameter("thalach"));
double exang = Integer.parseInt(request.getParameter("exang"));
double oldpeak = Float.parseFloat(request.getParameter("oldpeak"));
double slope = Integer.parseInt(request.getParameter("slope"));
double ca = Integer.parseInt(request.getParameter("ca"));
double thal = Integer.parseInt(request.getParameter("thal"));
double cigs = Integer.parseInt(request.getParameter("cigs"));
double smokeyears = Integer.parseInt(request.getParameter("smokeyears"));
double histDiabetes = Integer.parseInt(request.getParameter("histDiabetes"));
double familyHistory = Integer.parseInt(request.getParameter("familyHistory"));
double thaldur = Float.parseFloat(request.getParameter("thaldur"));
double ekgmo = Integer.parseInt(request.getParameter("ekgmo"));
double ekgday = Integer.parseInt(request.getParameter("ekgday"));
int heartnum = LoadAndSave.classifyall(cp, trestbps, chol, fbs, restecg, thalach, exang, oldpeak, slope, ca, thal);
String check = LoadAndSave.preventheartdisease(cp, trestbps, chol, fbs, restecg, thalach, exang, oldpeak, slope, ca, thal, cigs, smokeyears, thaldur, ekgmo, familyHistory, histDiabetes, heartnum);
try {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartdb", "root", "Mysuccess@26");
	java.sql.Statement st = con.createStatement();
	st.executeUpdate("update persondetails SET heartnum = " + heartnum + " where username = '"+ username +"'");
	if(heartnum == 1){%>
		<div align="right"><a href="logoutUser.jsp"><img src="C:\Users\avs23\Desktop\Neon\HeartAnalysis\logout.jpg" height="50" width="50"></a></div>
		<%out.println("<h2>You have Heart Disease.</h2>");
		out.println("<h2>" + check + "</h2>");
	} else{%>
	<div align="right"><a href="logoutUser.jsp"><img src="C:\Users\avs23\Desktop\Neon\HeartAnalysis\logout.jpg" height="50" width="50"></a></div>
	<%
		out.println("<h2>Keep Enjoying!! You don't have Heart Disease.</h2>");
		out.println("<h2>" + check + "</h2>");
	}
	//response.sendRedirect("index.html");
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