package com.sutisoft.contentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleTestController {

	@RequestMapping({"","/","index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping({"login"})
	public String login() {
		return "login";
	}
}
