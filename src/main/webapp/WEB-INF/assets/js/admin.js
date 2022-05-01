
function ValidationAdd1(){
	let input1 = document.getElementById('exampleInput6').value;
	if(input1=='none'){
		document.getElementById('errorInput6').innerText = "Không được bỏ trống";
		return false;
	}else{
		document.getElementById('errorInput6').innerText = "";
	}
	return true;
}
function ValidationAdd2(){
	let image = document.getElementById('imageQuestion2');

	if(image.value==''){
		document.getElementById('imageQuestionError2').innerText = "Chưa chọn File ảnh";
		return false;
	}else{
		document.getElementById('imageQuestionError2').innerText = "";
	}
	return true;
	
}
function ValidationAdd3(){
	let flag=true;
	let image1 = document.getElementById('exampleInputAdd1');
	let image2 = document.getElementById('exampleInputAdd2');
	let image3 = document.getElementById('exampleInputAdd3');
	let image4 = document.getElementById('exampleInputAdd4');
	let answer = document.getElementById('exampleInputAdd5');
	let audio = document.getElementById('exampleInputAdd6');
	if(image1.value==''){
		document.getElementById('exampleInputError1').innerText = "Chưa chọn File ảnh";
		flag=false;
	}else{
		document.getElementById('exampleInputError1').innerText = "";
	}
	if(image2.value==''){
		document.getElementById('exampleInputError2').innerText = "Chưa chọn File ảnh";
		flag=false;
	}else{
		document.getElementById('exampleInputError2').innerText = "";
	}
	
	if(image3.value==''){
		document.getElementById('exampleInputError3').innerText = "Chưa chọn File ảnh";
		flag=false;
	}else{
		document.getElementById('exampleInputError3').innerText = "";
	}
	
	if(image4.value==''){
		document.getElementById('exampleInputError4').innerText = "Chưa chọn File ảnh";
		flag=false;
	}else{
		document.getElementById('exampleInputError4').innerText = "";
	}
	if(answer.value=='none'){
		document.getElementById('exampleInputError5').innerText = "Chưa chọn đáp án đúng";
		flag=false;
	}else{
		document.getElementById('exampleInputError5').innerText = "";
	}
	if(audio.value==''){
		document.getElementById('exampleInputError6').innerText = "Chưa chọn File audio";
		flag=false;
	}else{
		document.getElementById('exampleInputError6').innerText = "";
	}
	return flag;
	
}