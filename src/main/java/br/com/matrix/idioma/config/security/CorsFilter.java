package br.com.matrix.idioma.config.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Value("${app.security.cors.origin-allowed}")
	private String allowedOrigin;
	
	@Value("${app.security.cors.methods-allowed}")
	private String allowedMethods;
	
	@Value("${app.security.cors.headers-allowed}")
	private String allowedHeaders;
	
	@Value("${app.security.cors.max-age}")
	private String maxAge;
	
	@Value("${app.security.cors.allow-credentials}")
	private String credentialsAllowed;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;		
		response.setHeader(CorsHeaders.ALLOW_ORIGIN, allowedOrigin );
		response.setHeader(CorsHeaders.ALLOW_CREDENTIALS, credentialsAllowed);
		
		if(request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString())){
			
			response.setHeader(CorsHeaders.ALLOW_METHODS, allowedMethods);
			response.setHeader(CorsHeaders.ALLOW_HEADERS, allowedHeaders);
			response.setHeader(CorsHeaders.ALLOW_MAX_AGE, maxAge);
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	class CorsHeaders {
		
		private CorsHeaders(){}

		public static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
		public static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
		public static final String ALLOW_METHODS = "Access-Control-Allow-Methods";
		public static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
		public static final String ALLOW_MAX_AGE = "Access-Control-Max-Age";
	}
}
