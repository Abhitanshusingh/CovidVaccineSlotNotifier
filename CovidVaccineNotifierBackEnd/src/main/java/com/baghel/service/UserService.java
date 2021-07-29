package com.baghel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baghel.model.UserDetails;
import com.baghel.repository.UserDetailRepository;

@Service
public class UserService {
	
	@Autowired UserDetailRepository userDetailRepository;
	
	public UserDetails addUser(UserDetails userDetail) {
		System.out.println(userDetail.getName());
		System.out.println(userDetail.getMobileNumber());
		return userDetailRepository.save(userDetail);
	}
}
