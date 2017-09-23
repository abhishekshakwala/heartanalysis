<%@page import="com.classify.BigDataProject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Smoking Data</title>
</head>
<body>
<%@ page import = "java.io.*" %>
<%
String saveFile = "";
String contentType = request.getContentType();
if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
	DataInputStream in = new DataInputStream(request.getInputStream());
	int formDataLength = request.getContentLength();
	byte dataBytes[] = new byte[formDataLength];
	int byteRead = 0;
	int totalBytesRead = 0;
	while (totalBytesRead < formDataLength) {
		byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
		totalBytesRead += byteRead;
	}
	String file = new String(dataBytes);
	saveFile = file.substring(file.indexOf("filename=\"") + 10);
	saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
	saveFile = saveFile.substring(0,saveFile.length() - 2);
	System.out.println(saveFile);
	BigDataProject.csvupload(null, saveFile);
	response.sendRedirect("adminHomepage.jsp");
}
%>
</body>
</html>