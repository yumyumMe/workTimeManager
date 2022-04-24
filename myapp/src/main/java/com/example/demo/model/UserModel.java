package com.example.demo.model;

public class userModel {

	// ユーザー区分を判定し、表記を変更する
	public String checkUserKbn(String user) {

		String userKbn = "";

		if (user.equals("admin")) {
			userKbn = "管理者";
		} else {
			userKbn = "一般";
		}

		return userKbn;

	}

}
