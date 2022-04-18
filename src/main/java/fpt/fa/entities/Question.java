/*
 * (C) Copyright 2022 Fresher Academy. All Rights Reserved
 *
 * @author NghiaHX
 * @birthDate 25/05/2000
 * @date 2022-04-04
 * version 1.0
 */
/**
 * 
 */
package fpt.fa.entities;

/**
 * @author Admin
 *
 */
public class Question {
	private int questionID;
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String correctAnswer;
	private String image;
	private String audio;
	private int quizID;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int questionID, String question, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer, String image, String audio, int quizID) {
		super();
		this.questionID = questionID;
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.correctAnswer = correctAnswer;
		this.image = image;
		this.audio = audio;
		this.quizID = quizID;
	}
	public Question( String question, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer, String image, String audio, int quizID) {
		super();
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.correctAnswer = correctAnswer;
		this.image = image;
		this.audio = audio;
		this.quizID = quizID;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public int getQuizID() {
		return quizID;
	}
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	
}


