package com.anurag.spring.countrydata.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anurag.spring.countrydata.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
