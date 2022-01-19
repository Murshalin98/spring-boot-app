package com.several.vnoticeboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome() {
		LOGGER.debug("Rendering home page");
		
		return "home";
	}
}
