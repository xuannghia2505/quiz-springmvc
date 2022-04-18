<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/assets/css/style.css">
<script src="/assets/js/main.js"></script>
<title>${title}</title>
</head>
<body>
	<div class="container-fluid">

		<nav
			class="shadow-lg p-3 mb-5 navbar navbar-expand-lg navbar-light bg-light  ">
			<a class="navbar-brand textCustom" href="#"><img
				src="/assets/images/quiz-short.png" height="30"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item ${catelogy=='quizbaby' ? "active" : "" }">
						<a class="nav-link textCustom" href="list?catelogy=quizbaby">Quiz
							cho bé </a>
					</li>

					<li
						class="nav-item dropdown ${catelogy!='quizbaby' ? catelogy!='quizenglish' ? catelogy!='none' ? "active" : "" : "" : ""}">
						<a class="nav-link dropdown-toggle textCustom" href="#"
						id="navbarDropdown" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Toán </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="list?catelogy=toan1">Lớp 1</a> <a
								class="dropdown-item" href="list?catelogy=toan2">Lớp 2</a> <a
								class="dropdown-item" href="list?catelogy=toan3">Lớp 3</a> <a
								class="dropdown-item" href="list?catelogy=toan4">Lớp 4</a> <a
								class="dropdown-item" href="list?catelogy=toan5">Lớp 5</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="list?catelogy=toannc">Nâng cao</a>
						</div>
					</li>

					<li class="nav-item ${catelogy=='quizenglish' ? "active" : ""}">
						<a class="nav-link textCustom" href="list?catelogy=quizenglish">Tiếng
							anh </a>
					</li>
				</ul>
				<form action="search" method="post"
					class="form-inline my-2 my-lg-0 form-search-custom"
					style="margin-right: 400px;">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Tìm kiếm Quiz" aria-label="Search" name="keySearch" oninput="searchQuiz()" id="keySearch">
					<button class="btn btn-primary my-2 my-sm-0" type="submit">Tìm
						kiếm</button>
				</form>
				<c:if test="${sessionScope.user!=null}">
					<ul class="navbar-nav" >
						<li class="nav-item dropdown" style="margin-left:20px;"><a
							class="nav-link dropdown-toggle textCustom" href="#"
							id="navbarDropdown2" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> Xin chào <b> ${user.name }</b> </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown2" style="left:5px; width:50px;">
								<a class="dropdown-item" href="/quizDid">Quiz đã làm</a> <a
									class="dropdown-item" href="" data-toggle="modal" data-target="#modal">Thay đổi thông
									tin</a> <a class="dropdown-item" href="/logout">Đăng xuất</a>
							</div></li>
					</ul>
				</c:if>
				<c:if test="${sessionScope.user==null}">
					<a class="btn btn-secondary my-2 my-sm-0 ml-2" type="button"
						href="/register"> Đăng ký</a>
					<a class="btn btn-success my-2 my-sm-0 ml-2" type="button"
						href="/login"> Đăng nhập</a>
				</c:if>
			</div>
		</nav>