package com.customer.sp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.sp.dao.ICustomerDAO;
import com.customer.sp.entity.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	//DAO needs to be injected.
	@Autowired
	private ICustomerDAO customerDAO;
	
	//Getting data
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

	//Saving data
	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
		
	}

	//Getting for update
	@Override
	@Transactional
	public Customer getCustomerForUpdate(int theId) {
		
		return customerDAO.getCustomerForUpdate(theId);
	}

}
