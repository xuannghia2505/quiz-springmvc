<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Admin - Quiz</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
	 <script src="/assets/js/admin.js"></script>
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/admin.css">

</head>

<body>

    <nav class="navbar fixed-top navbar-expand-md navbar-dark bg-primary mb-3">
        <div class="flex-row d-flex">
            <button type="button" class="navbar-toggler mr-2 " data-toggle="offcanvas" title="Toggle responsive left sidebar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="#" title="Free Bootstrap 4 Admin Template">Admin</a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse" id="collapsingNavbar">
            
            <ul class="navbar-nav ml-auto">
               
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container-fluid" id="main">
        <div class="row row-offcanvas row-offcanvas-left">
            <div class="col-md-3 col-lg-2 sidebar-offcanvas bg-light pl-0" id="sidebar" role="navigation">
            
                <ul class="nav flex-column sticky-top pl-0 pt-5 mt-3">
                   
                    <li class="nav-item" >
                        <a class="nav-link" href="#menuAcc" data-toggle="collapse" data-target="#menuAcc">Account</a>
                        <ul class="list-unstyled flex-column pl-3 collapse" id="menuAcc" aria-expanded="false">
                           <li class="nav-item"><a class="nav-link" href="/acc/list">List Acc</a></li>
                           <li class="nav-item"><a class="nav-link" href="#" onclick="createForm()">Create Acc</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#menuQuiz" data-toggle="collapse" data-target="#menuQuiz">Quiz</a>
                        <ul class="list-unstyled flex-column pl-3 collapse" id="menuQuiz" aria-expanded="false">
                           <li class="nav-item"><a class="nav-link" href="/quiz/list">List Quiz</a></li>
                           <li class="nav-item"><a class="nav-link" href="/question/list">List Question</a></li>
                        </ul>
                    </li>
                   
                </ul>
            </div>
            <!--/col-->
    
           
            <!--/main col-->
        </div>
         </div>
    
    