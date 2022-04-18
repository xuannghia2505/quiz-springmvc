<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/assets/css/home.css" >
<title>Chuẩn bị</title>
</head>
<body>


<div id="home">
            <section style="padding-top:20px;">
                <div style= "text-align:center;">
                   <img src="${quiz.image }" height="100" >
                </div>
                <h1>${quiz.name }</h1>
                <div class="play-button-container">
                    <ul>
                        <li>
                            <a class="play-button" href="/play?quizID=${quiz.quizID}">Bắt đầu</a> 
                        </li>
                    </ul>
                </div>
                <div class="auth-container">
                    <a href="/" class="auth-buttons" id="login-button">Trang chủ</a>
                    <a href="/login" class="auth-buttons" id="signup-button">Đăng nhập</a>
                </div>
            </section>
        </div>
</body>
</html>