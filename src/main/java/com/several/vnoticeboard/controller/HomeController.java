package com.several.vnoticeboard.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
public class HomeController {
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome() {
		return "home";
	}
}
