package com.resources.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.resources.dao.CustomerDAO;
import com.resources.entity.Customer;
import com.resources.service.CustomerService;

@Controller
@RequestMapping("/management")
public class CustomerController {
	//1st change
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerDAO customerDAO;

	@GetMapping("/list-customers")
	public String listCustomers(Model themodel, HttpSession session) {
		List<Customer> customers = customerService.getAllCustomers();
		themodel.addAttribute("customers", customers);
		Gson gson = new Gson();
		String json = gson.toJson(customers);
		System.out.println(json);
		themodel.addAttribute("customertablejson", json);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String un = ((Users)principal).getName();
		String username = auth.getName();
		session.setAttribute("username", username);
		return "home";
	}

	@GetMapping("/showFormForAdd")
	public String ShowAddForm(Model theModel) {

		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		Customer theCustomer = customerDAO.getCustomer(theId);
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
}
