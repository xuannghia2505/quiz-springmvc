<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>
    <div class="container">

  <form action="/acc/edit" method="post">
  <div class="form-group">
    <input type="text" class="form-control" style="width:200px;" value="${acc.username }" name="username" readonly >
  </div>
  <div class="form-group">
    <input type="text" class="form-control" value="${acc.password }"  name="password">
  </div>
  <div class="form-group form-check">
    <input type="text" class="form-control" value="${acc.name }"  name="name">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" value="${acc.age }"  name="age">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
   
</div>
<%@include file="footer.jsp" %>