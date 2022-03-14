package com.tothenew.sharda.EmployeeStuff;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.annotations.ApiOperation;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(path = "/employees")
	@ApiOperation(value = "List all Employees", notes = "List all employees using it's service method", response = Employee.class)
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}
	
	@GetMapping(path = "/employees/{id}")
	@ApiOperation(value = "Gets one Employee", notes = "List the employee associated with the passed ID", response = Employee.class)
	public EntityModel<Employee> getEmployee(@PathVariable Integer id) {
		Employee employee = employeeService.findOne(id);
		if(employee == null) {
			throw new EmployeeNotFoundException("No Employee found with ID: "+id);
		}
		
		//HATEOAS PART
		//"all-employees", SERVER_PATH + "/employees"
		//getAllEmployees
		EntityModel<Employee> resource = EntityModel.of(employee);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllEmployees());
		resource.add(linkTo.withRel("all-employees"));
		return resource;
	}
	
	@PostMapping(path = "/employees")
	@ApiOperation(value = "Creates new Employee", notes = "Creates new employee using it's service method", response = Employee.class)
	public ResponseEntity<Object> createUser(@Validated @RequestBody Employee employee) {
		Employee createdEmployee = employeeService.save(employee);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/employees/{id}")
	@ApiOperation(value = "Deletes the employee", notes = "Deletes the employee associated with the passed ID", response = Employee.class)
	public void deleteEmployee(@PathVariable Integer id) {
		Employee employee = employeeService.deleteById(id);
		if(employee == null) {
			throw new EmployeeNotFoundException("No Employee found with ID: "+id);
		}
	}
	
	@PutMapping(path = "/employees/{id}")
	@ApiOperation(value = "Update employee", notes = "Updates the employee details using it's service method", response = Employee.class)
	public void updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
		employeeService.updateById(id, employee);
	}
}