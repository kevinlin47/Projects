package com.kevin.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBeans() {
		List<SomeBean> someBeansList = new ArrayList();
		
		someBeansList.add(new SomeBean("value1", "value2", "value3"));
		someBeansList.add(new SomeBean("Value4", "value5", "value6"));
		
		return someBeansList;
	}

}
