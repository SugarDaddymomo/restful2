package com.tothenew.sharda.Versioning;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserVersioningController {
	
	//URI VERSIONING
	@GetMapping("/v1/user")
	public UserV1 userV1() {
		return new UserV1("John Charles");
	}
	
	@GetMapping("/v2/user")
	public UserV2 userV2() {
		return new UserV2(new Name("Ram", "Kumar"), LocalDate.now());
	}
	
	
	
	//REQUEST PARAMETER VERSIONING
	@GetMapping(value = "/v1/user", params = "version=1")
	public UserV1 paramV1() {
		return new UserV1("John Charles");
	}
	
	@GetMapping(value = "/v2/user", params = "version=2")
	public UserV2 paramV2() {
		return new UserV2(new Name("Ram", "Kumar"), LocalDate.now());
	}
	
	
	
	//HEADER VERSIONING
	@GetMapping(value = "/v1/user/header", headers = "X-API-VERSION=1")
	public UserV1 headerV1() {
		return new UserV1("John Charles");
	}
	
	@GetMapping(value = "/v2/user/header", headers = "X-API-VERSION=2")
	public UserV2 headerV2() {
		return new UserV2(new Name("Ram", "Kumar"), LocalDate.now());
	}
	
	
	
	//MIMETYPE VERSIONING
	@GetMapping(value = "/v1/user/produces", produces = "application/vnd.company.app-v1+json")
	public UserV1 producesV1() {
		return new UserV1("John Charles");
	}
		
	@GetMapping(value = "/v2/user/produces", produces = "application/vnd.company.app-v2+json")
	public UserV2 producesV2() {
		return new UserV2(new Name("Ram", "Kumar"), LocalDate.now());
	}
}