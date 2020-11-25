package com.resources.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resources.entity.AuthUser;
import com.resources.entity.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public UserDTO getUserByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("email", email));
		List userdtolist = criteria.list();
		UserDTO userdto;
		if(userdtolist.size()>0) {
			userdto = (UserDTO)userdtolist.get(0);
			return userdto;
		}else {
			return null;
		}
		
	}

	@Override
	public void saveAuthUser(AuthUser authuser) {
		Session session = entityManager.unwrap(Session.class);
		session.save(authuser);
	}

}
