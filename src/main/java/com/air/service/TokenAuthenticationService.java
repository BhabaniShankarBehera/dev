package com.air.service;

import org.springframework.stereotype.Component;

import com.air.vo.UserVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
	private String secret = "gshkjhgksfhl68734278732vbvzxnbmnzbmbvxzbnzvxmvv,";

	public UserVo parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			UserVo u = new UserVo();
			u.setEmail(body.getSubject());

			return u;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public String generateToken(UserVo u) {
		Claims claims = Jwts.claims().setSubject(u.getEmail());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
