package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;


@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;
	
	//ポイント１：ラジオボタンの実装
	private Map<String, String>radioMarriage;
	
	private Map<String, String> initRadioMarriage(){
		Map<String, String>radio = new LinkedHashMap<>();
		
		//既婚、未婚をMapに格納
		radio.put("既婚","true");
		radio.put("未婚","false");
		
		return radio;
	}
	
	//ポイント１：@ModelAttribute
	//ユーザ登録画面のGET用コントローラー
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form,Model model) {
		
		//ラジオボタンの初期化メソッドの呼び出し
		radioMarriage = initRadioMarriage();
		
		//ラジオボタン用のMapをModelに登録
		model.addAttribute("radioMarriage", radioMarriage);
		
		//signup.htmlに画面遷移
		return "login/signup";
	}
	
	//ユーザ登録画面のPOST用コントローラー
	//ポイント2：データバインド結果の受け取り
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult,
			Model model) {
		
		//データバインド失敗の場合
		//入力チェックに引っかかった場合、ユーザ登録画面に戻る
		if(bindingResult.hasErrors()) {
			
			//GETリクエスト用のメソッドを呼び出して、ユーザ登録画面に戻ります
			return getSignUp(form, model);
		}
		//formの中身をコンソールに出して確認します
		System.out.println(form);
		
		/*
		//ポイント２：リダイレクト
		//login.htmlにリダイレクト
		return "redirect:/login";
		*/
		
		//insert用変数
		User user = new User();
		
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		user.setRole("ROLE_GENERAL");
		
		//ユーザ登録処理
		boolean result = userService.insert(user);
		
		//ユーザ登録結果の判定
		if(result == true) {
			System.out.println("insert成功");
			
		}else {
			System.out.println("insert失敗");
		}
		
		//loguin.htmlにリダイレクト
		return "redirect:/login";
	}
	
	//ポイント：@ExceptionHandlerの使い方
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		//例外クラスのメッセージをModelに登録
		model.addAttribute("error","内部サーバーエラー（DB）:ExceptionHandler");
		
		//例外クラスのメッセージをModelに登録
		model.addAttribute("message","SignupControllerでDataAccesssExceptionが発生しました");
		
		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	//ポイント：@ExceptionHandlerの使い方
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e,Model model) {
		
		//例外クラスのメッセージをModelに登録
		model.addAttribute("error","内部サーバーエラー：ExceptionHandler");
		
		//例外クラスのメッセージをModelに登録
		model.addAttribute("message","SignupControllerでExceptionが発生しました");
		
		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

}
