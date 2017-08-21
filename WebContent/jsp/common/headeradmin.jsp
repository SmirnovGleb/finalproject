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
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/jsp/regorlogin.jsp"><fmt:message
					key="header.europeanroulette" /></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-9">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/jsp/administrator.jsp"><fmt:message
							key="user.account" /></a></li>
				<li><a
					href="${pageContext.request.contextPath}/jsp/strategy.jsp"><fmt:message
							key="header.strategyofgame" /></a></li>
				<li><a href="${pageContext.request.contextPath}/jsp/rules.jsp"><fmt:message
							key="header.rules" /></a></li>
				<li><a
					href="${pageContext.request.contextPath}/Controller?command=logout"><fmt:message
							key="user.logout" /></a></li>
				<li>
					<div class="sele">
						<form method="post" class="selectlang"
							action="/Roulette/Controller">
							<select select class="form-control" id="language" name="language"
								onchange="submit()">
								<option disabled selected="selected"><fmt:message
										key="language.lang" /></option>
								<option value="en"><fmt:message key="language.english" /></option>
								<option value="ru"><fmt:message key="language.russian" /></option>
							</select> <input type="hidden" name="command" value="language">
						</form>
					</div>
				</li>
			</ul>
		</div>
	</div>
	</nav>
</body>
</html>