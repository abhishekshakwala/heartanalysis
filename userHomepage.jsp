<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Homepage</title>
</head>
<body>
<div class="center">
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<% String username = session.getAttribute("username").toString(); %>
<div align="right"><a href="logoutUser.jsp"><img src="C:\Users\avs23\Desktop\Neon\HeartAnalysis\logout.jpg" height="50" width="50"></a></div>
<h1 class="Heading">Welcome <%= username %></h1>
</br>
<h2>Previous Heart Status:</h2><%
try {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartdb", "root", "Mysuccess@26");
	java.sql.Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select heartnum from persondetails where username = '" + username+ "'");
	if(rs.next()){
		int previousresult = Integer.parseInt(rs.getString("heartnum"));
		if(previousresult == 1){
			out.println("<h3>You had Heart Disease on your previous visit.</h3>");
		}else if(previousresult == 0){
			out.println("<h3>You didn't had heart disease previously. You must have enjoyed.</h3>");
		}else{
			out.println("<h3>Sorry your heart is being analysed for the first time by our application.</h3>");
		}
	} else{
		out.println("<h3>Sorry your heart is being analysed for the first time by our application.</h3>");
	}
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e){
	e.printStackTrace();
}
%>
</br>
<h2>Test Details:</h2>
<form method="post" action="checkHeart.jsp">
<h4>Chest Pain Type:</h4>
<select name="cp">
	<option value="1">typical angina</option>
	<option value="2">atypical angina</option>
	<option value="3">non-anginal pain</option>
	<option value="4">asymptomatic</option>
</select></br>
<h4>Resting Blood Pressure:</h4>
<input type="text" name="trestbps"></br>
<h4>Serem Cholestoral:</h4>
<input type="text" name="chol"></br>
<h4>Fasting Blood Sugar:</h4>
<select name="fbs">
	<option value="1">FBS > 120 mg/dl</option>
	<option value="0">FBS < 120 mg/dl</option>
</select></br>
<h4>Resting Electrocardiographic Result:</h4>
<select name="restecg">
	<option value="0">normal</option>
	<option value="1">ST-T wave abnormality</option>
	<option value="2">Left ventricular hypertrophy</option>
</select></br>
<h4>Maximum Heart rate achieved:</h4>
<input type="text" name="thalach"></br>
<h4>Exercise Induced angina:</h4>
<select name="exang">
	<option value="1">Yes</option>
	<option value="0">No</option>
</select></br>
<h4>ST depression induced:</h4>
<input type="text" name="oldpeak"></br>
<h4>Slope of the peak exercise:</h4>
<select name="slope">
	<option value="1">Unsloping</option>
	<option value="2">Flat</option>
	<option value="2">Downsloping</option>
</select></br>
<h4>Number of major vessels:</h4>
<select name="ca">
	<option value="0">0</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
</select></br>
<h4>Thal:</h4>
<select name="thal">
	<option value="3">Normal</option>
	<option value="6">Fixed Defect</option>
	<option value="7">Reversable defect</option>
</select></br></br>
<h2><%= username %>'s History:</h2></br>
<h4>Cigarettes per day:</h4>
<input type="text" name="cigs"></br>
<h4>Number of years as smoker:</h4>
<input type="text" name="smokeyears"></br>
<h4>History of Diabetes:</h4>
<select name="histDiabetes">
	<option value="1">Yes</option>
	<option value="0">No</option>
</select></br>
<h4>Family History(Coronary Artery disease):</h4>
<select name="familyHistory">
	<option value="1">Yes</option>
	<option value="0">No</option>
</select></br>
<h4>Duration of Exercise(in minutes):</h4>
<input type="text" name="thaldur"></br>
<h4>Months of Exercise:</h4>
<input type="text" name="ekgmo"></br>
<h4>Days of Exercise:</h4>
<input type="text" name="ekgday"></br>
</br>
<input type="submit" value="Check Heart">
</form>
</div>
</body>
</html>