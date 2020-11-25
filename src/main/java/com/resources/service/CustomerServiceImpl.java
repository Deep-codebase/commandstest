package com.resources.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resources.dao.*;

import com.resources.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO CustomerDAO;
	
	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		List<Customer> customers = CustomerDAO.getCustomers();
		return customers;
	}

}
