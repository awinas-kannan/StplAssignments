package com.project.awinas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logintable")
public class LoginModel {

	@Id
	@Column(name="USERID")
	private int userid;
	
	@Column(name="PASSWORD")
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
