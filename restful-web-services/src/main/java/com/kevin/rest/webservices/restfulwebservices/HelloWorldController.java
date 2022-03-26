package com.kevin.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

//Controller
@RestController
public class HelloWorldController {
	
	//GET
	//URI - /hello-world
	//method - "Hello World"
	@RequestMapping(method=RequestMethod.GET, path="/hello-world") 
	public String hellowWorld() {
		return "Hello World";
	}

}
