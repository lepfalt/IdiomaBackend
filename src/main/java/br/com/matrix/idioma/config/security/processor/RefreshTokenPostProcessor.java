package br.com.matrix.idioma.config.security.processor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

	@Value("${app.security.token.refresh.secure-token}")
	private boolean secureRefreshToken;
	
	@Value("${app.security.token.refresh.max-age}")
	private Integer maxAge;
	
	@Value("${app.security.token.refresh-token-name}")
	private String refreshTokenName;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest req = (( ServletServerHttpRequest)request).getServletRequest();
		HttpServletResponse resp = ((ServletServerHttpResponse)response).getServletResponse();
				
		addRefreshTokenOnCookie(body.getRefreshToken().getValue(), req, resp);
		removeRefreshTokenFromResponseBody(body);
		return body;
	}

	private void removeRefreshTokenFromResponseBody(OAuth2AccessToken body) {
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		token.setRefreshToken(null);		
	}


	private void addRefreshTokenOnCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
		Cookie refreshTokenCookie = new Cookie(refreshTokenName, refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(secureRefreshToken);
		refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(maxAge);
		resp.addCookie(refreshTokenCookie);
	}

}
