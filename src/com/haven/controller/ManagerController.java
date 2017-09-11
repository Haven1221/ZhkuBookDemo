package com.haven.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haven.service.interfaces.BookService;
import com.haven.service.interfaces.OrderService;

@Controller
@Scope("request")
@RequestMapping(value="manager")
public class ManagerController {

	@Resource
	private BookService bookService;
	
	@Resource
	private OrderService orderService;
	
}
