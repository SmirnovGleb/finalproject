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
	href="${pageContext.request.contextPath}/css/registration/registration.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/listbets/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/listbets/listbets.css">
</head>
<body>
	<div class="container">
		<c:import url="common/header.jsp" />
		<div class="inform">
			<div class="container">
				<table id="example" class="display" cellspacing="0" width="100%">
					<thead class="updown">
						<tr>
							<th><fmt:message key="bet.id" /></th>
							<th><fmt:message key="bet.bet.on" /></th>
							<th><fmt:message key="bet.money" /></th>
							<th><fmt:message key="bet.result" /></th>
							<th><fmt:message key="bet.amount" /></th>
							<th><fmt:message key="bet.date" /></th>
						</tr>
					</thead>
					<tfoot class="updown">
						<tr>
							<th><fmt:message key="bet.id" /></th>
							<th><fmt:message key="bet.bet.on" /></th>
							<th><fmt:message key="bet.money" /></th>
							<th><fmt:message key="bet.result" /></th>
							<th><fmt:message key="bet.amount" /></th>
							<th><fmt:message key="bet.date" /></th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="bet" items="${listbets}">
							<tr>
								<td>${bet.id}</td>
								<td>${bet.betOn}</td>
								<td>${bet.money}</td>
								<td>${bet.result}</td>
								<td>${bet.winAmount}</td>
								<td>${bet.date}</td>
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
		'use strict';
		var $ = jQuery;
		$
				.getScript(
						"${pageContext.request.contextPath}/js/listbets/datatables.min.js",
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