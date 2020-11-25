package com.resources.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.dao.VendorDAO;
import com.resources.entity.Vendor;

@Service
public class VendorServiceImpl implements VendorService{

	@Autowired
	private VendorDAO vendorDAO;

	@Transactional
	public void saveVendor(Vendor vendor) {
		vendorDAO.saveVendor(vendor);
	}

	@Transactional
	public List<Vendor> getVendorDetails() {
		List<Vendor> vendors = vendorDAO.getVendorDetails();
		return vendors;
	}
}
