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
      <c:import url="common/headerwithoutuser.jsp"/>
      <div class="inform">
      	<div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-7">
                    <div class="panel panel-default">
                        <div class="panel-heading"><span class="glyphicon glyphicon-registration-mark"></span> Registration</div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="myForm" action="/Roulette/Controller" method="post">
                              <div class="form-group">
                                  <label for="name" class="col-sm-3 control-label"><fmt:message key="user.name"/> *</label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" id="name" name="name" placeholder=<fmt:message key="user.name"/> required>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="login" class="col-sm-3 control-label"><fmt:message key="user.login"/> *</label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" name="login" id="login" pattern="\w{3,15}" placeholder=<fmt:message key="user.login"/> required>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="password1" class="col-sm-3 control-label"><fmt:message key="user.password"/> *</label>
                                  <div class="col-sm-9">
                                      <input type="password" class="form-control" name="password1" id="password1" placeholder=<fmt:message key="user.password"/> required>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="password2" class="col-sm-3 control-label"><fmt:message key="user.password"/> *</label>
                                  <div class="col-sm-9">
                                      <input type="password" class="form-control" name="password2" id="password2" placeholder=<fmt:message key="user.password"/> required>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="email" class="col-sm-3 control-label"><fmt:message key="user.email"/> *</label>
                                  <div class="col-sm-9">
                                      <input type="email" class="form-control" name="mail" id="email" placeholder=<fmt:message key="user.email"/> pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$" required>
                                  </div>
                              </div>
                             	<div class="form-group">
									<div class="col-sm-9">
										<div>${infoforguest}</div>
									</div>
								</div>
                              <div class="form-group last">
                                  <div class="col-sm-offset-3 col-sm-9">
                                      <button type="submit" class="btn btn-success btn-sm"><fmt:message key="user.register"/></button>
                                      <button type="reset" class="btn btn-default btn-sm"><fmt:message key="user.reset"/></button>
                                  </div>
                              </div>
                              <input type="hidden" name="command" value="registration">
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
    <script src="${pageContext.request.contextPath}/js/registration/registration.js"></script>
  </body>
</html>