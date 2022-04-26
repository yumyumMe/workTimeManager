package com.example.demo.reposiory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class LoginUserDao implements LoginUserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Optional<User> findUser(String userName) {

		String sql = "SELECT * FROM user WHERE userName = ? AND deleteFlg != 1";

		// パラメータ設定用Map
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);

        User user = new User();

        // ログインユーザー情報の取得
        try {

        	Map<String, Object> result = jdbcTemplate.queryForMap(sql, param);

        	// 取得したユーザー情報をエンティティに詰める
        	user.setUserId((String) result.get("userId"));
        	user.setUserName((String) result.get("userName"));
        	user.setPassword((String)result.get("password"));
        	user.setUserKbn((String)result.get("userKbn"));
        	user.setDeleteFlg((int)result.get("deleteFlg"));

        }catch(EmptyResultDataAccessException e) {

        	Optional <User> userOpl = Optional.ofNullable(user);
            return userOpl;

        }

        Optional <User> userOpl = Optional.ofNullable(user);
        return userOpl;

	}
}
