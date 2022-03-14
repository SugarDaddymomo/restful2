package com.tothenew.sharda.UserStuff;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	UserService service;
	
	@GetMapping(path = "/users/user")
	public String helloUser(@RequestParam String username) {
		return "Hello "+username+" "+messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping(path = "/users")
	@ApiOperation(value = "Lists all Users", notes = "Lists all user by using it's service method", response = User.class)
	public List<User> getAllUsers() {
		return service.findAll();
	}
	
	@PostMapping(path = "/users")
	@ApiOperation(value = "Create new User", notes = "Creates a new User and send back it's URI in response", response = User.class)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User createdUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	@ApiOperation(value = "Deletes an User by ID", notes = "Provides and ID to look up and delete the user associated with that ID", response = User.class)
	public void deleteUser(@PathVariable Integer id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("No User found with ID: "+id);
		}
	}
	
	
	
	
	//HATEOAS configuration
	@GetMapping(path = "/users/{id}")
	@ApiOperation(value = "Gets one User", notes = "List the User associated with the passed ID", response = User.class)
	public EntityModel<User> getUser(@PathVariable Integer id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("No User found with ID: "+id);
		}
		
		//HATEOAS PART
		//"all-users", SERVER_PATH + "/users"
		//getAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

}