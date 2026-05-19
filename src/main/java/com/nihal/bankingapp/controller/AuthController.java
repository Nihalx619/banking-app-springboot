package com.nihal.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nihal.bankingapp.dto.AuthDto;
import com.nihal.bankingapp.entity.User;
import com.nihal.bankingapp.repository.UserRepository;
import com.nihal.bankingapp.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String register(@RequestBody AuthDto authDto) {

		String encodedpassword = passwordEncoder.encode(authDto.getPassword());
		User user = new User();
		user.setRole("USER");
		user.setUsername(authDto.getUsername());
		user.setPassword(encodedpassword);
		userRepository.save(user);

		return "user registered successfully";

	}
	
	@PostMapping("/login")
	public String login(@RequestBody AuthDto authDto) {
		User user = userRepository.findByUsername(authDto.getUsername());
		
		if(passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
			String token = jwtUtil.generateToken(user.getUsername());
			return token;
		}
		
	   return "login failed";
	}

}
