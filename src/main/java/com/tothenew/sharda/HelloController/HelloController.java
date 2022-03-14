package com.tothenew.sharda.HelloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello")
	public String hello() {
		return "Welcome to Spring Boot!";
	}
	
	@GetMapping(path = "/hello-international")
	public String helloInternational() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}