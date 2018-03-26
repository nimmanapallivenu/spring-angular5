package com.app.dto;

public class UserDTO {

	private long id;

	private String name;

	private String email;

	private String pwd;

	private String address;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(long id, String name, String email, String password, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = password;
		this.address = address;

	}

	public UserDTO(String name, String password, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = password;
		this.address = address;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String password) {
		this.pwd = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
