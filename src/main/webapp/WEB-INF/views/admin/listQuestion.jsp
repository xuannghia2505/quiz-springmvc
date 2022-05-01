<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<style>
.form-group b {
	float: left;
}

.form-group img {
	margin-right: 20px;
}
</style>
<div class="form-group">
	<form action="/question/editQuestion" method="get"
		enctype='multipart/form-data'>
		<select class="form-control" name="quizID" id="selectCatelogy"
			onchange="this.form.submit()" style="width: 300px; margin: 10px;">
			<option selected disabled value="none">....</option>
			<c:forEach items="${listQuizs}" var="quiz">
				<option value="${quiz.quizID}"
					${quiz.quizID==quizID ? 'selected="selected"' : ''}>${quiz.name}</option>
			</c:forEach>
		</select>
		<c:if test="${message2!='none' }">
			<c:if test="${q.image==null&&q.audio==null}">
				<button style="margin-left: 10px;" type="button"
					class="btn btn-success" id="btnAddQuestion" data-toggle="modal"
					data-target="#modalCreateQuestion1">Add New Question</button>
			</c:if>
			<c:if test="${q.answerA==null}">
				<button style="margin-left: 10px;" type="button"
					class="btn btn-success" id="btnAddQuestion" data-toggle="modal"
					data-target="#modalCreateQuestion2">Add New Question</button>
			</c:if>
			<c:if test="${q.question==null}">
				<button style="margin-left: 10px;" type="button"
					class="btn btn-success" id="btnAddQuestion" data-toggle="modal"
					data-target="#modalCreateQuestion3">Add New Question</button>
			</c:if>
			
		</c:if>
		<!-- <div id="formAddQuestion" class="hide" style="width:500px; position:absolute; right:20px;">
			<input type="text" class="form-control" placeholder="Question" name="question">
			<input type="text" class="form-control" placeholder="AnswerA" name="answerA">
			<input type="text" class="form-control" placeholder="AnswerB" name="answerB">
			<input type="text" class="form-control" placeholder="AnswerC" name="answerC">
			<input type="text" class="form-control" placeholder="AnswerD" name="answerD">
			<input type="text" class="form-control" placeholder="Correct Answer" name="correctAnswer">
			<input type="file" class="form-control" placeholder="Image" name="image">
			<input type="file" class="form-control" placeholder="Audio" name="audio">
			<button class="btn btn-success" type="submit">Add</button>
		</div> -->
	</form>
	<form action="/question/edit" method="post">

		<div id="questionEdit"></div>
	</form>
</div>


