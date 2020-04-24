package com.anurag.spring.countrydata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryDataService {
	
	@Autowired
	CountryDataRepo mongoRepo;

	public void save(CountryStaticData country) {
		mongoRepo.save(country);
	}

	public CountryStaticData get(String id) {
		return mongoRepo.findById(id).get();
	}

	public void delete(String id) {
		mongoRepo.deleteById(id);
	}

	public void add(List<CountryStaticData> countryList) {
		mongoRepo.saveAll(countryList);
	}

	public List<CountryStaticData> getAll() {		
		return mongoRepo.findAll();
	}
}