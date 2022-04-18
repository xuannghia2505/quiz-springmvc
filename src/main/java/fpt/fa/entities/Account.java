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
public class Account {
	private String username;
	private String password;
	private String name;
	private int age;
	private int role;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String username, String password, String name, int age, int role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.age = age;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
}


