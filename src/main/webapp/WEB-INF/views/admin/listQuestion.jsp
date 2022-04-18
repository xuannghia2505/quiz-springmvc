<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>

<div class="form-group">
	<form action="/question/add" method="post">
		<select class="form-control" name="quizID" id="selectCatelogy"
			onchange="findQuestion(this)" style="width: 300px; margin: 10px;">
			<option selected disabled value="none">....</option>
			<c:forEach items="${listQuiz }" var="quiz">
				<option value="${quiz.quizID}">${quiz.name}</option>
			</c:forEach>
		</select>
		<button style="margin-left: 10px;" type="button"
			class="btn btn-success hide" id="btnAddQuestion"
			onclick="addNewQuestion()">Add New Question</button>
		<div id="formAddQuestion" class="hide" style="width:500px; position:absolute; right:20px;">
			<input type="text" class="form-control" placeholder="Question" name="question">
			<input type="text" class="form-control" placeholder="AnswerA" name="answerA">
			<input type="text" class="form-control" placeholder="AnswerB" name="answerB">
			<input type="text" class="form-control" placeholder="AnswerC" name="answerC">
			<input type="text" class="form-control" placeholder="AnswerD" name="answerD">
			<input type="text" class="form-control" placeholder="Correct Answer" name="correctAnswer">
			<input type="text" class="form-control" placeholder="Image" name="image">
			<input type="text" class="form-control" placeholder="Audio" name="audio">
			<button class="btn btn-success" type="submit">Add</button>
		</div>
	</form>
	<form action="/question/edit" method="post">
		
		<div id="questionEdit"></div>
	</form>
</div>


<div id="questionRs" style="display: flex;">
	<%-- <table class="table table-hover" style="width: 800px;">
		<thead>
			<tr style="text-align: center;">
				<th scope="col">QuestionID</th>
				<th scope="col">Question</th>
				<th scope="col">CorrectAnswer</th>
				<th scope="col">Audio</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listQuestion}" var="question">

				<tr style="text-align: center;">
					<td>${quiz.questionID }</td>
					<td>${quiz.question }</td>
					<td>${quiz.correctAnswer }</td>
					<td><audio src="${quiz.audio }" controls></audio></td>
					<td><a class="btn btn-success"
						href="/question/edit?questionID=${quiz.quizID}"><i
							class="fa fa-edit"></i></a>
						<button type="button" class="btn btn-danger" data-toggle="modal"
							data-target="#modal${quiz.quizID}">
							<i class="fa fa-trash"></i>
						</button></td>
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
								Bạn muốn xóa Question có ID: <span style="color: red;">${quiz.quizID}</span>
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
	</table> --%>
</div>




<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var row = document.getElementById("questionRs");
	var questionEdit = document.getElementById("questionEdit");
	var btnAddQuestion = document.getElementById("btnAddQuestion");
	function findQuestion(quiz) {

		btnAddQuestion.classList.remove("hide");
		url = "http://localhost:8080/question/list?quizID=" + quiz.value;
		history.pushState(null, null, url);

		$.ajax({
			url : "/question/search",
			type : "get", //send it through get method
			data : {
				quizID : quiz.value
			},
			success : function(data) {

				row.innerHTML = data;
			},
			error : function(xhr) {
				//Do Something to handle error
			}
		});

	}
	function getQuestion(questionID) {
		$("#formAddQuestion").addClass("hide");
		url = "http://localhost:8080/question/edit?questionID=" + questionID;
		history.pushState(null, null, url);

		$.ajax({
			url : "/question/edit",
			type : "get", //send it through get method
			data : {
				questionID : questionID
			},
			success : function(data) {

				questionEdit.innerHTML = data;
			},
			error : function(xhr) {
				//Do Something to handle error
			}
		});

	}
	
	function addNewQuestion() {
		$("#formEditQuestion").addClass("hide");
		$("#formAddQuestion").toggleClass("hide");
		
	}

	function Validation() {
		let flag = true;
		var quizName = document.getElementById('quizName');
		var selectCatelogy = document.getElementById('selectCatelogy');
		var quizImage = document.getElementById('quizImage');
		var quizNumberQuestion = document.getElementById('quizNumberQuestion');
		if (quizName.value == '') {
			document.getElementById('errorName').innerText = "Không được bỏ trống"
			flag = false;
		}
		if (selectCatelogy.value == 'none') {
			document.getElementById('errorCatelogy').innerText = "Không được bỏ trống"
			flag = false;
		}
		if (quizImage.value == '') {
			document.getElementById('errorImage').innerText = "Không được bỏ trống"
			flag = false;
		}
		if (quizNumberQuestion.value == '') {
			document.getElementById('errorNumber').innerText = "Không được bỏ trống"
			flag = false;
		}
		return flag;
	}
</script>
<%@include file="footer.jsp"%>