<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta charset="utf-8">  
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/credit/jquery-ui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/credit/credit.css">
	</head>
  <body>
    <div class="container">
      <c:import url="common/header.jsp"/>
      <div class="inform">
	    <div class="container">
	      <div class="price-box">
	        <form class="form-horizontal form-pricing" action="/Roulette/Controller" method="post" role="form">
	          <div class="price-slider">
	            <h4 class="great"><fmt:message key="credit.amount"/></h4>
	            <span><fmt:message key="credit.minmoney"/>   <h4>${infoforguest}</h4></span>
	            <div class="col-sm-12">
	              <div id="slider"></div>
	            </div>
	          </div>
	          <div class="price-slider">
	            <h4 class="great"><fmt:message key="credit.duration"/></h4>
	            <span><fmt:message key="credit.mindays"/></span>
	            <div class="col-sm-12">
	              <div id="slider2"></div>
	            </div>
	          </div>
	          <div class="price-form">
	            <div class="form-group">
	              <label for="amount" class="col-sm-6 control-label"><fmt:message key="credit.amount"/> ($): </label>
	              <span class="help-text"><fmt:message key="credit.chooseamount"/></span>
	              <div class="col-sm-6">
	                <input type="hidden" id="amount" name="amount" class="form-control">
	                <p class="price lead" id="amount-label"></p>
	                <span class="price">.00</span>
	              </div>
	            </div>
	            <div class="form-group">
	              <label for="duration" class="col-sm-6 control-label"><fmt:message key="credit.duration"/>: </label>
	              <span class="help-text"><fmt:message key="credit.choosecommitment"/></span>
	              <div class="col-sm-6">
	                <input type="hidden" id="duration" name="duration" class="form-control">
	                <p class="price lead" id="duration-label"></p>
	                <span class="price"><fmt:message key="credit.days"/></span>
	              </div>
	            </div>
	          </div>
	          <div class="form-group">
	            <div class="col-sm-12">
	              <button type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="credit.get"/></button>
	            </div>
	          </div>
	          <input type="hidden" name="command" value="takecredit">
	        </form>
	      </div>
	    </div>
      </div>
      <c:import url="common/footer.jsp"/>
    </div> 
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/credit/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/credit/credit.js"></script>
  </body>
</html>