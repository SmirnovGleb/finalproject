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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listallbets/jquery.dataTables.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listallbets/listallbets.css">
	</head>
  <body>
    <div class="container">
      <c:import url="common/headeradmin.jsp"/>
      <div class="inform">
      	<div class="container">        
			<table id="example" class="display" cellspacing="0" width="100%">
		        <thead class="updown">
		            <tr>
		                 <th><fmt:message key="bet.id"/></th>
		                 <th><fmt:message key="bet.player.id"/></th>
		                 <th><fmt:message key="bet.player"/></th>
		                 <th><fmt:message key="bet.bet.on"/></th>
		                 <th><fmt:message key="bet.money"/></th>
		                 <th><fmt:message key="bet.result"/></th>
		                 <th><fmt:message key="bet.amount"/></th>
		                 <th><fmt:message key="bet.date"/></th>
		            </tr>
		        </thead>
		        <tfoot class="updown">
		            <tr>
		               	<th><fmt:message key="bet.id"/></th>
		               	<th><fmt:message key="bet.player.id"/></th>
		               	<th><fmt:message key="bet.player"/></th>
		                <th><fmt:message key="bet.bet.on"/></th>
		                <th><fmt:message key="bet.money"/></th>
		                <th><fmt:message key="bet.result"/></th>
		                <th><fmt:message key="bet.amount"/></th>
		                <th><fmt:message key="bet.date"/></th>
		            </tr>
		        </tfoot>
		        <tbody>
					<c:forEach var="bet" items="${mapbets}">	
					   	<tr>
		                    <td>${bet.key.id}</td>
		                    <td>${bet.key.userId}</td>
		                    <td>${bet.value}</td>
		                    <td>${bet.key.betOn}</td>
		                    <td>${bet.key.money}</td>
		                    <td>${bet.key.result}</td>
		                    <td>${bet.key.winAmount}</td>
		                    <td>${bet.key.date}</td>
		               	</tr>	
					</c:forEach>
		        </tbody>
    		</table>    
        </div>
      </div>
      <c:import url="common/footer.jsp"/>
    </div>   
    <div class="clearfix"></div>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    'use strict';
	var $ = jQuery;
	$.getScript("${pageContext.request.contextPath}/js/listplayers/datatables.min.js", function(){
		$('#example').DataTable( {
		    "paging":   true,
		    "ordering": true,
		    "info":     false
		} );
	});
    </script>
  </body>
</html>