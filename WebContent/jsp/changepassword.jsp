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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration/registration.css"> 
	</head>
  <body>
    <div class="container">
      <c:import url="common/header.jsp"/>
      <div class="inform">
      	<div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-7">
                    <div class="panel panel-default">
                        <div class="panel-heading"><span class="glyphicon glyphicon-lock"></span> <fmt:message key="user.change.password"/></div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="/Roulette/Controller" method="post">
                              <div class="form-group">
                                  <label for="name" class="col-sm-3 control-label"><fmt:message key="user.change.password.old"/></label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" id="oldpassword" name="oldpassword" placeholder=<fmt:message key="user.password"/> required>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="login" class="col-sm-3 control-label"><fmt:message key="user.change.password.new"/></label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" name="newpassword" id="newpassword" placeholder=<fmt:message key="user.password"/> required>
                                  </div>
                              </div>
                              <div class="form-group last">
                                  <div class="col-sm-offset-3 col-sm-9">
                                      <button type="submit" class="btn btn-success btn-sm"><fmt:message key="user.change"/></button>
                                      <button type="reset" class="btn btn-default btn-sm"><fmt:message key="user.reset"/></button>
                                  </div>
                              </div>
                              <input type="hidden" name="command" value="changepassword">
                            </form>
                        </div>
                    </div>
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