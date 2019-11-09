package com.example.demo.model;

public class JwtUser {

	private String userName;
	private Long id;
	private String role;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRol() {
		return role;
	}
	public void setRol(String rol) {
		this.role = rol;
	}
}
