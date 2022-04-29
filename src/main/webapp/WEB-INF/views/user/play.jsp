
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<link rel="stylesheet" href="/assets/css/play.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>Quiz-App</title>

</head>
<body>


	<a href="/" id="iconHome"><i class="fa fa-home" aria-hidden="true"></i>
		<span class="tooltiptext">Nhà</span></a>
	<button class="btn-outline-success" id="iconPre" onclick="preQ()" ><i class="fa fa-arrow-circle-left"
		aria-hidden="true"></i><span class="tooltiptext">Lui</span></button>
	<button class="btn-outline-success" id="iconNext" onclick="nextQ()"><i class="fa fa-arrow-circle-right"
		aria-hidden="true"></i> <span class="tooltiptext">Tới</span></button>
	<div id="backgroundPlay">
		<div class="container">
			<input id="questionSize" value="${listQuestions.size()}" hidden></input>

			<c:forEach items="${listQuestions}" var="question" varStatus="index">
				<div id="question-container${index.index}" class="hide">
					<div style="color: red; font-size: 30px;">Câu hỏi số
						${index.index+1}</div>
					<c:if test="${question.question!=null && question.answerA!=null }">
						<div class="question">${question.question }</div>
						<div class="answer-buttons" class="btn-grid">
							<button class="btn" onclick="selectAnswer('A')">${question.answerA}</button>
							<button class="btn" onclick="selectAnswer('B')">${question.answerB}</button>
							<br />
							<button class="btn" onclick="selectAnswer('C')">${question.answerC}</button>
							<button class="btn" onclick="selectAnswer('D')">${question.answerD}</button>
						</div>
					</c:if>
					<c:if test="${question.audio!=null }">
						<audio src="${question.audio }"
							style="margin-top: 50px;" controls id="audio${index.index}"></audio>
						<div class="answer-buttons" class="btn-grid"
							style="margin-top: 30px;">
							<img class="btn2" onclick="selectAnswer('A')"
								src="${question.answerA}" width="300px" height="200px"
								style="cursor: pointer;"></img> <img class="btn2"
								onclick="selectAnswer('B')" src="${question.answerB}"
								width="300px" height="200px" style="cursor: pointer;"></img> <br />
							<img class="btn2" onclick="selectAnswer('C')"
								src="${question.answerC}" width="300px" height="200px"
								style="cursor: pointer;"></img> <img class="btn2"
								onclick="selectAnswer('D')" src="${question.answerD}"
								width="300px" height="200px" style="cursor: pointer;"></img>
						</div>
					</c:if>
					<c:if test="${question.answerA==null }">
						<div id="questionEnglish${index.index }" class="question"
							value="${question.question }">${question.question }
							<div><img src="${question.image}" alt="image" height="200"></div>
							</div>

					</c:if>

				</div>
				<input id="correctAnswer${index.index}"
					value="${question.correctAnswer}" hidden></input>
				
			</c:forEach>
			<div class="hide" id="controlTalk" style="margin-top: -40px;">
				<div class="hide"
					style="font-size: 25px; font-weight: 700px; position:absolute; top:460px; left:600px; "
					id="hearing">Đang lắng nghe...</div>

				<button class="btn btn-danger" id="btnTalk">click to talk</button>
				<!-- <div style="margin-top: 50px;">
					<button class="btnPre btn-danger" id="btnPre">Previous</button>
					<button class="btnNext btn-success" id="btnNext">Next</button>
				</div> -->
			</div>
			<audio src="/assets/audio/correct-answer.mp3" id="correctAnswer"></audio>
			<audio src="/assets/audio/wrong-answer.mp3" id="wrongAnswer"></audio>


			<div id="tableResult" class="hide">
				<form action="hoanthanh" method="post">
					<h1 class="quizEnd">Đã hoàn thành bài</h1>
					<div class="containerRs stats">
						<h1 style="color: red; font-wieght: 800;">Bạn đã làm rất
							tốt,tiếp tục cố gắng nhé!</h1>
						<h2 style="color: blue;">
							Điểm số: <input
								style="width: 60px; color: red; text-align: center;"
								id="resultQuiz" name="resultQuiz" readonly></input> %
						</h2>
						<h2 style="color: green;">Bạn đã hoàn thành bài thi hãy nhấn
							hoàn thành bài thi để tiếp tục</h2>
					</div>

					<div>
						<button class="backhome" type="submit" style="cursor: pointer;">Hoàn
							thành bài thi</button>
					</div>
					<input name="quizID" value="${quizID}" hidden></input>
				</form>

			</div>

		</div>
	</div>
	<div class="alert hide" id="alertC1">
		<span id="msgCorrect1">bạn
			trả lời gần đúng rồi nè</span>

		<div class="close-btn">
			<span style="color: white; font-size: 22px;">X</span>
		</div>
	</div>
	<div class="alert hide" id="alertC2">
		<span id="msgCorrect2">bạn trả lời đúng rồi, giỏi quá</span>

		<div class="close-btn">
			<span style="color: white; font-size: 22px;">X</span>
		</div>
	</div>
	<div class="alert hide" id="alertW">
		<span id="msgWrong">sai
			mất tiêu rùii, cố gắng lần sau nha</span>
		<div class="close-btn">
			<span style="color: white; font-size: 22px;">X</span>
		</div>
	</div>

	<script>
		$('.close-btn').click(function() {
			$('.alert').removeClass("show");
			$('.alert').addClass("hide");
		});
	</script>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
	<script rel="stylesheet" src="/assets/js/play.js"></script>

</body>
</html>