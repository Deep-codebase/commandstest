package com.resources.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resources.entity.AuthUser;
import com.resources.entity.UserDTO;

public interface UserDAO {

	public UserDTO getUserByEmail(String email);

	public void saveAuthUser(AuthUser authuser);
}
