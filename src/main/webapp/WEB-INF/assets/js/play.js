
// alert

let alertC1 = document.getElementById('alertC1');
let alertC2 = document.getElementById('alertC2');
let alertW = document.getElementById('alertW');
let hearing = document.getElementById('hearing');

if(document.getElementById('questionEnglish0')!=null){
	document.getElementById('controlTalk').classList.remove('hide');
}
// score
let score=0, currentQuestionIndex =0;
let questionSize = document.getElementById('questionSize').value;
let questionContainer = document.getElementById(`question-container${currentQuestionIndex}`);
const correctAudio = document.getElementById('correctAnswer');
const wrongAudio = document.getElementById('wrongAnswer');
questionContainer.classList.remove('hide');

function nextQuestion(){
	currentQuestionIndex++;
    questionContainer.classList.add('hide');
    questionContainer = document.getElementById(`question-container${currentQuestionIndex}`)
    questionContainer.classList.remove('hide');
}
function selectAnswer(answer) {
    let correct=document.getElementById(`correctAnswer${currentQuestionIndex}`).value;
    if(correct==answer){
      score++;
  	alertC2.classList.add('show');
    alertC2.classList.remove('hide');
    alertC2.classList.add('showAlert');      
    correctAudio.play();
    setTimeout(function(){
 	   alertC1.classList.remove('show');
 	   alertC1.classList.add('hide');
      }, 3000);
    }else{
    	alertW.classList.add('show');
        alertW.classList.remove('hide');
        alertW.classList.add('showAlert'); 
        wrongAudio.play();
        setTimeout(function(){
     	   alertW.classList.remove('show');
     	   alertW.classList.add('hide');
          }, 3000);
    }
   
    if(currentQuestionIndex<questionSize-1){
      nextQuestion();
    }else{
    	questionContainer.classList.add('hide');
    	document.getElementById('resultQuiz').value=(score*100/questionSize).toFixed(); 
    	document.getElementById('tableResult').classList.remove('hide');
    	
   
    }
   
  }

// english


// LevenshteinDistance

let distance = (a,b)=>{
	 let costs = new  Array(b.length + 1);
	 for (let j = 0; j < costs.length; j++)
         costs[j] = j;
     for (let i = 1; i <= a.length; i++) {
         // j == 0; nw = lev(i - 1, j)
         costs[0] = i;
         let nw = i - 1;
         for (let j = 1; j <= b.length; j++) {
        	 let cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
             nw = costs[j];
             costs[j] = cj;
         }
     }
     return costs[b.length];
}


var message = document.querySelector('#message');

var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
var SpeechGrammarList = SpeechGrammarList || webkitSpeechGrammarList;

var grammar = '#JSGF V1.0;'

var recognition = new SpeechRecognition();
var speechRecognitionList = new SpeechGrammarList();
speechRecognitionList.addFromString(grammar, 1);
recognition.grammars = speechRecognitionList;
recognition.interimResults = false;

recognition.onresult = function(event) {

   	hearing.classList.add('hide');
    var lastResult = event.results.length - 1;
    var content = event.results[lastResult][0].transcript;
    const questionEnglish = document.getElementById(`questionEnglish${currentQuestionIndex}`);
    console.log(content);
    var answer = questionEnglish.getAttribute('value').toLowerCase();
    console.log(answer);
  
    if(distance(content,answer)==0){		 
    		alertC2.classList.add('show');
           alertC2.classList.remove('hide');
           alertC2.classList.add('showAlert');      
           correctAudio.play();
           setTimeout(function(){
        	   alertC1.classList.remove('show');
        	   alertC1.classList.add('hide');
             }, 3000);
    	
    }else if(distance(content,answer)==1){
    	alertC1.classList.add('show');
        alertC1.classList.remove('hide');
        alertC1.classList.add('showAlert');      
        correctAudio.play();
        setTimeout(function(){
     	   alertC2.classList.remove('show');
     	   alertC2.classList.add('hide');
          }, 3000);
    } 
    
    else{
    	alertW.classList.add('show');
        alertW.classList.remove('hide');
        alertW.classList.add('showAlert'); 
        wrongAudio.play();
        setTimeout(function(){
     	   alertW.classList.remove('show');
     	   alertW.classList.add('hide');
          }, 3000);
    	
    }
};

recognition.onspeechend = function() {
    recognition.stop();
  
};

recognition.onerror = function(event) {
    message.textContent = 'Error occurred in recognition: ' + event.error;
}

document.querySelector('#btnTalk').addEventListener('click', function(){
    recognition.start();
    hearing.classList.remove('hide');
});

// pre next
document.querySelector('#btnPre').addEventListener('click', function(){
	if(currentQuestionIndex>0){
		currentQuestionIndex--;
	    questionContainer.classList.add('hide');
	    questionContainer = document.getElementById(`question-container${currentQuestionIndex}`)
	    questionContainer.classList.remove('hide');
	}
	
});
document.querySelector('#btnNext').addEventListener('click', function(){
	if(currentQuestionIndex<questionSize-1){
		currentQuestionIndex++;
	    questionContainer.classList.add('hide');
	    questionContainer = document.getElementById(`question-container${currentQuestionIndex}`)
	    questionContainer.classList.remove('hide');
	}
});
