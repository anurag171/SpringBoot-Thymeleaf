package com.anurag.spring.countrydata.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepo extends MongoRepository<Token, String>{

}
