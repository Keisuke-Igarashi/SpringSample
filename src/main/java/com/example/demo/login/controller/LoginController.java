package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	
	//ログイン画面のGET用コントローラ
	@GetMapping("/login")
	public String getLogin(Model model) {
		
		//login.htmlに画面遷移
		return "login/login";
	}
	
	//HEADER情報学習用のGET用コントローラー
	@GetMapping("/cssexp")
	public String getCssexp(Model model) {
		
		//cssexp.htmlに画面遷移
		return "login/cssexp";
	}
	
	//ログイン画面のPOST用コントローラ
	@PostMapping("/login")
	public String postLogin(Model model) {
		
		//login.htmlに画面遷移
		//return "login/login";
		
		//ホーム画面に遷移
		return "redirect:/home";
	}
	
}
