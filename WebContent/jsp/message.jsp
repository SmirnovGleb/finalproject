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
	href="${pageContext.request.contextPath}/css/message/message.css">
</head>
<body>
	<div class="container">
		<c:import url="common/header.jsp" />
		<div class="inform">
			<div class="container">
				<div class="row form-group">
					<div
						class="col-xs-12 col-md-offset-3 col-md-6 col-lg-6 col-lg-offset-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-comment"></span>
								<fmt:message key="user.message" />
							</div>
							<div class="panel-body body-panel" id="scrolldown">
								<ul class="chat">
									<c:forEach var="mess" items="${messageslist}">
										<li class="left clearfix">
											<div class="chat-body clearfix">
												<div class="header">
													<strong class="primary-font">${mess[0]}</strong>
												</div>
												<p>${mess[1]}</p>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="panel-footer clearfix">
								<form action="/Roulette/Controller" method="post">
									<textarea class="form-control" rows="3" name="textmessage"></textarea>
									<span
										class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-xs-12"
										style="margin-top: 10px">
										<button class="btn btn-warning btn-md btn-block" id="btn-chat">
											<fmt:message key="message.send" />
										</button>
									</span> <input type="hidden" name="command" value="messages">
								</form>
							</div>
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
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById('scrolldown').scrollTop = 9999;
		}
	</script>
</body>
</html>