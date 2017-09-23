<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heart Analysis - Add Smoking Data</title>
</head>
<body>
<div class="center">
<h1 class="Heading">Add Smoking History Data</h1>
</br>
<form enctype="multipart/form-data" method="post" action="addSmokingDataProcess.jsp">
<center>
<table border="2" >
<tr><td colspan="2"><p align="center"><B>UPLOAD THE FILE</B><center></td></tr>
<tr><td><b>Choose the file To Upload:</b></td>
<td><input type="file" name="filename"></td></tr>
<tr><td colspan="2"><p align="right"><input type="submit" value="Upload File" ></p></td></tr>
</table>
</form>
</div>
</body>
</html>