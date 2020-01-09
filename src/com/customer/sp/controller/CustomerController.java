package com.customer.sp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer.sp.dao.ICustomerDAO;
import com.customer.sp.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Need to inject DAO

	@Autowired
	private ICustomerDAO customerDAO;

	@RequestMapping("/list")
	public String listCustomers(Model model) {

		// get customers from DAO
		List<Customer> theCustomers = customerDAO.getCustomers();

		// add customers to model
		model.addAttribute("customers", theCustomers); //name  and value to carry it forward

		return "list-customers";
	}
}
