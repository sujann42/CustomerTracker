package com.customer.sp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.sp.entity.Customer;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {

	// Dependency Injection should be done here
	@Autowired
	private SessionFactory sessionFactory; // this is from Hibernate package

	@Override
	@Transactional
	public List<Customer> getCustomers() {

		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return result
		return customers;
	}

}
