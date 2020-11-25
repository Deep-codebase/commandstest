package com.resources.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resources.entity.Vendor;

@Repository
public class VendorDAOImpl implements VendorDAO {

	@Autowired
	private EntityManager entityManager;

	public List<Vendor> loadAllVendors() {
		return null;
	}

	public void saveVendor(Vendor vendor) {
		Session session = entityManager.unwrap(Session.class);
		session.save(vendor);
		System.out.println("Vendor saved");
	}

	public List<Vendor> getVendorDetails() {
		Session currentSession = entityManager.unwrap(Session.class);

//		Query<Vendor> theQuery = currentSession.createQuery("from Vendor order by id asc", Vendor.class);
//
//		List<Vendor> vendors = theQuery.getResultList();
		
//		Criteria criteria = currentSession.createCriteria(Vendor.class);
//		List vendors = criteria.list();
		/*
		 * CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		 * CriteriaQuery<Vendor> criteriaQuery = builder.createQuery(Vendor.class);
		 * Root<Vendor> root = criteriaQuery.from(Vendor.class); Path<Object> venname =
		 * root.get("sgdfg"); Path<Object> venemail = root.get("email");
		 * 
		 * criteriaQuery.select(builder.construct(Vendor.class, ));
		 */
		
		
		List<Vendor> vendors = currentSession.createCriteria(Vendor.class)
							   .setFetchMode("vendorFiles", FetchMode.LAZY).list();	
		System.out.println("vendors json: "+ vendors);
		return vendors;
	}

}
