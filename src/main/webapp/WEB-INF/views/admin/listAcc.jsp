<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>
<div style="display:flex;">
 
	 <form action="/acc/search" method="post" class="form-inline form-search-custom" style="margin-left:540px; margin-bottom:20px;">
      <input class="form-control mr-sm-2" type="search" placeholder="Tìm kiếm User" aria-label="Search" name="keySearch">
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Tìm kiếm</button>
       <button class="btn btn-success ml-2 " type="button" onclick="createForm()">Create</button>
    </form>
    <span id="titleEdit" style="font-size:32px; font-weight:600; margin-left:30px;" class="hide"> Edit User</span>
    <span id="titleCreate" class="hide" style="font-size:32px; font-weight:600; margin-left:30px;"> Create User</span>
    </div>
    <div class="container" style="display:flex;">
    <div>
<table class="table table-hover" style="width:800px;">
  <thead>
    <tr>
      <th scope="col">Username</th>
      <th scope="col">Password</th>
      <th scope="col">Name</th>
      <th scope="col">Age</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${listAcc}" var="acc">

    <tr>
      <th scope="row">${acc.username }</th>
      <td>${acc.password }</td>
      <td>${acc.name }</td>
      <td>${acc.age }</td>
      <td>
      	<button class="btn btn-success" onclick="getUsername('${acc.username}')"><i class="fa fa-edit"></i></button>
      	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal${acc.username}"><i class="fa fa-trash"></i></button>
      
      </td>
    </tr>
    <div class="modal fade" id="modal${acc.username}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel${acc.username}" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel${acc.username}">Xác nhận</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Bạn muốn xóa tài khoản <span style="color:red;">${acc.username}</span> ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <a class="btn btn-danger" href="/acc/delete?username=${acc.username }">Yes</a>
      </div>
    </div>
  </div>
</div>
      </c:forEach>
  </tbody>
</table>
<c:if test="${empty param.keySearch}">
<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item"><a class="page-link" href="/acc/list?message=pre&page=${page }">Previous</a></li>
    <c:forEach begin="1" end="${maxPage }" var="i">
    <li class="page-item ${page == i ? "active" : "" }"><a class="page-link " href="/acc/list?page=${i }">${i }</a></li>
    </c:forEach>
    <li class="page-item"><a class="page-link" href="/acc/list?message=next&page=${page }">Next</a></li>
  </ul>
</nav>
 </c:if>	
</div>
<div id="rsUser"></div>

<form action="/acc/create" method="post" class="hide"  id="formCreateUser" style="align-items:center; text-align:center; margin-left:30px;"  onsubmit="return Validation();">
  <div class="form-group">
  
    <input type="text" class="form-control" style="width:250px;" name="username" oninput="checkUser()" id="username" placeholder="Username" >
    <small style="color:red" id="userValid"></small>
  </div>
  <div class="form-group">
    <input type="text" class="form-control" style="width:250px;" name="password" placeholder="Password" id="password">
    <small style="color:red" id="password1"></small>
  </div>
  <div class="form-group">
    <input type="text" class="form-control" style="width:250px;"   name="name" placeholder="Name" id="name" >
     <small style="color:red" id="name1"></small>
  </div>
  <div class="form-group">
    <input type="text" class="form-control" style="width:250px;"   name="age" placeholder="Age" id="age">
     <small style="color:red" id="age1"></small>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	var titleCreate = document.getElementById('titleCreate');
	var formCreateUser = document.getElementById('formCreateUser');
	var formEditUser = document.getElementById('formEditUser');
	var titleEdit = document.getElementById('titleEdit');
	
	var row = document.getElementById("rsUser");
	function getUsername(username){	
			
			formCreateUser.classList.add("hide");
			titleCreate.classList.add("hide");
			titleEdit.classList.remove("hide");
			
			url= "http://localhost:8080/acc/edit?username="+username;
			history.pushState(null,null,url);
			
			$.ajax({
				url : "/acc/edit",
				type : "get", //send it through get method
				data : {
					username :username
				},
				success : function(data) {
					
					
					row.innerHTML = data;
				},
				error : function(xhr) {
					//Do Something to handle error
				}
			});
		
	
	}
	function createForm(){
		  $("#formCreateUser").toggleClass("hide");
		  $("#titleCreate").toggleClass("hide");
		row.innerHTML="";
		titleEdit.classList.add("hide");
	}
	function checkUser(){
		
		var usernameInput = document.getElementById("username").value;
		
		$.ajax({
			url : "/acc/checkUser",
			type : "post", //send it through get method
			data : {
				username :usernameInput
			},
			success : function(data) {
			
					var error = document.getElementById("userValid");
					error.innerHTML = data;
				
				
			},
			error : function(xhr) {
				//Do Something to handle error
			}
		});
	}
	function Validation(){
		let flag=true;
		let username = document.getElementById('username').value;
		let password = document.getElementById('password').value;	
		let name = document.getElementById('name').value;
		let age = document.getElementById('age').value;
		if(document.getElementById('userValid').innerText==""){
		if(username==""){
			flag=false;
			document.getElementById('userValid').innerText="Không được bỏ trống";
		}else{
			document.getElementById('userValid').innerText="";
		}
		}else{
			flag=false;
		}
		
		if(password==""){
			flag=false;
			document.getElementById('password1').innerText="Không được bỏ trống";
		}else{
			document.getElementById('password1').innerText="";
		}
		
		if(name==""){
			flag=false;
			document.getElementById('name1').innerText="Không được bỏ trống";
		}else{
			document.getElementById('name1').innerText="";
		}
		if(age==""){
			flag=false;
			document.getElementById('age1').innerText="Không được bỏ trống";
		}else{
			document.getElementById('age1').innerText="";
		}
		
		return flag;
	}

		
		
	</script>
<%@include file="footer.jsp" %>