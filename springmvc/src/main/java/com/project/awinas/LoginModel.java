package com.project.awinas;

public class LoginModel {

	private int userid;
	private String pwd; 
	
	public LoginModel()
	{
		//do
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
