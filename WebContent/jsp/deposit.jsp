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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/deposit/deposit.css">
	</head>
  <body>
    <div class="container">
	  <c:import url="common/header.jsp"/>
      <div class="inform">
      	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-7">
                <div class="panel panel-default">
                    <div class="panel-body">
                      <form action="/Roulette/Controller" method="post" class="form-horizontal" role="form">
                        <fieldset>
                          <legend><fmt:message key="card.payment"/></legend>
                          <div class="form-group">
                            <label class="col-sm-3 control-label" for="card-holder-name"><fmt:message key="card.name"/></label>
                            <div class="col-sm-9">
                              <input type="text" class="form-control" name="card-holder-name" id="card-holder-name" placeholder=<fmt:message key="card.name"/> required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label" for="card-number"><fmt:message key="card.number"/></label>
                            <div class="col-sm-9">
                              <input type="text" class="form-control" name="card-number" id="card-number" placeholder=<fmt:message key="card.number"/> required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label" for="expiry-month"><fmt:message key="card.expiration"/></label>
                            <div class="col-sm-9">
                              <div class="row">
                                <div class="col-xs-3">
                                  <select class="form-control col-sm-2" name="expiry-month" id="expiry-month">
                                    <option><fmt:message key="card.month"/></option>
                                    <option value="01">Jan (01)</option>
                                    <option value="02">Feb (02)</option>
                                    <option value="03">Mar (03)</option>
                                    <option value="04">Apr (04)</option>
                                    <option value="05">May (05)</option>
                                    <option value="06">June (06)</option>
                                    <option value="07">July (07)</option>
                                    <option value="08">Aug (08)</option>
                                    <option value="09">Sep (09)</option>
                                    <option value="10">Oct (10)</option>
                                    <option value="11">Nov (11)</option>
                                    <option value="12">Dec (12)</option>
                                  </select>
                                </div>
                                <div class="col-xs-3">
                                  <select class="form-control" name="expiry-year">
                                    <option value="13">2013</option>
                                    <option value="14">2014</option>
                                    <option value="15">2015</option>
                                    <option value="16">2016</option>
                                    <option value="17">2017</option>
                                    <option value="18">2018</option>
                                    <option value="19">2019</option>
                                    <option value="20">2020</option>
                                    <option value="21">2021</option>
                                    <option value="22">2022</option>
                                    <option value="23">2023</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label" for="cvv"><fmt:message key="card.cvv"/></label>
                            <div class="col-sm-3">
                              <input type="text" class="form-control" name="cvv" id="cvv" placeholder=<fmt:message key="card.cvv2"/> required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label" for="amaunt"><fmt:message key="card.money"/></label>
                            <div class="col-sm-3">
                              <input type="number" min="10" max="10000" value="10" step="10" class="form-control" name="money" id="amaunt" placeholder=<fmt:message key="card.money"/> required>
                            </div>
                          </div>
                          <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-success btn-sm"><fmt:message key="card.pay"/></button>
                                <button type="reset" class="btn btn-default btn-sm"><fmt:message key="user.reset"/></button>
                            </div>
                        </div>
                        </fieldset>
                        <input type="hidden" name="command" value="deposit">
                      </form>
                    </div>
                </div>
            </div>
        </div>
      </div>
      </div>
      <c:import url="common/footer.jsp"/>
    </div> 
      </div>
    </div>   
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  </body>
</html>