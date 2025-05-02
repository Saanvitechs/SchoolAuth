package com.erp.school.security;

import com.erp.school.model.Teacher;
import com.erp.school.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private TeacherRepository userRepository;


	 @PostMapping("/login")
	    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest,@RequestHeader("tenant") String tenant) {
		 try {
			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					 authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			 final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			 Teacher user= userRepository.findByEmail(authenticationRequest.getUsername());
			 Map<String, Object> subjects = new HashMap<>();
			 subjects.put("Name",user.getName());
			 subjects.put("role",user.getRole());
			 subjects.put("id",user.getId());
			 subjects.put("username",user.getEmail());

			 subjects.put("tenant",user.getTenantId());
			 String jwt = jwtUtil.generateToken(userDetails.getUsername(),subjects);
			 return ResponseEntity.ok(new AuthenticationResponse(jwt, null, user.getRole(),user.getName(),user.getId().toString()));
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(new AuthenticationResponse(null, "Invalid username or password "+e.getMessage(),null,null,null));
		 }
	    }
}
