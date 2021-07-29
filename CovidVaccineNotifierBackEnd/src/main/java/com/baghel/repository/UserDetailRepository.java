package com.baghel.repository;

import org.springframework.data.repository.CrudRepository;

import com.baghel.model.UserDetails;

public interface UserDetailRepository extends CrudRepository<UserDetails, Long> {

}
