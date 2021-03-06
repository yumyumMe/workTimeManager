package com.example.demo.model;

import javax.validation.constraints.NotBlank;

public class UserForm {

	public String userId;

	@NotBlank(message="※名前を入力してください")
	public String userName;

	@NotBlank(message="※パスワードを入力してください")
	public String password;

	public String userKbn;

	public int deleteFlg;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserKbn() {
		return userKbn;
	}

	public void setUserKbn(String userKbn) {
		this.userKbn = userKbn;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

}
