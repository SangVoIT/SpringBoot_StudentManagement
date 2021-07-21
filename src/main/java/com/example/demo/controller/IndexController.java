package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.SignupForm;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	private final AccountService accountService;

	public IndexController(AccountService accountService) {
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
	@GetMapping(value="/")
	public String index(Model model) {
			// @ModelAttribute("signup") SignupForm signupForm, Model model) {
		System.out.println("IndexController: @GetMapping(/)");
		log.debug("IndexController: @GetMapping(/)");

		List<User> userList = accountService.finaAll();
		model.addAttribute("logged_message", "Logging successfully...");
		model.addAttribute("users", userList);
		return "index";
	}
	
	
	/***
	 * Home　page
	 * 
	 * @param signupForm サインアップフォームデータ
	 * @param model モデル（ユーザーリスト）
	 * @return index
	 */
	@GetMapping(value="/all")
	public String allAccount(Model model) {
			// @ModelAttribute("signup") SignupForm signupForm, Model model) {
		System.out.println("IndexController: @GetMapping(/all)");
		log.debug("IndexController: @GetMapping(/all)");

		List<User> userList = accountService.finaAll();
		model.addAttribute("logged_message", "Logging successfully...");
		model.addAttribute("users", userList);
		return "index";
	}
	
	
	/***
	 * 新しいアカウントを登録する
	 * @param signupForm サインアップフォームデータ
	 * @param redirectAttributes リダイレクト先へメッセージを送るため
	 * @return
	 */
	@RequestMapping(value="/signup_2", consumes="application/json", method=RequestMethod.POST)
	public String signup(@RequestBody SignupForm signupForm, HttpServletRequest request) {
			// @ModelAttribute("signup") SignupForm signupForm, RedirectAttributes redirectAttributes) {
		System.out.println("IndexController: @PostMapping(signup");
		
		// 暫定的に二つのロール持つアカウントを登録する
		String[] roles = {"ROLE_USER", "ROLE_ADIMIN"};
		
		accountService.register(signupForm.getName(), signupForm.getEmail(), signupForm.getPassword(), roles);
		// redirectAttributes.addFlashAttribute("successMessage", "アカウントの登録は完了しました。");

		return "/POST Successful!";
	}
	
	// Please note the @RequestBody annotation
	  @PostMapping(value = "/signup_3")
	 // Do not add @ModelAttribute superfluously, otherwise it will be overwritten by it, as follows
	//  public Account getAccount(@RequestBody@ModelAttribute Account account)
	public String postAccount(@RequestBody SignupForm signupForm) {
	    System.out.println("postAccount: POST signup ");
	    return "POST /signup OKE";
	}

	// -----------------------------------------------------------------------------------
	// GET URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Default get mapping handle
	 * @param model
	 * @return
	 */
	@PostMapping("/signup")
	public String postImage(@ModelAttribute("signupForm") SignupForm signupForm) {
		System.out.println("check");
		System.out.println("ImagesController: postImage; /POST request with " + signupForm.getEmail());
		System.out.println("check");
		
		// 暫定的に二つのロール持つアカウントを登録する
		String[] roles = {"ROLE_USER", "ROLE_ADIMIN"};
		
		accountService.register(signupForm.getName(), signupForm.getEmail(), signupForm.getPassword(), roles);
		
		return "redirect:/students";
	}

	// Please note the @RequestBody annotation
	@GetMapping(value="/signup")
	 // Do not add @ModelAttribute superfluously, otherwise it will be overwritten by it, as follows
	//  public Account getAccount(@RequestBody@ModelAttribute Account account)
	public String getAccount(Model model) {
	    System.out.println("getAccount GET signup ");
	    SignupForm signupForm = new SignupForm();
		model.addAttribute("signupForm", signupForm);
	    return "create_user";
	}
	

	// Please note the @RequestBody annotation
	@GetMapping(value="/error")
	 // Do not add @ModelAttribute superfluously, otherwise it will be overwritten by it, as follows
	//  public Account getAccount(@RequestBody@ModelAttribute Account account)
	public String getError(Model model) {
	    System.out.println("getError GET error ");
	    return "error";
	}
}
