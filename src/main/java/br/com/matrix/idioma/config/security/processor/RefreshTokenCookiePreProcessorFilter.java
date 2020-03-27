package br.com.matrix.idioma.config.security.processor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class RefreshTokenCookiePreProcessorFilter implements Filter {

	@Value("${app.security.token.refresh-token-name}")
	private String refreshTokenName;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
				HttpServletRequest req = (HttpServletRequest) request;
			if (req.getRequestURI().equalsIgnoreCase("/oauth/token")
					&& "refresh_token".equalsIgnoreCase(req.getParameter("grant_type"))
					&& req.getCookies() != null) {
				for (Cookie cookie : req.getCookies())
					if(cookie.getName().equalsIgnoreCase(refreshTokenName))
						req = new MyServletRequestWrapper(req, cookie.getValue());
			}
			chain.doFilter(req, response);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	static class MyServletRequestWrapper extends HttpServletRequestWrapper {

		private String refreshToken;

		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[]{refreshToken});
			map.setLocked(true);
			return map;
		}
	}

}
