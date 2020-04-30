package com.example.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.exception.JwtTokenException;
import com.example.service.CustomUserDetailService;
import com.example.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		if(authorizationHeader != null && authorizationHeader.startsWith("Kunecz ")) {
			token = authorizationHeader.substring(7);
				if(validateToken(token,request,response)) {
					userName = jwtUtil.extractUsername(token);
				}
		}
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailService.loadUserByUsername(userName);
			
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
				= new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} 
		}
		
		filterChain.doFilter(request, response);
	}
	
	 public boolean validateToken(String authToken,HttpServletRequest request, HttpServletResponse response)  {
	        try {
	           Jwts.parser().setSigningKey(jwtUtil.getSecret()).parseClaimsJws(authToken);
	           return true;
	        } catch (SecurityException | MalformedJwtException e) {
	           resolver.resolveException(request, response, null,new JwtTokenException("Invalid JWT signature"));
	        } catch (ExpiredJwtException e) {
	        	resolver.resolveException(request, response,null,new JwtTokenException("Expired JWT token"));
	        } catch (UnsupportedJwtException e) {
	        	resolver.resolveException(request, response, null,new JwtTokenException("Unsupported JWT token"));
	        } catch (IllegalArgumentException e) {
	        	resolver.resolveException(request, response, null,new JwtTokenException("JWT token compact of handler are invalid"));
	        }
	        return false;
	     }
}