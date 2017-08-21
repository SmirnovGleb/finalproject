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
	href="${pageContext.request.contextPath}/css/listplayers/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/listplayers/listplayers.css">
</head>
<body>
	<div class="container">
		<c:import url="common/headeradmin.jsp" />
		<div class="inform">
			<div class="container">
				<table id="example" class="display" cellspacing="0" width="100%">
					<thead class="updown">
						<tr>
							<th><fmt:message key="bet.id" /></th>
							<th><fmt:message key="user.name" /></th>
							<th><fmt:message key="user.login" /></th>
							<th><fmt:message key="user.money" /></th>
							<th><fmt:message key="user.email" /></th>
							<th><fmt:message key="user.blocked" /></th>
							<th>Lock Operations</th>
						</tr>
					</thead>
					<tfoot class="updown">
						<tr>
							<th><fmt:message key="bet.id" /></th>
							<th><fmt:message key="user.name" /></th>
							<th><fmt:message key="user.login" /></th>
							<th><fmt:message key="user.money" /></th>
							<th><fmt:message key="user.email" /></th>
							<th><fmt:message key="user.blocked" /></th>
							<th>Lock Operations</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="player" items="${listplayers}">
							<tr>
								<td>${player.id}</td>
								<td>${player.name}</td>
								<td>${player.login}</td>
								<td>${player.money}</td>
								<td>${player.email}</td>
								<td></td>
								<td>
									<form id="blockform" action="/Roulette/Controller"
										method="post">
										<select select name="daylock" onchange="submit()">
											<option disabled selected="selected">Days</option>
											<option value="1">1</option>
											<option value="7">7</option>
											<option value="30">30</option>
											<option value="90">90</option>
											<option value="180">180</option>
											<option value="360">360</option>
											<option value="18300">18300</option>
										</select> <input type="hidden" name="command" value="lockplayer">
										<input type="hidden" name="lockbyid" value=${player.id}>
									</form>
								</td>
							</tr>
						</c:forEach>
						<c:forEach var="player" items="${maplockedplayers}">
							<tr>
								<td>${player.key.id}</td>
								<td>${player.key.name}</td>
								<td>${player.key.login}</td>
								<td>${player.key.money}</td>
								<td>${player.key.email}</td>
								<td>${player.value}</td>
								<td>
									<form id="blockform" action="/Roulette/Controller"
										method="post">
										<input type="hidden" name="command" value="unlockplayer">
										<input type="hidden" name="lockbyid" value=${player.key.id}>
										<button type="submit" id="submitunblock">Разблокировать</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:import url="common/footer.jsp" />
	</div>
	<div class="clearfix"></div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		/* API method to get paging information */
		'use strict';
		var $ = jQuery;
		$
				.getScript(
						"${pageContext.request.contextPath}/js/listplayers/datatables.min.js",
						function() {
							$('#example').DataTable({
								"paging" : true,
								"ordering" : true,
								"info" : false
							});
						});
	</script>
</body>
</html>