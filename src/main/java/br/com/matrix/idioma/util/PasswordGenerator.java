package br.com.matrix.idioma.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("APP Client Details");
		System.out.println(encoder.encode("123456"));
		
		System.out.println("user text password");
		System.out.println(encoder.encode("654321"));
	}
}
