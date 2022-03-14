package com.tothenew.sharda.Filtering;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class FilteringController {
	
	@Autowired
	UserServiceNew service;
	
	//static-filtering for password
	@GetMapping(path = "/staticfilter/{id}")
	@ApiOperation(value = "Gets one User", notes = "List the user associated with the passed ID", response = User.class)
	public User getEmployee(@PathVariable Integer id) {
		User user = service.findOne(id);
		return user;
	}
	
	//method to create user, accepts password too
	@PostMapping(path = "/staticfilter")
	@ApiOperation(value = "Create new User", notes = "Creates a new User and send back it's URI in response", response = User.class)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User createdUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//method to print list of users
	@GetMapping(path = "/staticfilter/list")
	@ApiOperation(value = "List all users", notes = "Lists all users", response = User.class)
	public List<User> getListOfUsers() {
		return service.findAll();
	}
	
	
	//DYNAMIC filtering
	@GetMapping(path = "/dynamicfilter/{id}")
	public MappingJacksonValue someUser(@PathVariable Integer id) {
		User user = service.findOne(id);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "username");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeUserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		return mapping;
	}
}