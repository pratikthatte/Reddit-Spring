package com.example.reddit.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reddit.dto.RegisterRequest;
import com.example.reddit.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger log = Logger.getLogger(AuthController.class);
	private final AuthService authService;
	
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}


	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest)
	{
		try
		{
			log.info("User registration process started for user -" + registerRequest.getEmail());
			authService.signup(registerRequest);
			return new ResponseEntity<>("User registration successful",HttpStatus.OK);
		}
		catch(Exception e)
		{
			log.info("Exception occurred during user registration" , e);
			return new ResponseEntity<>("User registration failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
