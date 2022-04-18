/*
 * (C) Copyright 2022 Fresher Academy. All Rights Reserved
 *
 * @author NghiaHX
 * @birthDate 25/05/2000
 * @date 2022-04-05
 * version 1.0
 */
/**
 * 
 */
package fpt.fa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fpt.fa.entities.Account;
import fpt.fa.entities.Quiz;
import fpt.fa.entities.QuizUser;

/**
 * @author Admin
 *
 */
public class UserDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Account> getAllAcc() throws SQLException {
		List<Account> listAccounts = new ArrayList<>();
		try {
			conn = new DBConnect().getConnection();
			
			String query = "select * from Account where role>0";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listAccounts.add(new Account(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getInt(5)));
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
		return listAccounts;
	}
	public List<Account> getListAccByPage(int page) throws SQLException {
		List<Account> listAccounts = new ArrayList<>();
		try {
			conn = new DBConnect().getConnection();
			
			String query = "SELECT * FROM Account WHERE role>0 ORDER BY username OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (page-1)*3);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listAccounts.add(new Account(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getInt(5)));
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
		return listAccounts;
	}
	
	public int getCountAcc() throws SQLException {
		int dem=0;
		try {
			conn = new DBConnect().getConnection();
			
			String query = "select count(1) from Account where role>0";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dem=rs.getInt(1);
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
		return dem;
	}
	public List<Account> findAccByUsername(String key) throws SQLException {
		List<Account> listAccounts = new ArrayList<>();
		try {
			conn = new DBConnect().getConnection();
			
			String query = "select * from Account where role>0 and username like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%"+ key+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listAccounts.add(new Account(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getInt(5)));
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
		return listAccounts;
	}
	
	public Account getAcc(String username,String password) throws SQLException {
		Account user = null;
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Account where username=? and password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new Account(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getInt(5));
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
		return user;
	}
	public Account getAccByUsername(String username) throws SQLException {
		Account user = null;
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Account where username=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new Account(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getInt(5));
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
		return user;
	}
	public boolean findAcc(String username) throws SQLException {
		Account user = null;
		try {
			conn = new DBConnect().getConnection();

			String query = "select * from Account where username=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new Account(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getInt(5));
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
		if(user==null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void insertAcc(String username,String password,String name,int age) throws SQLException {
	
		try {
			conn = new DBConnect().getConnection();

			String query = "insert into Account values (?,?,?,?,1)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setNString(3, name);
			ps.setInt(4, age);
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
	
	public void updateAcc(String username,String password,String name,int age) throws SQLException {
		
		try {
			conn = new DBConnect().getConnection();

			String query = "update Account\r\n" + 
					"set password=?,name=?,age=?\r\n" + 
					"where username=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, password);
			ps.setNString(2, name);
			ps.setInt(3, age);
			ps.setString(4, username);
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
public void updateAcc(String username,String name,int age) throws SQLException {
		
		try {
			conn = new DBConnect().getConnection();

			String query = "update Account\r\n" + 
					"set name=?,age=?\r\n" + 
					"where username=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNString(1, name);
			ps.setInt(2, age);
			ps.setString(3, username);
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
	
	public void deleteAcc(String username) throws SQLException {

		try {
			conn = new DBConnect().getConnection();

			String query = "delete from Account\r\n" + "where username=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
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
	
	public List<QuizUser> getQuizOfUser(String username) throws SQLException{
		List<QuizUser> list = new ArrayList<QuizUser>();
		String query = "\r\n" + 
				"select q.*,uq.score\r\n" + 
				"from User_Quiz as uq join Quiz as q on uq.quizID=q.quizID\r\n" + 
				"where username=?";
		try {
			conn = new DBConnect().getConnection();

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new QuizUser(rs.getInt(1),rs.getNString(2),rs.getNString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
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
		return list;
	}
}


