package com.customer.sp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.sp.dao.ICustomerDAO;
import com.customer.sp.entity.Customer;
import com.customer.sp.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Need to inject customer service here
	@Autowired
	private ICustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {

		// get customers from DAO
		List<Customer> theCustomers = customerService.getCustomers();

		// add customers to model
		model.addAttribute("customers", theCustomers); // name and value to carry it forward

		return "list-customers";
	}

	// Display the form to input customer
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// Creating model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	// Saving the customer data into the database
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	// Getting the form to update the info existing in the database
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//Getting customers from the service
		Customer theCustomer = customerService.getCustomerForUpdate(theId);
		
		//Set customer as a model attribute to populate the form
		theModel.addAttribute("customer", theCustomer);

		return "customer-form";

	}
}
