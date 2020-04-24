package com.anurag.spring.countrydata;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class CountryDataController {
	
	@Value(value = "classpath:/data.json")
	String preloadFilePath;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	CountryDataService service;
	
	private boolean isJsonDataLoaded = false;
	
	@GetMapping("/country_form")
	public String showForm(Model model) throws IOException {
		List<CountryStaticData> countryList = null;
		if(!isJsonDataLoaded) {			
			countryList = new ArrayList<>();
			StringBuilder contentBuilder = new StringBuilder();
			File resource = resourceLoader.getResource(preloadFilePath).getFile();			    
			try(Stream<String> stream = Files.lines(Paths.get(resource.getPath()),StandardCharsets.UTF_8)){
				stream.forEach(s -> contentBuilder.append(s).append("\n"));
				Gson gson = new Gson();
				countryList =  gson.fromJson(contentBuilder.toString(), new TypeToken<List<CountryStaticData>>(){}.getType());
				service.add(countryList);
			}catch(Exception e) {
				e.printStackTrace();
			}
			isJsonDataLoaded = true;
		}else {
			countryList = service.getAll();
		}
		model.addAttribute("countryList", countryList);		
		return "country_form";
	}
	
	@PostMapping("/register")
	public String submitForm(@ModelAttribute("countrydata") CountryStaticData countrydata) {
		System.out.println(countrydata);
		return "country_add";
	}
	
	@RequestMapping("/new")
	public String showNewPage(Model model) {
	    CountryStaticData country = new CountryStaticData();
	    model.addAttribute("country", country);
	     
	    return "new_country";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveData(@ModelAttribute("country") CountryStaticData country) {
	    service.save(country);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{code}")
	public ModelAndView showEditDataPage(@PathVariable(name = "code") String code) {
	    ModelAndView mav = new ModelAndView("edit_country");
	    CountryStaticData country = service.get(code);
	    mav.addObject("country", country);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{code}")
	public String deleteCountryData(@PathVariable(name = "code") String code) {
	    service.delete(code);
	    return "redirect:/";       
	}
}
