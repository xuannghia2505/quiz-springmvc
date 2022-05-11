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
package fpt.fa.dao;

import java.security.spec.PSSParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fpt.fa.entities.Question;
import fpt.fa.entities.Quiz;

/**
 * @author Admin
 *
 */
public class QuizDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-11
	 * @param catelogy
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getAllQuiz() throws SQLException {
		ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Quiz";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listQuiz.add(new Quiz(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getInt(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listQuiz;
	}
	public ArrayList<Quiz> getAllQuizIgnoreEnglish() throws SQLException {
		ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Quiz where catelogy!='quizenglish'";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listQuiz.add(new Quiz(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getInt(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listQuiz;
	}
	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-04
	 * @param catelogy
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getQuizByCatelogy(String catelogy) throws SQLException {
		ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Quiz where catelogy=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, catelogy);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listQuiz.add(new Quiz(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getInt(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listQuiz;
	}

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-04
	 * @param catelogy
	 * @return
	 * @throws SQLException
	 */
	public Quiz getQuizById(int id) throws SQLException {
		Quiz quiz = null;
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Quiz where quizID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quiz = new Quiz(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getInt(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return quiz;
	}

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-04
	 * @param catelogy
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Question> getQuestionByQuizID(int id) throws SQLException {
		ArrayList<Question> listQuestion = new ArrayList<Question>();
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Question where quizID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listQuestion.add(new Question(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4),
						rs.getNString(5), rs.getNString(6), rs.getNString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10),rs.getString(11)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listQuestion;
	}
	
	public Question getQuestionByID(int id) throws SQLException {
		Question question = null;
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Question where questionID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				question = new Question(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4),
						rs.getNString(5), rs.getNString(6), rs.getNString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10),rs.getString(11));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return question;
	}

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-04
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String getTitleByCatelogy(String catelogy) throws SQLException {
		String title = "";
		try {
			conn = new DBConnect().getConnection();

			String query = "select title from TitleQuiz where catelogy=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, catelogy);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				title = rs.getNString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return title;
	}

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-05
	 * @param username
	 * @param quizID
	 * @param score
	 * @return
	 * @throws SQLException
	 */
	public boolean insertResult(String username, int quizID, int score) throws SQLException {
		int kq = -1;
		try {
			conn = new DBConnect().getConnection();

			String query = "delete from User_Quiz\r\n" + "  where username=? and quizID=?\r\n"
					+ "  insert into User_Quiz(username,quizID,score) values (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, username);
			ps.setInt(2, quizID);
			ps.setNString(3, username);
			ps.setInt(4, quizID);
			ps.setInt(5, score);
			kq = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		if (kq == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-05
	 * @param catelogy
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getQuizHint(int age) throws SQLException {
		ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();

		String query = "";
		int ageHint = 1;

		try {

			conn = new DBConnect().getConnection();
			if (age > 5) {
				ageHint = age - 5;
				query = "select * from Quiz where catelogy like ?";
				ps = conn.prepareStatement(query);
				ps.setString(1, "%" + ageHint + "%");
			} else {
				query = "select * from Quiz where catelogy='quizbaby' ";
				ps = conn.prepareStatement(query);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listQuiz.add(new Quiz(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getInt(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listQuiz;
	}

	/**
	 * 
	 * @author NghiaHX
	 * @birthDate 25/05/2000
	 * @date 2022-04-08
	 * @param age
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getQuizbyName(String key) throws SQLException {
		ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
		String query = "select * from Quiz where name like ?";
		try {

			conn = new DBConnect().getConnection();
			ps = conn.prepareStatement(query);
			ps.setNString(1, "%" + key + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listQuiz.add(new Quiz(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getInt(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listQuiz;
	}

	public void insertQuiz(String name, String catelogy, String image) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "insert into Quiz values (?,?,?,0)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, catelogy);
			ps.setNString(3, image);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void updateQuiz(int quizID, String name, String catelogy, String image, int numberQuestion)
			throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Quiz\r\n" + "set name=?,catelogy=?,image=?,numberQuestion=?\r\n" + "where quizID=? ";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, catelogy);
			ps.setNString(3, image);
			ps.setInt(4, numberQuestion);
			ps.setInt(5, quizID);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void updateQuizNoImage(int quizID, String name, String catelogy, int numberQuestion) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Quiz\r\n" + "set name=?,catelogy=?,numberQuestion=?\r\n" + "where quizID=? ";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, catelogy);
			ps.setInt(3, numberQuestion);
			ps.setInt(4, quizID);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public ArrayList<String> getAllCatelogyQuiz() throws SQLException {
		ArrayList<String> listCatelogy = new ArrayList<String>();
		try {
			conn = new DBConnect().getConnection();

			String query = "select catelogy from TitleQuiz";
			PreparedStatement ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				listCatelogy.add(rs.getNString(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listCatelogy;
	}
	public ArrayList<String> getAllCatelogyQuizIgnoreEnglish() throws SQLException {
		ArrayList<String> listCatelogy = new ArrayList<String>();
		try {
			conn = new DBConnect().getConnection();

			String query = "select catelogy from TitleQuiz where catelogy!='quizenglish' ";
			PreparedStatement ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				listCatelogy.add(rs.getNString(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return listCatelogy;
	}
	public void deleteQuiz(int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "delete from Question\r\n" + "where quizID=?\r\n" + "delete from Quiz\r\n"
					+ "where quizID=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, quizID);
			ps.setInt(2, quizID);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void deleteQuestion(int questionID) throws SQLException {
		updateNumberByQuestionID(questionID);
		try {
			conn = new DBConnect().getConnection();

			String query = "delete from Question\r\n" + "where questionID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, questionID);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void addQuestion(int quizID, String question, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer, String image, String audio,String questionType) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "insert into Question values\r\n" + "(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, question);
			ps.setNString(2, answerA);
			ps.setNString(3, answerB);
			ps.setNString(4, answerC);
			ps.setNString(5, answerD);
			ps.setNString(6, correctAnswer);
			ps.setString(7, image);
			ps.setString(8, audio);
			ps.setInt(9, quizID);
			ps.setString(10, questionType);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			updateNumberByQuizID(quizID);
		}

	}

	public void updateNumberByQuizID(int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Quiz \r\n" + "set numberQuestion= questionUpdate.number\r\n"
					+ "from (select count(1) as number from Question where quizID=?) as questionUpdate\r\n"
					+ "where quizID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, quizID);
			ps.setInt(2, quizID);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void updateNumberByQuestionID(int questionID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "declare @quizID as int \r\n"
					+ "set @quizID = (select quizID from Question where questionID=?)\r\n" + "update Quiz\r\n"
					+ "set numberQuestion= questionUpdate.number\r\n" + "from\r\n"
					+ "(select count(1)-1 as number from Question \r\n" + "where quizID=@quizID) as questionUpdate\r\n"
					+ "where Quiz.quizID=@quizID";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, questionID);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void updateQuestion(int questionID, String question, String answerA, String answerB, String answerC,
			String answerD, String correctAnswer, String image, String audio, int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Question\r\n"
					+ "set question=?,answerA=?,answerB=?,answerC=?,answerD=?,correctAnswer=?,image=?,audio=?,quizID=?\r\n"
					+ "where questionID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, question);
			ps.setNString(2, answerA);
			ps.setNString(3, answerB);
			ps.setNString(4, answerC);
			ps.setNString(5, answerD);
			ps.setNString(6, correctAnswer);
			ps.setString(7, image);
			ps.setString(8, audio);
			ps.setInt(9, quizID);
			ps.setInt(10, questionID);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public int getQuizIDbyQuestionID(int questionID) throws SQLException {
		int id = 0;
		try {
			conn = new DBConnect().getConnection();

			String query = "select quizID from Question where questionID=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, questionID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return id;
	}

	public void updateQuestion1(int questionID, String question, String answerA, String answerB, String answerC,
			String answerD, String correctAnswer, int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Question\r\n"
					+ "set question=?,answerA=?,answerB=?,answerC=?,answerD=?,correctAnswer=?,quizID=?\r\n"
					+ "where questionID=?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, question);
			ps.setString(2, answerA);
			ps.setString(3, answerB);
			ps.setString(4, answerC);
			ps.setString(5, answerD);
			ps.setString(6, correctAnswer);
			ps.setInt(7, quizID);
			ps.setInt(8, questionID);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}
	public void updateQuestion2(int questionID, String question,  String correctAnswer,String image, int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Question\r\n" + 
					"set question=?,correctAnswer=?,image=?,quizID=?\r\n" + 
					"where questionID=?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, question);
			ps.setString(2, correctAnswer);
			ps.setString(3, image);
			ps.setInt(4, quizID);
			ps.setInt(5, questionID);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}
	public void updateQuestion2NoImage(int questionID, String question,  String correctAnswer, int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Question\r\n" + 
					"set question=?,correctAnswer=?,quizID=?\r\n" + 
					"where questionID=?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, question);
			ps.setString(2, correctAnswer);
			ps.setInt(3, quizID);
			ps.setInt(4, questionID);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public void updateQuestion3(int questionID,  String answerA, String answerB, String answerC,
			String answerD, String correctAnswer,String audio, int quizID) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "update Question\r\n" + 
					"set answerA=?,answerB=?,answerC=?,answerD=?,correctAnswer=?,audio=?,quizID=?\r\n" + 
					"where questionID=?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, answerA);
			ps.setString(2, answerB);
			ps.setString(3, answerC);
			ps.setString(4, answerD);
			ps.setString(5, correctAnswer);
			ps.setString(6, audio);
			ps.setInt(7, quizID);
			ps.setInt(8, questionID);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}
	
	public void addQuestion1( String question, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer,int quizID,String questionType) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "insert into Question(question,answerA,answerB,answerC,answerD,correctAnswer,quizID,questionType) values\r\n" + 
					"(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, question);
			ps.setNString(2, answerA);
			ps.setNString(3, answerB);
			ps.setNString(4, answerC);
			ps.setNString(5, answerD);
			ps.setNString(6, correctAnswer);
			ps.setInt(7, quizID);
			ps.setString(8, questionType);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			updateNumberByQuizID(quizID);
		}

	}
	public void addQuestion2(String question,String image,int quizID,String questionType) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "insert into Question(question,correctAnswer,image,quizID,questionType) values\r\n" + 
					"(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, question);
			ps.setNString(2, question.toLowerCase());
			ps.setNString(3, image);
			ps.setInt(4, quizID);
			ps.setString(5, questionType);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			updateNumberByQuizID(quizID);
		}

	}
	public void addQuestion3(String answerA, String answerB, String answerC, String answerD,
			String correctAnswer,String audio,int quizID,String questionType) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "insert into Question(answerA,answerB,answerC,answerD,correctAnswer,audio,quizID,questionType) values\r\n" + 
					"(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
		
			ps.setNString(1, answerA);
			ps.setNString(2, answerB);
			ps.setNString(3, answerC);
			ps.setNString(4, answerD);
			ps.setNString(5, correctAnswer);
			ps.setNString(6, audio);
			ps.setInt(7, quizID);
			ps.setString(8, questionType);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			updateNumberByQuizID(quizID);
		}

	}
	
}
