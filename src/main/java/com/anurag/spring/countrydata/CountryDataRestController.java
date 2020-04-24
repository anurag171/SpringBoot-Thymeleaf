package com.anurag.spring.countrydata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class CountryDataRestController {
	
	@Autowired
	CountryDataService service;
	
	@RequestMapping(path = "/api/country/{code}", produces = "application/json; charset=UTF-8")
    @ResponseBody
	public String getCountryData(@PathVariable(value = "code") String code) {
		
		CountryStaticData countrydata = service.get(code);
		Gson gson = new Gson();		
		return gson.toJson(countrydata);		
	}
}
