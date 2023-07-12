package com.garvitrajput.exittest.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.garvitrajput.exittest.dao.UserDAO;
import com.garvitrajput.exittest.entity.UsersStructure;
import com.garvitrajput.exittest.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Filter for decoding a JWT in the Authorization header and loading the user
 * object into the authentication context.
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	/** The JWT Service. */
	private JWTService jwtService;
	/** The Local User DAO. */
	private UserDAO localUserDAO;

	public JWTRequestFilter(JWTService jwtService, UserDAO localUserDAO) {
		this.jwtService = jwtService;
		this.localUserDAO = localUserDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader = request.getHeader("Authorization");
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.substring(7);
			try {
				String username = jwtService.getUsername(token);
				Optional<UsersStructure> opUser = localUserDAO.findByusernameIgnoreCase(username);
				if (opUser.isPresent()) {
					UsersStructure user = opUser.get();
					@SuppressWarnings("rawtypes")
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
							null, new ArrayList());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (JWTDecodeException ex) {
			}
		}
		filterChain.doFilter(request, response);
	}
}