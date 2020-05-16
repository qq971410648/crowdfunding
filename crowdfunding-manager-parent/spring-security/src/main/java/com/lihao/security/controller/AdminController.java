package com.lihao.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/main.html")
	public String main(){
		return "main";
	}

	@RequestMapping("/to/no/auth/page.html")
	public String toNoAuthPage() {
		return "no_auth";
	}

}
