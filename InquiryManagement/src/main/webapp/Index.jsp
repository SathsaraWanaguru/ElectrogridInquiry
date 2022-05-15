<%@page import="model.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("inquiryNumber") != null) 
{ 
 Inquiry empObj = new Inquiry(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hididinquirysave") == "") 
 { 
 stsMsg = empObj.insertInquiry(request.getParameter("inquiryNumber"), 
 request.getParameter("inquiryName"), 
 request.getParameter(" inquiryArea"), 
 request.getParameter("inquiryPnumber"),
 request.getParameter(" inquiryMail")); 
 } 
else//Update----------------------
 { 
 stsMsg = empObj.updateInquiry(request.getParameter("hididinquirysave"), 
		 request.getParameter("inquiryNumber"), 
		 request.getParameter("inquiryName"), 
		 request.getParameter(" inquiryArea"), 
		 request.getParameter("inquiryPnumber"),
		 request.getParameter(" inquiryMail")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hididinquiryDelete") != null) 
{ 
 Inquiry inqObj = new Inquiry(); 
 String stsMsg = 
 inqObj.deleteInquiry(request.getParameter("hididinquiryDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Inquiry.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Inquiry Management </h1>
<form id="formInq" name="formEmp">
 Inquiry Number: 
 <input id="inquiryNumber" name="inquiryNumber" type="text" 
 class="form-control form-control-sm">
 <br> Inquiry Name: 
 <input id="inquiryName" name="inquiryName" type="text" 
 class="form-control form-control-sm">
 <br>InquiryArea: 
 <input id="inquiryArea" name="inquiryArea" type="text" 
 class="form-control form-control-sm">
 <br> Inquiry Phone Number: 
 <input id="inquiryPnumber" name="inquiryPnumber" type="text" 
 class="form-control form-control-sm">
 <br> inquiryMail: 
 <input id="inquiryMail" name="inquiryMail" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hididinquirySave" 
 name="hididinquirySave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divInquiryGrid">
 <%
 Employee empObj = new Inquiry(); 
 out.print(empObj.readInquiry()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
