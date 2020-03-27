package br.com.matrix.idioma.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@Value("${app.security.token.refresh.secure-token}")
	private boolean secureRefreshToken;
	
	@Value("${app.security.token.refresh-cookie-name}")
	private String cookieName;
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse res){
		Cookie cookie = new Cookie(cookieName,null);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setSecure(secureRefreshToken);
		cookie.setPath(req.getContextPath() + "/oauth/token");
		res.addCookie(cookie);
		res.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
