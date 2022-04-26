package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.LoginUserDetailsService;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfigurer extends WebSecurityConfigurerAdapter {

	// ログイン処理を行うインスタンスをDI
	@Autowired
    LoginUserDetailsService loginUserDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		/*
		 * セキュリティ設定を無視するリクエスト設定
		 * 静的リソースに対するアクセスはセキュリティ設定を無視する
		 */
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");

	}

	@Override
	protected void configure(HttpSecurity https) throws Exception {

		/*
		 *  アクセス権限の設定
		 */
		https
		.authorizeRequests()
			// アクセス制限の無いURL
			.antMatchers("/login").permitAll()
			// その他は認証済みであること
			.anyRequest()
			.authenticated()
			.and()

		.formLogin()
			// 認証が必要なURLへアクセスした際はログイン画面へ飛ばす
			.loginPage("/login")

			// ログイン成功
			.defaultSuccessUrl("/work/index")

			// ログイン失敗時はログイン画面を再表示
			.failureUrl("/login")

			// usernameのパラメータ名
			.usernameParameter("userName")
			// passwordのパラメータ名
			.passwordParameter("password")
			.and()

		.logout()
			// ログアウト処理
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

			// ログアウト成功時の遷移先
			.logoutSuccessUrl("/login");

	}

	/*
     * フォームから送信されたパスワードをハッシュ化
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;

    }

    /*
     * ログイン処理の設定
     */
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// ログイン処理時のユーザー情報をDBから取得する
        auth.userDetailsService(loginUserDetailsService).passwordEncoder(passwordEncoder());

	}

}
