package com.kevin.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBeans() {
		List<SomeBean> someBeansList = new ArrayList();
		
		someBeansList.add(new SomeBean("value1", "value2", "value3"));
		someBeansList.add(new SomeBean("Value4", "value5", "value6"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBeansList);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}

}
