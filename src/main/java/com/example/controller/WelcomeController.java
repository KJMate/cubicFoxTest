package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.User;
import com.example.util.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome";
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody User user) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
					);
		}catch (Exception e) {
			throw new Exception("invalid username/password");
		}
		
		return jwtUtil.generateToken(user.getName());
		
	}
}