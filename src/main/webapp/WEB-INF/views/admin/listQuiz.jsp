<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>
	 <div style="display:flex;">
 
	 <form action="/quiz/search" method="post" class="form-inline form-search-custom" style="margin-left:540px; margin-bottom:20px;">
      <input class="form-control mr-sm-2" type="search" placeholder="Tìm kiếm Quiz" aria-label="Search" name="keySearch">
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Tìm kiếm</button>
       <button class="btn btn-success ml-2 " type="button" onclick="createForm()">Create</button>
    </form>
    <span id="titleEdit" style="font-size:32px; font-weight:600; margin-left:30px;" class="hide"> Edit Quiz</span>
    <span id="titleCreate" class="hide" style="font-size:32px; font-weight:600; margin-left:30px;"> Create Quiz</span>
    </div>
    <div class="container" style="display:flex;">
    <div>
<table class="table table-hover" style="width:800px;">
  <thead >
    <tr style="text-align:center;">
      <th scope="col">Name</th>
      <th scope="col">Catelogy</th>
      <th scope="col">Image</th>
      <th scope="col">Number Question</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${listQuizs}" var="quiz">

    <tr style="text-align:center;">
      <th scope="row">${quiz.name }</th>
      <td>${quiz.catelogy }</td>
      <td><img src="${quiz.image }" width="50" height="50" /></td>
      
      <td>${quiz.numberQuestion }</td>
      <td>
      	<button class="btn btn-success" onclick="getQuiz('${quiz.quizID}')"><i class="fa fa-edit"></i></button>
      	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal${quiz.quizID}"><i class="fa fa-trash"></i></button>
      
      </td>
    </tr>
					<div class="modal fade" id="modal${quiz.quizID}" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel${quiz.quizID}"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel${quiz.quizID}">Xác
										nhận</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									Bạn muốn xóa Quiz ID <span style="color: red;">${quiz.quizID}</span>
									?
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/quiz/delete?quizID=${quiz.quizID }">Yes</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
  </tbody>
</table>
</div>
<div id="rsUser"></div>

<form action="/quiz/create" method="post" class="hide"  id="formCreateQuiz" style="align-items:center; text-align:center; margin-left:30px;" onsubmit="return Validation();" >
  <div class="form-group">

    <input type="text" class="form-control" style="width:250px;" name="quizName" placeholder="Name" id="quizName" >
    
  </div>
   <small style="color:red" id="errorName"></small>
  <div class="form-group">
      <select class="form-control" name="catelogy" id="selectCatelogy">
        <option selected disabled value="none">....</option>
        <c:forEach items="${listCatelogy }" var="catelogy">
        <option value="${catelogy}">${catelogy}</option>
        </c:forEach>
      </select>
    </div>
      <small style="color:red" id="errorCatelogy"></small>
  <div class="form-group">
    <input type="text" class="form-control" style="width:250px;"   name="image" placeholder="Image" id="quizImage">
  </div>
   <small style="color:red" id="errorImage"></small>
  <div class="form-group">
    <input type="text" class="form-control" style="width:250px;"   name="numberQuestion" placeholder="Number of Question" id="quizNumberQuestion">
  </div>
  <small style="color:red" id="errorNumber"></small>
  <div>
  <button type="submit" class="btn btn-primary">Submit</button>
  </div>
</form>
</div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	var titleCreate = document.getElementById('titleCreate');
	var formCreateQuiz = document.getElementById('formCreateQuiz');
	var formEditQuiz = document.getElementById('formEditQuiz');
	var titleEdit = document.getElementById('titleEdit');
	
	var row = document.getElementById("rsUser");
	function getQuiz(quizID){	
			
			formCreateQuiz.classList.add("hide");
			titleCreate.classList.add("hide");
			titleEdit.classList.remove("hide");
			
			url= "http://localhost:8080/quiz/edit?quizID="+quizID;
			history.pushState(null,null,url);
			
			$.ajax({
				url : "/quiz/edit",
				type : "get", //send it through get method
				data : {
					quizID :quizID
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
		  $("#formCreateQuiz").toggleClass("hide");
		  $("#titleCreate").toggleClass("hide");
		row.innerHTML="";
		titleEdit.classList.add("hide");
	}
	
	function Validation(){
		let flag = true;
		var quizName = document.getElementById('quizName');
		var selectCatelogy = document.getElementById('selectCatelogy');
		var quizImage = document.getElementById('quizImage');
		var quizNumberQuestion = document.getElementById('quizNumberQuestion');
		if(quizName.value==''){
			document.getElementById('errorName').innerText="Không được bỏ trống"
			flag= false;
		}
		if(selectCatelogy.value=='none'){
			document.getElementById('errorCatelogy').innerText="Không được bỏ trống"
			flag= false;
		}
		if(quizImage.value==''){
			document.getElementById('errorImage').innerText="Không được bỏ trống"
			flag= false;
		}
		if(quizNumberQuestion.value==''){
			document.getElementById('errorNumber').innerText="Không được bỏ trống"
			flag= false;
		}
		return flag;
	}

		
		
	</script>
<%@include file="footer.jsp" %>