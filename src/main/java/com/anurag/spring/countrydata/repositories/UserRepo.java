package com.anurag.spring.countrydata.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anurag.spring.countrydata.domain.User;

public interface UserRepo extends MongoRepository<User, String> {

	User findByUsername(String username);

}
