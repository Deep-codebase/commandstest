package com.resources.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resources.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private EntityManager entityManager;


	@Override
	public List<Customer> getCustomers() {
		Session currSession = entityManager.unwrap(Session.class);

		Query<Customer> theQry = currSession.createQuery("from Customer");
		List<Customer> customers = theQry.getResultList();
		return customers;
	}

	public void saveCustomer(Customer theCustomer) {
        Session currentSession = entityManager.unwrap(Session.class);
        
        currentSession.saveOrUpdate(theCustomer);
    }

    @Transactional
    public Customer getCustomer(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Customer theCustomer = currentSession.get(Customer.class, theId);
        
        return theCustomer;
    }
}
