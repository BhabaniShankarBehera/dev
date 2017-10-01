package com.air.security.service.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import com.air.exception.JwtTokenMissingException;
import com.air.security.AnonAuthentication;
import com.air.service.TokenAuthenticationService;
import com.air.vo.UserVo;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	public static final String LOGIN_MATCHER = "/login";
	// public static final String TEST_MATCHER = "/test";

	static final String HEADER_STRING = "Authorization";
	static final String TOKEN_PREFIX = "TOKEN_";

	private List<String> pathsToSkip = Arrays.asList(LOGIN_MATCHER);

	@Autowired
	TokenAuthenticationService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String headertoken = request.getHeader(HEADER_STRING);

		if (headertoken != null && !skipPathRequest(request, pathsToSkip)) {

			headertoken = headertoken.replaceAll(TOKEN_PREFIX, "").trim();

			UserVo authentication = tokenService.parseToken(headertoken);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					authentication.getEmail(), null, Collections.emptyList());
			SecurityContextHolder.getContext().setAuthentication(token);

		} else {
			SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication());
		}
		filterChain.doFilter(request, response);
	}

	private boolean skipPathRequest(HttpServletRequest request, List<String> pathsToSkip) {
		Assert.notNull(pathsToSkip, "pathsToSkip is null!");
		List<RequestMatcher> m = pathsToSkip.stream().map(path -> new AntPathRequestMatcher(path))
				.collect(Collectors.toList());
		OrRequestMatcher matchers = new OrRequestMatcher(m);
		return matchers.matches(request);
	}

}
