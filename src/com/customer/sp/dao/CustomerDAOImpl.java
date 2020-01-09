package com.customer.sp.dao;

import java.util.List;

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

	// Getting data for customer UPDATE
	@Override
	public Customer getCustomerForUpdate(int theId) {
		// Get current Hibernate session
		Session ssn = sessionFactory.getCurrentSession();

		// Retrieve the data from database based on Id.
		Customer theCustomer = ssn.get(Customer.class, theId);

		// return customer
		return theCustomer;
	}

	// Deleting the customer based on id
	@Override
	public void deleteCustomer(int theId) {
		// Get current Hibernate session
		Session ssn = sessionFactory.getCurrentSession();

		// Create a query
		Query theQuery = ssn.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

	// Searching customers based on first name
	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		//Search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Customer", Customer.class);			
		}
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

}
