package rest.config.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationExample {
	private static AuthenticationManager am = new SampleAuthenticationManager();

	public AuthenticationExample(String name, String password) throws Exception {

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(name, password);
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
		}
		System.out.println(">> " + name + " " + password);

		System.out.println("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
	}
}

class SampleAuthenticationManager implements AuthenticationManager {
	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_"));
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		System.out.println(auth.getName() + " auth " + auth.getCredentials());

		return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);

	}
}