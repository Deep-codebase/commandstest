package com.resources.dao;
import java.util.List;

import com.resources.entity.*;

public interface VendorDAO {

	public List<Vendor> loadAllVendors();

	public void saveVendor(Vendor vendor);
	
	public List<Vendor> getVendorDetails();
}
