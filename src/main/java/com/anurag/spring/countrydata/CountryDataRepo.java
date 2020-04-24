package com.anurag.spring.countrydata;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryDataRepo extends MongoRepository<CountryStaticData, String> {

}
