/*
 * (C) Copyright 2022 Fresher Academy. All Rights Reserved
 *
 * @author NghiaHX
 * @birthDate 25/05/2000
 * @date 2022-04-17
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
public class QuizUser {
	private int quizID;
	private String name;
	private String catelogy;
	private String image;
	private int numberQuestion;
	private int score;
	public QuizUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuizUser(int quizID, String name, String catelogy, String image, int numberQuestion, int score) {
		super();
		this.quizID = quizID;
		this.name = name;
		this.catelogy = catelogy;
		this.image = image;
		this.numberQuestion = numberQuestion;
		this.score = score;
	}
	public int getQuizID() {
		return quizID;
	}
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatelogy() {
		return catelogy;
	}
	public void setCatelogy(String catelogy) {
		this.catelogy = catelogy;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNumberQuestion() {
		return numberQuestion;
	}
	public void setNumberQuestion(int numberQuestion) {
		this.numberQuestion = numberQuestion;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}


