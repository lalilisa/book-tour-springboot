package com.ttcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/tour_list")
	public String tour_list() {
		return "tour_list";
	}
	
	@GetMapping("/tour_detail")
	public String tour_detail() {
		return "tour_detail";
	}
	
	@GetMapping("/book_tour")
	public String book_tour() {
		return "views/book_tour";
	}
	
	@GetMapping("/admin/addTour")
	public String addTour() {
		return "/admin/addTour";
	}
	
	@GetMapping("/admin/admin")
	public String admin() {
		return "/admin/admin";
	}
	
	@GetMapping("/admin/adminInfo")
	public String adminInfo() {
		return "/admin/adminInfo";
	}
	
	@GetMapping("/admin/bookedTour")
	public String bookedTour() {
		return "/admin/bookedTour";
	}
	
	@GetMapping("/admin/bookTourDetail")
	public String bookTourDetail() {
		return "/admin/bookTourDetail";
	}
	
	@GetMapping("/admin/editTour")
	public String editTour() {
		return "/admin/editTour";
	}
	
	@GetMapping("/forgotPass")
	public String forgotPass() {
		return "/forgotPass";
	}
	
	@GetMapping("/admin/listBookTour")
	public String listBookTour() {
		return "/admin/listBookTour";
	}
	
	@GetMapping("/admin/listTour")
	public String listTour() {
		return "/admin/listTour";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/register";
	}
	
	@GetMapping("/admin/statistic")
	public String statistic() {
		return "/admin/statistic";
	}
	
	@GetMapping("/admin/tourDetail")
	public String tourDetail() {
		return "/admin/tourDetail";
	}
	
	@GetMapping("/admin/user")
	public String user() {
		return "/admin/user";
	}
	
	@GetMapping("/userBooked")
	public String userBooked() {
		return "/views/userBooked";
	}
	
	@GetMapping("/userInfo")
	public String userInfo() {
		return "/views/userInfo";
	}
}