<div id="questionRs" class="container-fluid" style="text-align: center;">
	<table class="table table-hover">
		<c:if test="${q.image==null&&q.audio==null}">
			<thead>
				<tr style="text-align: center;">
					<th scope="col">QuestionID</th>
					<th scope="col">Question</th>
					<th scope="col">AnswerA</th>
					<th scope="col">AnswerB</th>
					<th scope="col">AnswerC</th>
					<th scope="col">AnswerD</th>
					<th scope="col">CorrectAnswer</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listQuestions}" var="question">

					<tr style="text-align: center;">
						<td>${question.questionID }</td>
						<td>${question.question }</td>
						<td>${question.answerA }</td>
						<td>${question.answerB }</td>
						<td>${question.answerC }</td>
						<td>${question.answerD }</td>
						<td>${question.correctAnswer }</td>
						<td><button class="btn btn-success" data-toggle="modal"
								data-target="#modalEditQuestion${question.questionID}">
								<i class="fa fa-edit"></i>
							</button>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								data-target="#modal${question.questionID}">
								<i class="fa fa-trash"></i>
							</button></td>
					</tr>
					<div class="modal fade" id="modal${question.questionID}" tabindex="-1"
						role="dialog" aria-bledby="exampleModalb${question.questionID}"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalb${question.questionID}">Xác
										nhận</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-b="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									Bạn muốn xóa Question có ID: <span style="color: red;">${question.questionID}</span>
									?
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/question/delete?questionID=${question.questionID}&quizID=${question.quizID}">Yes</a>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade"
						id="modalEditQuestion${question.questionID }" tabindex="-1"
						role="dialog"
						aria-bledby="exampleModalbEdit${question.questionID }"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title"
										id="exampleModalbEdit${question.questionID }">Chỉnh sửa
										question</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-b="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form action="/question/update1" method="post">
									<div class="modal-body">
										<div style="text-align: center; font-size: 22px;">
											Bạn đang chỉnh sửa Question ID: <b style="color: red;">${question.questionID}</b>
										</div>
										<input value="${question.questionID}" name="questionID" hidden>
										<div class="form-group">
											<b for="exampleInput1">Question</b> <input type="text"
												class="form-control" id="exampleInput1"
												placeholder="Nhập câu hỏi" value="${question.question }"
												name="question"
												required>
										</div>
										<div class="form-group">
											<b for="exampleInput2">AnswerA</b> <input type="text"
												class="form-control" id="exampleInput2"
												placeholder="Nhập câu trả lời A"
												value="${question.answerA }" name="answerA"
												required>
										</div>
										<div class="form-group">
											<b for="exampleInput3">AnswerB</b> <input type="text"
												class="form-control" id="exampleInput3"
												placeholder="Nhập câu trả lời B"
												value="${question.answerB }" name="answerB"
												required>
										</div>
										<div class="form-group">
											<b for="exampleInput4">AnswerC</b> <input type="text"
												class="form-control" id="exampleInput4"
												placeholder="Nhập câu trả lời C"
												value="${question.answerC }" name="answerC"
												required>
										</div>
										<div class="form-group">
											<b for="exampleInput5">AnswerD</b> <input type="text"
												class="form-control" id="exampleInput5"
												placeholder="Nhập câu trả lời D"
												value="${question.answerD }" name="answerD"
												required>
										</div>
										<div class="form-group">
											<b for="exampleInput6">Correct Answer</b> <select
												class="form-control" name="correctAnswer" id="exampleInput6">
												<option value="A"
													${question.correctAnswer=='A' ? 'selected="selected"' : ''}>A</option>
												<option value="B"
													${question.correctAnswer=='B' ? 'selected="selected"' : ''}>B</option>
												<option value="C"
													${question.correctAnswer=='C' ? 'selected="selected"' : ''}>C</option>
												<option value="D"
													${question.correctAnswer=='D' ? 'selected="selected"' : ''}>D</option>
											</select>
										</div>
										<div class="form-group">
											<b>Quiz</b> <br /> <select class="form-control"
												name="quizID" id="selectCatelogy"
												style="width: 300px; margin: 10px;">
												<c:forEach items="${listQuizs}" var="quiz">
													<option value="${quiz.quizID}"
														${quiz.quizID==quizID ? 'selected="selected"' : ''}>${quiz.name}</option>
												</c:forEach>
											</select>
										</div>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>
										<button class="btn btn-success" type="submit">Cập
											nhật</button>
									</div>
								</form>
							</div>
						</div>
					</div>

				</c:forEach>
			</tbody>
		</c:if>
		<c:if test="${q.answerA==null}">
			<thead>
				<tr style="text-align: center;">
					<th scope="col">QuestionID</th>
					<th scope="col">Question</th>
					<th scope="col">CorrectAnswer</th>
					<th scope="col">Image</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listQuestions}" var="question">

					<tr style="text-align: center;">
						<td>${question.questionID }</td>
						<td>${question.question }</td>
						<td>${question.correctAnswer }</td>
						<td><img src="${question.image }" width="50" height="50"></td>
						<td><button class="btn btn-success" data-toggle="modal"
								data-target="#modalEditQuestion${question.questionID}">
								<i class="fa fa-edit"></i>
							</button>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								data-target="#modal${question.questionID}">
								<i class="fa fa-trash"></i>
							</button></td>
					</tr>
					<div class="modal fade" id="modal${question.questionID}" tabindex="-1"
						role="dialog" aria-bledby="exampleModalb${question.questionID}"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalb${question.questionID}">Xác
										nhận</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-b="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									Bạn muốn xóa Question có ID: <span style="color: red;">${question.questionID}</span>
									?
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/question/delete?questionID=${question.questionID}&quizID=${question.quizID}">Yes</a>
								</div>
							</div>
						</div>
					</div>

					<div class="modal fade"
						id="modalEditQuestion${question.questionID }" tabindex="-1"
						role="dialog"
						aria-bledby="exampleModalbEdit${question.questionID }"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title"
										id="exampleModalbEdit${question.questionID }">Chỉnh sửa
										question</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-b="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form action="/question/update2" method="post"
									enctype="multipart/form-data">
									<input value="${question.questionID}" name="questionID" hidden>
									<div class="modal-body">
										<div style="text-align: center; font-size: 22px;">
											Bạn đang chỉnh sửa Question ID: <b style="color: red;">${question.questionID}</b>
										</div>

										<div class="form-group">
											<b for="exampleInput1">Question</b> <input type="text"
												class="form-control" id="exampleInput1"
												placeholder="Nhập câu hỏi" value="${question.question }"
												name="question"
												required>
										</div>


										<div class="form-group">
											<b>Image</b> <br />
											<div style="display: flex;">
												<img src="${question.image}" width="50" height="50"
													id="oldEditImageEnglish"> <img width="50" height="50"
													id="newEditImageEnglish" class="hide"> <input type="file"
													class="form-control" name="image" onchange="readURLEditImageEnglish(this);">
											</div>
										</div>
										<div class="form-group">
											<b>Quiz</b><br /> <select class="form-control" name="quizID"
												id="selectCatelogy" style="width: 300px; margin: 10px;">
												<c:forEach items="${listQuizs}" var="quiz">
													<option value="${quiz.quizID}"
														${quiz.quizID==quizID ? 'selected="selected"' : ''}>${quiz.name}</option>
												</c:forEach>
											</select>
										</div>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>
										<button class="btn btn-success" type="submit">Cập
											nhật</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</tbody>
		</c:if>
		<c:if test="${q.question==null}">
			<thead>
				<tr style="text-align: center;">
					<th scope="col">QuestionID</th>
					<th scope="col">AnswerA</th>
					<th scope="col">AnswerB</th>
					<th scope="col">AnswerC</th>
					<th scope="col">AnswerD</th>
					<th scope="col">CorrectAnswer</th>
					<th scope="col">Audio</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listQuestions}" var="question">

					<tr style="text-align: center;">
						<td>${question.questionID }</td>
						<td><img src="${question.answerA}" width="50" height="50"></td>
						<td><img src="${question.answerB}" width="50" height="50"></td>
						<td><img src="${question.answerC}" width="50" height="50"></td>
						<td><img src="${question.answerD}" width="50" height="50"></td>
						<td>${question.correctAnswer }</td>
						<td><audio src="/${question.audio }" controls></audio></td>
						<td><button class="btn btn-success" data-toggle="modal"
								data-target="#modalEditQuestion${question.questionID}">
								<i class="fa fa-edit"></i>
							</button>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								data-target="#modal${question.questionID}">
								<i class="fa fa-trash"></i>
							</button></td>
					</tr>
					<div class="modal fade" id="modal${question.questionID}"
						tabindex="-1" role="dialog"
						aria-bledby="exampleModalb${question.questionID}"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title"
										id="exampleModalb${question.questionID}">Xác nhận</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-b="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									Bạn muốn xóa Question có ID: <span style="color: red;">${question.questionID}</span>
									?
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/question/delete?questionID=${question.questionID}&quizID=${question.quizID}">Yes</a>
								</div>
							</div>
						</div>
					</div>


					<div class="modal fade"
						id="modalEditQuestion${question.questionID }" tabindex="-1"
						role="dialog"
						aria-bledby="exampleModalbEdit${question.questionID }"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title"
										id="exampleModalbEdit${question.questionID }">Chỉnh sửa
										question</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-b="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form action="/question/update3" method="post"
									enctype="multipart/form-data">
									<input value="${question.questionID}" name="questionID" hidden>
									<div class="modal-body">
										<div style="text-align: center; font-size: 22px;">
											Bạn đang chỉnh sửa Question ID: <b style="color: red;">${question.questionID}</b>
										</div>

										<div class="form-group">
											<b for="exampleInput2">AnswerA</b><br />
											<div style="display: flex;">
												<img src="${question.answerA}" width="50" height="50"
													id="oldImage1"> <img id="newImage1" width="50"
													height="50" class="hide"> <input type="file"
													class="form-control" id="exampleInput2"
													placeholder="Nhập câu trả lời A" name="answerA"
													onchange="readURLs(this,'1');">
											</div>
										</div>
										<div class="form-group">
											<b for="exampleInput3">AnswerB</b><br />
											<div style="display: flex;">
												<img src="${question.answerB}" width="50" height="50"
													id="oldImage2"> <img id="newImage2" width="50"
													height="50" class="hide"> <input type="file"
													class="form-control" id="exampleInput3"
													placeholder="Nhập câu trả lời B" name="answerB"
													onchange="readURLs(this,'2');">
											</div>
										</div>
										<div class="form-group">
											<b for="exampleInput4">AnswerC</b><br />
											<div style="display: flex;">
												<img src="${question.answerC}" width="50" height="50"
													id="oldImage3"> <img id="newImage3" width="50"
													height="50" class="hide"> <input type="file"
													class="form-control" id="exampleInput4"
													placeholder="Nhập câu trả lời C" name="answerC"
													onchange="readURLs(this,'3');">
											</div>
										</div>
										<div class="form-group">
											<b for="exampleInput5">AnswerD</b><br />
											<div style="display: flex;">
												<img src="${question.answerD}" width="50" height="50"
													id="oldImage4"> <img id="newImage4" width="50"
													height="50" class="hide"> <input type="file"
													class="form-control" id="exampleInput5"
													placeholder="Nhập câu trả lời D" name="answerD"
													onchange="readURLs(this,'4');">
											</div>
										</div>
										<div class="form-group">
											<b for="exampleInput6">Correct Answer</b> <select
												class="form-control" name="correctAnswer" id="exampleInput6">
												<option value="A"
													${question.correctAnswer=='A' ? 'selected="selected"' : ''}>A</option>
												<option value="B"
													${question.correctAnswer=='B' ? 'selected="selected"' : ''}>B</option>
												<option value="C"
													${question.correctAnswer=='C' ? 'selected="selected"' : ''}>C</option>
												<option value="D"
													${question.correctAnswer=='D' ? 'selected="selected"' : ''}>D</option>
											</select>
										</div>
										<div class="form-group">
											<b for="exampleInput8">Audio</b><br>
											<div style="display: flex;">
												<audio src="/${question.audio}" id="oldAudio" controls></audio>
												<audio controls id="newAudio" class="hide"></audio>
												<input type="file" class="form-control" id="exampleInput8"
													name="audio" onchange="readURLs(this,'5');">>
											</div>
										</div>
										<div class="form-group">
											<b>Quiz</b><br /> <select class="form-control" name="quizID"
												id="selectCatelogy" style="width: 300px; margin: 10px;">
												<c:forEach items="${listQuizs}" var="quiz">
													<option value="${quiz.quizID}"
														${quiz.quizID==quizID ? 'selected="selected"' : ''}>${quiz.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>
										<button class="btn btn-success" type="submit">Cập
											nhật</button>
									</div>
								</form>
							</div>
						</div>
					</div>


				</c:forEach>
			</tbody>
		</c:if>
	</table>
</div>
<h1 style="text-align: center; color: red;">${message}</h1>
<div class="modal fade" id="modalCreateQuestion1" tabindex="-1"
	role="dialog" aria-bledby="exampleModalbCreate1" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalbCreate1">Add Question</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-b="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="/question/add1" method="post" onsubmit="return ValidationAdd1();")>
				<input value="${q.quizID}" name="quizID" hidden>
				<div class="modal-body">
				
					<div class="form-group">
						<b for="exampleInput1">Question</b> <input type="text"
							class="form-control" id="exampleInput1"
							placeholder="Nhập câu hỏi" name="question" required>
					</div>
					<div class="form-group">
						<b for="exampleInput2">AnswerA</b> <input type="text"
							class="form-control" id="exampleInput2"
							placeholder="Nhập câu trả lời A" name="answerA" required>
					</div>
					<div class="form-group">
						<b for="exampleInput3">AnswerB</b> <input type="text"
							class="form-control" id="exampleInput3"
							placeholder="Nhập câu trả lời B" name="answerB" required>
					</div>
					<div class="form-group">
						<b for="exampleInput4">AnswerC</b> <input type="text"
							class="form-control" id="exampleInput4"
							placeholder="Nhập câu trả lời C" name="answerC" required>
					</div>
					<div class="form-group">
						<b for="exampleInput5">AnswerD</b> <input type="text"
							class="form-control" id="exampleInput5"
							placeholder="Nhập câu trả lời D" name="answerD" required>
					</div>
					<div class="form-group">
						<b for="exampleInput6">Correct Answer</b> <select
							class="form-control" name="correctAnswer" id="exampleInput6">
							<option value="none" selected disabled>---</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</div>
					<small style="color:red" id="errorInput6"></small>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<button class="btn btn-success" type="submit">Add</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="modal fade" id="modalCreateQuestion2" tabindex="-1"
	role="dialog" aria-bledby="exampleModalbCreate2" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalbCreate2">Add Question</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-b="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="/question/add2" method="post"
				enctype="multipart/form-data" onsubmit="return ValidationAdd2();">
				<input value="${q.quizID}" name="quizID" hidden>
				<div class="modal-body">
					<div class="form-group">
						<b for="exampleInput1">Question</b> <input type="text"
							class="form-control" id="exampleInput1"
							placeholder="Nhập câu hỏi" name="question" required>
					</div>


					<div class="form-group">
						<b>Image</b> <br />
						<div style="display: flex;">					
							<img width="50" height="50" id="newAddImageEnglish" class="hide"> <input id="imageQuestion2"
								type="file" class="form-control" name="image"
								onchange="readURLAddImageEnglish(this);">
						</div>
						<small style="color:red" id="imageQuestionError2"></small>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<button class="btn btn-success" type="submit">Add</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="modal fade" id="modalCreateQuestion3" tabindex="-1"
	role="dialog" aria-bledby="exampleModalbCreate3" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalbCreate3">Add Question</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-b="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="/question/add3" method="post"
				enctype="multipart/form-data" onsubmit="return ValidationAdd3();">
					<input value="${q.quizID}" name="quizID" hidden>
				<div class="modal-body">

				
					<div class="form-group">
						<b for="exampleInput1">AnswerA</b><br />
						<div style="display: flex;">
							<img id="newImageAdd1" width="50" height="50" class="hide"> <input
								type="file" class="form-control" id="exampleInputAdd1"
								placeholder="Nhập câu trả lời A" name="answerA"
								onchange="readURLAdd(this,'1');">
						</div>
						<small style="color:red" id="exampleInputError1"></small>
					</div>
					<div class="form-group">
						<b for="exampleInput2">AnswerB</b><br />
						<div style="display: flex;">
							<img id="newImageAdd2" width="50" height="50" class="hide"> <input
								type="file" class="form-control" id="exampleInputAdd2"
								placeholder="Nhập câu trả lời B" name="answerB"
								onchange="readURLAdd(this,'2');">
						</div>
						<small style="color:red" id="exampleInputError2"></small>
					</div>
					<div class="form-group">
						<b for="exampleInput3">AnswerC</b><br />
						<div style="display: flex;">
							<img id="newImageAdd3" width="50" height="50" class="hide"> <input
								type="file" class="form-control" id="exampleInputAdd3"
								placeholder="Nhập câu trả lời C" name="answerC"
								onchange="readURLAdd(this,'3');">
						</div>
						<small style="color:red" id="exampleInputError3"></small>
					</div>
					<div class="form-group">
						<b for="exampleInput4">AnswerD</b><br />
						<div style="display: flex;">
							<img id="newImageAdd4" width="50" height="50" class="hide"> <input
								type="file" class="form-control" id="exampleInputAdd4"
								placeholder="Nhập câu trả lời D" name="answerD"
								onchange="readURLAdd(this,'4');">
						</div>
						<small style="color:red" id="exampleInputError4"></small>
					</div>
					<div class="form-group">
						<b for="exampleInput5">Correct Answer</b> <select
							class="form-control" name="correctAnswer" id="exampleInputAdd5">
							<option value="none" selected>---</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
						<small style="color:red" id="exampleInputError5"></small>
					</div>
					<div class="form-group">
						<b for="exampleInput6">Audio</b><br>
						<div style="display: flex;">
							<audio controls id="newAudioAdd" class="hide"></audio>
							<input type="file" class="form-control" id="exampleInputAdd6"
								name="audio" onchange="readURLAdd(this,'5');">
						</div>
						<small style="color:red" id="exampleInputError6"></small>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<button class="btn btn-success" type="submit">Add</button>
				</div>
			</form>
		</div>
	</div>
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
	function readURL(input) {
		if (input.files && input.files[0]) {
			document.getElementById('newImage').classList.remove("hide");
			document.getElementById('oldImage').classList.add("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImage').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}

	}
	function readURLAddImageEnglish(input) {
		if (input.files && input.files[0]) {
			document.getElementById('newAddImageEnglish').classList.remove("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newAddImageEnglish').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}

	}
	function readURLEditImageEnglish(input) {
		if (input.files && input.files[0]) {
			document.getElementById('oldEditImageEnglish').classList.add("hide");
			document.getElementById('newEditImageEnglish').classList.remove("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newEditImageEnglish').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}

	}
	
	function readURLs(input, key) {
		if (key == '1') {
			document.getElementById('newImage1').classList.remove("hide");
			document.getElementById('oldImage1').classList.add("hide");
			console.log("hello");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImage1').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '2') {
			document.getElementById('newImage2').classList.remove("hide");
			document.getElementById('oldImage2').classList.add("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImage2').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '3') {
			document.getElementById('newImage3').classList.remove("hide");
			document.getElementById('oldImage3').classList.add("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImage3').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '4') {
			document.getElementById('newImage4').classList.remove("hide");
			document.getElementById('oldImage4').classList.add("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImage1').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '5') {
			document.getElementById('newAudio').classList.remove("hide");
			document.getElementById('oldAudio').classList.add("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newAudio').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
	function readURLAdd(input, key) {
		if (key == '1') {
			document.getElementById('newImageAdd1').classList.remove("hide");
			console.log("hello");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImageAdd1').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '2') {
			document.getElementById('newImageAdd2').classList.remove("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImageAdd2').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '3') {
			document.getElementById('newImageAdd3').classList.remove("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImageAdd3').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '4') {
			document.getElementById('newImageAdd4').classList.remove("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newImageAdd4').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
		if (key == '5') {
			document.getElementById('newAudioAdd').classList.remove("hide");
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#newAudioAdd').attr('src', e.target.result).height(50);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<script src="/assets/css/admin.js">
</script>
<%@include file="footer.jsp"%>