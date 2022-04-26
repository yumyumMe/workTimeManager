package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.reposiory.LoginUserRepository;

/* ログイン機能専用のServiceクラス
 * Spring SecurityのUserDatailsServiceを継承して
 * loadUserByUsernameメソッドの引数(ユーザー名)にヒットするデータを検索
 *
 * ※フォームの値と照合する結果を得られたときUserDetailsオブジェクトを生成
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	LoginUserRepository loginUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> loginUser = loginUserRepository.findUser(userName);

        if(!loginUser.isPresent()) {

            throw new UsernameNotFoundException(userName + "が存在しません");

        }

        return new LoginUser(loginUser.get());

    }

}
