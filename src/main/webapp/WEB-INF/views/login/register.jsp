<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Đăng nhập</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="/login/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="/login/images/img-01.png" alt="IMG">
				</div>

				<form action="register" method="post"	 class="login100-form validate-form" onsubmit="return Validation();">
					<span class="login100-form-title">
						Đăng ký
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Valid username is required">
					 
						<input id="username" class="input100" type="text" name="username" placeholder="Username" oninput="checkUser()" >
						<small style="color:red; display:block; text-align:center;" id="userValid"></small>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input id="password" class="input100" type="password" name="password" placeholder="Password">
						 <small style="color:red;  display:block; text-align:center;" id="password1"></small>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input" data-validate = "Reassword is required">
						<input id="repassword" class="input100" type="password" name="repassword" placeholder="RePassword">
						 <small style="color:red; display:block; text-align:center;" id="repassword1"></small>
						  <small style="color:red; display:block; text-align:center;" id="rpassword"></small>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input" data-validate = "Hoten is required">
						<input id="name" class="input100" type="text" name="name" placeholder="Name">
						 <small style="color:red; display:block; text-align:center;" id="name1"></small>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-edit" aria-hidden="true"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input" data-validate = "Hoten is required">
						<input id="age" class="input100" type="number" name=age placeholder="Age">
						 <small style="color:red; display:block; text-align:center;" id="age1"></small>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-calendar" aria-hidden="true"></i>
						</span>
					</div>
					<small style="color:red; text-align:center;">${message}</small>
				
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Đăng ký
						</button>
					</div>

					<div class="text-center p-t-12">
						
						<a class="txt2" href="/">
							Trở về
						</a>
					</div>

					<div class="text-center p-t-136">
						<a class="txt2" href="/login">
							Login
							<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/bootstrap/js/popper.js"></script>
	<script src="/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
		function Validation(){
			let flag=true;
			let username = document.getElementById('username').value;
			let password = document.getElementById('password').value;
			let repassword = document.getElementById('repassword').value;
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
			if(repassword==""){
				flag=false;
				document.getElementById('repassword1').innerText="Không được bỏ trống";
			}else{
				document.getElementById('repassword1').innerText="";
			}
			if(password!=repassword){
				flag=false;
				document.getElementById('rpassword').innerText="Nhập lại password không giống nhau";
			}else{
				document.getElementById('rpassword').innerText="";
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
	</script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>