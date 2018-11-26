
    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BuyInsurance</title>
</head>
<body>
<form action="vehicleinfo"   >


<h2>Buy Insurance</h2>
</div><br>
<br><br>
Vehicle Type&nbsp<select name="vehicle_type">
<option value="2W">2-Wheeler</option>
<option value="4W">4-Wheeler</option>
</select><br><br>


Company/Manufactutrer&nbsp<select name="vehicle_company">
<c:forEach var="r" items="${company_list}">   
 <option value="${r}">${r}</option>  
   </c:forEach>
</select><br><br>

Vehicle Model&nbsp<select name="vehicle_model">
<c:forEach var="m" items="${model_list}">   
 <option value="${m}">${m}</option>  
   </c:forEach>
</select><br><br>



Zone/Location &nbsp<select name="zone">
<option value="1">ZONE A-Ahmedabad, Bangalore, Chennai,  Hyderabad , Kolkata, Mumbai, New Delhi and Pune</option>
<option value="2">ZONE B-Rest of India</option>
</select><br><br>

Purchase Date:&nbsp<input type="date" name="vehicle_purchase_date" value="25-Nov-2016"  required /><br><br>

Policy Type &nbsp<select name="policy">
<option value="3PL">Thrid Party Liability Policy</option>
<option value="COMP">Comprehensive Policy</option>
</select><br><br>


<div id="submit"><input type="submit" Value="Submit" /></div>
</div>
</center>
</form>
</body>
</html>