package com.resources.service;

import java.util.List;

import com.resources.entity.Vendor;

public interface VendorService {

	public void saveVendor(Vendor vendor) ;

	public List<Vendor> getVendorDetails();
}
