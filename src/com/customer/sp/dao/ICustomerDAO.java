package com.customer.sp.dao;

import java.util.List;
import com.customer.sp.entity.Customer;

public interface ICustomerDAO {
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerForUpdate(int theId);
}
