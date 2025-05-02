package com.erp.school.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {


	@Value("${jwt.secret}")
	private String secretKey;

	public String generateToken(String username, Map<String, Object> claims) {
		JwtBuilder jwt = Jwts.builder()
				.setSubject(username)
				.addClaims(claims)    // Add additional claims (e.g., role, id)

				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, generateJwtSecretKey());
		String token = jwt.compact();
		System.out.println("Generated JWT Token: " + token);
//		System.out.println("JWT Payload: " + subjects);
		System.out.println("[JWT Generation] Claims Included: " + claims);

		return token;
	}

	public SecretKey generateJwtSecretKey() {
		byte[] keyBytes = secretKey.getBytes();
		byte[] keyBytesPadded = new byte[32];
		System.arraycopy(keyBytes, 0, keyBytesPadded, 0, Math.min(keyBytes.length, 32));
		return Keys.hmacShaKeyFor(keyBytesPadded);
	}


	public boolean validateToken(String token, String username) {
		try {
			Claims claims = getClaims(token);
			System.out.println("[JWT Validation] Claims in Token: " + claims);

			String tokenUsername = claims.getSubject(); // Extract the subject (username)
			boolean isExpired = isTokenExpired(token);

			System.out.println("Token Username: " + tokenUsername);
			System.out.println("Token Expiration: " + claims.getExpiration());
			System.out.println("Is Token Expired: " + isExpired);

			return (username.equals(tokenUsername) && !isExpired);
		} catch (Exception e) {
			System.out.println("Token validation error: " + e.getMessage());
			return false;
		}
	}




	private boolean isTokenExpired(String token) {
		Claims claims = getClaims(token);
		return claims != null && claims.getExpiration().before(new Date());
	}

	private String getUsername(String token) {
		// TODO Auto-generated method stub
		return getClaims(token).getSubject();
	}

	public String extractUsername(String token) {
		Claims claims = getClaims(token);
		if (claims == null) {
			System.out.println("[JWT Extraction] Failed to extract claims.");
			return null;
		}
		System.out.println("[JWT Extraction] Claims: " + claims);
		String username = claims.get("username", String.class); // Custom claim
		return username != null ? username : claims.getSubject(); // Fallback to 'sub'
	}




	public Claims getClaims(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(generateJwtSecretKey()) // Correct method for setting the secret key
					.build()
					.parseClaimsJws(token) // Parses the token and retrieves the claims
					.getBody();
			System.out.println("[JWT Parsing] Parsed Claims: " + claims);
			return claims;

		} catch (Exception e) {
			System.out.println("Error extracting claims: " + e.getMessage());
			return null;
		}
	}
}
