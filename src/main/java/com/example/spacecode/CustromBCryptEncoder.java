package com.example.spacecode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustromBCryptEncoder extends BCryptPasswordEncoder {
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword == null) {
			throw new IllegalArgumentException("rawPassword cannot be null");
		}
		if (encodedPassword == null || encodedPassword.length() == 0) {
			this.logger.warn("Empty encoded password");
			return false;
		}

		return encodedPassword.toString().equals(BCrypt.hashpw(rawPassword.toString(), getSalt()).substring(0,45));
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		if (rawPassword == null) {
			throw new IllegalArgumentException("rawPassword cannot be null");
		}
		String salt = getSalt();
		return BCrypt.hashpw(rawPassword.toString(), salt);
	}
	
	private String getSalt() {
		return "$2a$06$NkYh0RCM8pNWPaYvRLgN9.SpaceCodeSalt123456789461651651651654654561sdfxgddgfhDuyJiNitantSameeradsf";
	}
}
