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
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"> 
	</head>
  <body>
    <div class="container">
      <c:import url="common/headerwithoutuser.jsp"/>
      <div class="inform">
      <div class="container">       
		<div class="col-md-8 col-md-offset-2" id="rulesregorlog">
			<div class="col-md-8 col-md-offset-7">
				<h1><fmt:message key="user.motivator1"/></h1>
				<p><fmt:message key="user.motivator2"/></p>
				<span>
				  <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="btn btn-primary btn-lg" role="button"><fmt:message key="user.login"/></a> 
				  <a href="${pageContext.request.contextPath}/jsp/registration.jsp" class="btn btn-primary btn-lg" role="button"><fmt:message key="user.register"/></a>
				</span>
			</div>
		</div>  
      </div>
      </div>
      <c:import url="common/footer.jsp"/>
    </div>   
    <div class="clearfix"></div>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  </body>
</html>