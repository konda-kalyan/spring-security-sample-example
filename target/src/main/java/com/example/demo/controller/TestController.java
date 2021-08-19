package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public void test() {
		System.out.println("----- Kalyan -test -----------");
	}
	
	@GetMapping("mytest")
	public void mytest() {
		System.out.println("----- Kalyan - mytest -----------");
	}
}
