<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text"/>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta charset="utf-8">  
	    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../css/login.css"> 
	</head>
  <body>
    <div class="container">
      <c:import url="common/headerwithoutuser.jsp"/>
      <div class="inform">
	      <div class="container">
	            <div class="col-md-8 col-md-offset-2" id="rulesrules">
	            	<fmt:message key="strategy.game"/>
	            </div>
	      </div>
      </div>
      <c:import url="common/footer.jsp"/>
    </div>   
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>