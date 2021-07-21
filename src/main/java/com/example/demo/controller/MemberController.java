package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/members")
public class MemberController {
	private final AccountService accountService;

	public MemberController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	/***
	 * Home　page
	 * 
	 * @param signupForm サインアップフォームデータ
	 * @param model モデル（ユーザーリスト）
	 * @return index
	 */
	@GetMapping("/admin")
	public String getAdminPage(Model model) {
			// @ModelAttribute("signup") SignupForm signupForm, Model model) {
		System.out.println("HomeController:getAdminPage @GetMapping(/)");
		log.debug("HomeController:getAdminPage @GetMapping(/)");

		List<User> userList = accountService.finaAll();
		model.addAttribute("logged_message", "Logging successfully...");
		model.addAttribute("users", userList);
		return "admin/home";
	}

	/***
	 * Home　page
	 * 
	 * @param signupForm サインアップフォームデータ
	 * @param model モデル（ユーザーリスト）
	 * @return index
	 */
	@GetMapping("/user")
	public String getUserPage(Model model) {
			// @ModelAttribute("signup") SignupForm signupForm, Model model) {
		System.out.println("HomeController:getUserPage @GetMapping(/)");
		log.debug("HomeController:getUserPage @GetMapping(/)");

		List<User> userList = accountService.finaAll();
		model.addAttribute("logged_message", "Logging successfully...");
		model.addAttribute("users", userList);
		return "user/home";
	}
}
