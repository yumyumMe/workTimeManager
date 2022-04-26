package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserForm;
import com.example.demo.reposiory.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;

	// 全ユーザー取得
	public List<Map<String, Object>> selectAll() {

		List<Map<String, Object>> list = userDao.selectAll();
		return list;

	}

	// ユーザー登録
	public int registUser(UserForm userForm) {

		// 初期値としてdeleteFlgに0を設定する
		userForm.setDeleteFlg(0);

		/* userIdを発行するため
		 * 0～9の乱数5桁を生成
		 */
		Random rand = new Random();
		int userIdNum = 0;
		for (int i = 0; i < 5; i++) {
			int randNum = rand.nextInt(10);
			userIdNum = randNum;
		}

		// 5桁のランダム数値をユーザーIDとする
		userForm.setUserId(String.valueOf(userIdNum));

		int user = userDao.registUser(userForm);
		return user;

	}

	// ユーザー更新
	public int updateUser(UserForm userForm) {

		int user = userDao.updateUser(userForm);
		return user;

	}

	// ユーザー選択(ID検索)
	public Map<String, Object> selectUser(String userId) {

		Map<String, Object> user = userDao.selectUserId(userId);

		return user;

	}

	// ユーザー論理削除(deleteFlg=1)
	public int updateDeleteFlg(String[] userId) {

		int user = userDao.updateDeleteFlg(userId);
		return user;

	}

	// ユーザー削除
	public int deleteUser(String[] userId) {

		int user = userDao.deleteUser(userId);
		return user;

	}

}
