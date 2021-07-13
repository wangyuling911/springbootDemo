package com.light.springboot.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.light.springboot.util.RandomUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class JwtTokenService implements IJwtTokenService {
    private String jwtKey;
    private Integer jwtExpires;
    
    public JwtTokenService(String jwtKey) {
    	this.jwtKey = jwtKey;
    }
    
    public JwtTokenService(String jwtKey, Integer jwtExpires) {
    	this.jwtKey = jwtKey;
    	this.jwtExpires = jwtExpires;
    }

	@SuppressFBWarnings({"REC_CATCH_EXCEPTION","DE_MIGHT_IGNORE"})
	@Override
	public String generateToken() {
		try {
			Date expiresTime = addSeconds(new Date(), jwtExpires);
			String sign = JWT.create().withJWTId(RandomUtils.generateToken(24))
					.withIssuer("Easemob")
					.withSubject("kefu-internal-service-call")
					.withIssuedAt(new Date())
					.withExpiresAt(expiresTime)
					.sign(Algorithm.HMAC256(jwtKey));
			//log.info("Generate token success");
			
			return sign;
		} catch (UnsupportedEncodingException e) {
			//log.error("Generate token error.", e);
		}
		
		return null;
	}

	@SuppressFBWarnings({"REC_CATCH_EXCEPTION","DE_MIGHT_IGNORE"})
	@Override
	public String generateCsrfToken(String session) {
		try {
			String sign = JWT.create().withJWTId(RandomUtils.generateToken(24))
					.withIssuer("Easemob")
					.withSubject("kefu-csrf")
					.withIssuedAt(new Date())
					.sign(Algorithm.HMAC256(jwtKey+":"+session));
			
			return sign;
		} catch (UnsupportedEncodingException e) {
			//
		}
		
		return null;
	}

	@Override
	public boolean isValid(String token) {
		return verifyToken(token);
	}
	
	@Override
	public boolean isCsrfTokenValid(String token, String session) {
		return verifyCsrfToken(token,session);
	}

	private Date addSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	private boolean verifyToken(String token) {        
        DecodedJWT decodedToken = decodedToken(token);
        if(decodedToken == null) {
        	//log.error("Token be decoded error.");
            return false;
        }
        
        Date expiresTime = decodedToken.getExpiresAt();
        if ((expiresTime == null)
        		|| (expiresTime.getTime() - System.currentTimeMillis() < 0)) {
            //log.error("Token be verified failed, it has expired with expires time: {}.", expiresTime);
            return false;
        }
        
        return true;
	}
	
	private boolean verifyCsrfToken(String token, String session) {
        DecodedJWT decodedToken = decodedToken(token,session);
        if(decodedToken == null) {
            return false;
        }
        
        return true;
	}

	@SuppressFBWarnings("REC_CATCH_EXCEPTION")
	private DecodedJWT decodedToken(String token, String session) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtKey+":"+session)).acceptLeeway(30).build();
			return verifier.verify(token);
		} catch (Exception e) {
			//log.error("Token be decoded error.", e);
		}

		return null;
	}

	private DecodedJWT decodedToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtKey)).acceptLeeway(30).build();
            return verifier.verify(token);
        } catch (Exception e) {
            //log.error("Token be decoded error.", e);
        }

        return null;
    }

}
