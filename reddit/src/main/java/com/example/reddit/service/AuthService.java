package com.example.reddit.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reddit.dto.RegisterRequest;
import com.example.reddit.model.NotificationEmail;
import com.example.reddit.model.User;
import com.example.reddit.model.VerificationToken;
import com.example.reddit.repository.UserRepository;
import com.example.reddit.repository.VerificationTokenRepository;

@Service
public class AuthService {
	
	public static final String ACTIVATION_EMAIL_SUBJECT = "notification.email.subject";
	public static final String ACTIVATION_EMAIL_BODY = "notification.email.body";
	public static final String ACTIVATION_EMAIL_URL = "account.activation.url";
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	
	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailService = mailService;
	}

	@Transactional
	public void signup(RegisterRequest registerRequest)
	{
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setCreationTime(Instant.now());
		user.setActive(false);	
		
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendEmail(new NotificationEmail(ACTIVATION_EMAIL_SUBJECT,ACTIVATION_EMAIL_BODY+ACTIVATION_EMAIL_URL+token,user.getEmail()));
	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationToken.setCreationDate(Instant.now());
		
		verificationTokenRepository.save(verificationToken);
		
		return token;
	}
}
