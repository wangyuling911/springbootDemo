package com.light.springboot.jwt;

public interface IJwtTokenService {
	//Internal service call
    String generateToken();
    boolean isValid(String token);
    
    //CSRF
    String generateCsrfToken(String session);
    boolean isCsrfTokenValid(String token, String session);
}
