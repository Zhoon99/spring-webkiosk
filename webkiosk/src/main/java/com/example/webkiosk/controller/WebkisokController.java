package com.example.webkiosk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webkiosk.entity.User;
import com.example.webkiosk.repository.UserRepository;
import com.example.webkiosk.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // @Autowired 없어도 변수 앞에 final 붙이면 자동 등록
@Controller
public class WebkisokController {

	private final UserService userService;
	private final UserRepository userRepository;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new User());
		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit(User user, RedirectAttributes rttr) {
		if (userService.login(user.getUserId(), user.getUserPassword())) {
			rttr.addFlashAttribute("userId", user.getUserId());
			return "redirect:/kiosk";
		} else {
			return "redirect:/fail";
		}
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String signUpSubmit(@Valid User user, Errors errors) {
		if (userRepository.findByUserId(user.getUserId()) == null) { // 아이디로 검색해서 널 값일경우 서비스실행
			userService.registerUser(user);
			return "redirect:/login";
		} else {
			return "redirect:/signup";
		}
	}
}