package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.helper.JwtUtil;
import com.exam.service.CustomUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService customUserDetailsSerivce;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get jwt or header
		// it start with bearer then check for validate

		// getting header
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println(jwtToken);

			try {
				//System.out.println("---------------------"+"inside the try");
				username = this.jwtUtil.getUserNameFromToken(jwtToken);
				//System.out.println(username);
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt Token has Expired!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid token , bearer is missing");
		}
		// validated
		// now validated token
	
		// security
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = this.customUserDetailsSerivce.loadUserByUsername(username);
			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {

				// token is valid

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			System.out.println("Token is not validate");
		}

		filterChain.doFilter(request, response);

	}

}
