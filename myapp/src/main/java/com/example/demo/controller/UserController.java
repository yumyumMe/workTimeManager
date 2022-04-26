package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.UserForm;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	UserModel userModel = new UserModel();

	@GetMapping("/list")
	public String userList(Model model) {

		List<Map<String, Object>> list = userService.selectAll();

		// ユーザー区分変換
		for(Map<String, Object> val : list) {
			String user = val.get("userKbn").toString();
			val.replace("userKbn", userModel.checkUserKbn(user));
		}

		model.addAttribute("list", list);

		return "/user/userList";

	}

	@GetMapping("/create")
	public String userCreate(@RequestParam(name = "userId", defaultValue = "") String userId,
			@ModelAttribute UserForm userForm, Model model) {

		// userIdに値があれば編集モードとして値を設定する
		if(!userId.isEmpty()) {
			Map<String, Object> user = userService.selectUser(userId);
			model.addAttribute("user", user);
		}

		return "/user/userCreate";

	}

	@PostMapping("/create")
	public String userConfirm(@Validated @ModelAttribute UserForm userForm,
			BindingResult bindingResult, Model model, RedirectAttributes attr) {

		if(bindingResult.hasErrors()) {
			return "/user/userCreate";
		}

		if(!userForm.getUserId().isEmpty()) {
			// ユーザー情報更新
			userService.updateUser(userForm);
		}else {
			// ユーザー新規登録
			userService.registUser(userForm);
		}

		return "redirect:/user/list";

	}

	@PostMapping("/delete")
	public String userDelete(@RequestParam("check[]") String[] userId, RedirectAttributes attr) {

		// 削除対象のユーザーIDを配列にまとめて削除
		int user = userService.updateDeleteFlg(userId);
		attr.addFlashAttribute("deleteNum", user + "件削除しました");

		return "redirect:/user/list";

	}

}
