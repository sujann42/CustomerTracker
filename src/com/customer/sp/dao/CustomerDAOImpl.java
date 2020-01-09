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

	// Getting list of all customers
	@Override
	public List<Customer> getCustomers() {

		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query and sort data by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName", Customer.class);
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return result
		return customers;
	}

	// Saving/Updating new customer into the database
	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentsession = sessionFactory.getCurrentSession();
		currentsession.saveOrUpdate(theCustomer);
	}

	//Getting data for customer UPDATE
	@Override
	public Customer getCustomerForUpdate(int theId) {
		// Get current Hibernate session
		Session ssn = sessionFactory.getCurrentSession();

		// Retrieve the data from database based on Id.
		Customer theCustomer = ssn.get(Customer.class, theId);
		
		//return customer
		return theCustomer;
	}

}
