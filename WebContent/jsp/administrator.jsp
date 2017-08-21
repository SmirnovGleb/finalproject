<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/administrator/administrator.css">
</head>
<body>
	<div class="container">
		<c:import url="common/headeradmin.jsp" />
		<div class="inform">
			<div class="container">
				<div class="row">
					<div class="allinform3">
						<div class="col-md-3 col-md-offset-5" id="userinfo">
							<h3 class="textuser">${user.name}</h3>
							<h5>
								<br>
								<fmt:message key="admin.menu" />
							</h5>
						</div>
					</div>
				</div>
				<div class="col-md-9 col-md-offset-3" id="userchoice">
					<div class="row">
						<br />
					</div>
					<div class="row">
						<div class="col-md-3 text-center">
							<a href="${pageContext.request.contextPath}/jsp/percent.jsp"
								class="btn btn-primary btn-lg" id="togame" role="button"><fmt:message
									key="admin.set.percent" /></a>
						</div>
						<div class="col-md-3 text-center">
							<a
								href="${pageContext.request.contextPath}/Controller?command=listplayers"
								class="btn btn-primary btn-lg" role="button"><fmt:message
									key="admin.listplayers" /></a>
						</div>
						<div class="col-md-3 text-center">
							<a
								href="${pageContext.request.contextPath}/Controller?command=allbets"
								class="btn btn-primary btn-lg" role="button"><fmt:message
									key="bet.info" /></a>
						</div>
					</div>
					<div class="row">
						<br />
					</div>
					<div class="row">
						<div class="col-md-3 text-center">
							<a
								href="${pageContext.request.contextPath}/Controller?command=messages"
								class="btn btn-primary btn-lg " role="button"><fmt:message
									key="user.message" /></a>
						</div>
						<div class="col-md-3 text-center">
							<a href="${pageContext.request.contextPath}/jsp/changeemail.jsp"
								class="btn btn-primary btn-lg " role="button"><fmt:message
									key="user.change.email" /></a>
						</div>
						<div class="col-md-3 text-center">
							<a
								href="${pageContext.request.contextPath}/jsp/changepassword.jsp"
								class="btn btn-primary btn-lg" role="button"><fmt:message
									key="user.change.password" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:import url="common/footer.jsp" />
	</div>
	<div class="clearfix"></div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</body>
</html>