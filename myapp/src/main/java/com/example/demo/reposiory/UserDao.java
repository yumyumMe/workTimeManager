package com.example.demo.reposiory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserForm;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// 全ユーザー取得
	public List<Map<String, Object>> selectAll() {

		String sql = "SELECT *  FROM user WHERE deleteFlg != 1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		return list;

	}

	// ユーザー登録
	public int registUser(UserForm userForm) {

		String sql = "INSERT INTO user (userId, userName, password, userKbn, deleteFlg) VALUES(?, ?, ?, ?, ?)";
		Object[] param = {userForm.getUserId(), userForm.getUserName(), userForm.getPassword(), userForm.getUserKbn(), userForm.getDeleteFlg()};
		int user = jdbcTemplate.update(sql, param);

		return user;

	}

	// ユーザー更新
	public int updateUser(UserForm userForm) {

		String sql = "UPDATE user SET userName=?, password=?, userKbn=? WHERE userId=?";
		Object[] param = {userForm.getUserName(), userForm.getPassword(), userForm.getUserKbn(), userForm.getUserId()};
		int user = jdbcTemplate.update(sql, param);

		return user;

	}

	// ユーザー選択(ID検索)
	public Map<String, Object> selectUserId(String userId) {

		String sql = "SELECT * FROM user WHERE userId = ? AND deleteFlg != 1";
		Object[] param = {userId};
		Map<String, Object> user = jdbcTemplate.queryForMap(sql, param);

		return user;

	}

	// ユーザー論理削除(deleteFlg=1)
	public int updateDeleteFlg(String[] userId) {

		int user = 0;

		for(String id: userId) {
			String sql = "UPDATE user SET deleteFlg=1 WHERE userId=?";
			Object[] param = {id};
			user += jdbcTemplate.update(sql, param);
		}

		return user;

	}

	// ユーザー削除
	public int deleteUser(String[] userId) {

		int user = 0;

		for (String id: userId) {
			String sql = "DELETE FROM user WHERE userId = ?";
			Object[] param = {id};
			user += jdbcTemplate.update(sql, param);
		}

		return user;

	}

}
