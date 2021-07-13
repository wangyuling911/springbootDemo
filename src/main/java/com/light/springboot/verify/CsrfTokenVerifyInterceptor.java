package com.light.springboot.verify;

import com.light.springboot.exception.CsrfTokenException;
import com.light.springboot.jwt.IJwtTokenService;
import com.light.springboot.jwt.JwtTokenService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import joptsimple.internal.Strings;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * An interceptor implementation which verify CSRF token.
 * 
 * @author achen
 *
 */
public class CsrfTokenVerifyInterceptor implements HandlerInterceptor {
	@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
	private final String CSRF_TOKEN_HEADER = "Easemob-Csrf-Token";
	@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
	private final String COOKIE_SESSION = "SESSION";
	private String[] ignoreUrlPrefix;
    private String jwtKey;
	private IJwtTokenService jwtTokenService;
	private List<Pattern> uriPatterns;
    
    /**
     * @param jwtKey The jwt secret key.
     * @param ignoreUrlPrefix The list of URL prefixes. If an target URL starts with one of those prefixes then 
     * 							 it will be ignored.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CsrfTokenVerifyInterceptor(String jwtKey, String[] ignoreUrlPrefix) {
    	this.jwtKey = jwtKey;
    	this.jwtTokenService = new JwtTokenService(this.jwtKey);
    	this.ignoreUrlPrefix = ignoreUrlPrefix;
    	initPatterns(ignoreUrlPrefix);
    }
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (isIgnore(request, ignoreUrlPrefix)) {
			return true;
		}
		Cookie cookie = getCookie(request, COOKIE_SESSION);
		if (cookie == null){
			throw new CsrfTokenException("Can not find session from request.");
		}
		String session = cookie.getValue();
		String tokenStr = getToken(request);
		if (Strings.isNullOrEmpty(tokenStr)) {
			throw new CsrfTokenException("Can not find csrf token from request.");
		}
		
		if (!validToken(tokenStr,session)) {
			throw new CsrfTokenException("Csrf token check failed.");
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	private boolean isIgnore(HttpServletRequest request, String[] ignoreUrlPrefix) {
		String path = request.getRequestURI();
		
		for (String urlPrefix : ignoreUrlPrefix) {
            if (path.startsWith(urlPrefix) || path.endsWith(urlPrefix) || isMatch(urlPrefix, request)) {
				return true;
			}
		}
		
		return false;
	}
	
	private String getToken(HttpServletRequest request) {
		String headerToken = request.getHeader(CSRF_TOKEN_HEADER);
		if (!Strings.isNullOrEmpty(headerToken)) {
			return headerToken;
		}

		return null;
	}
	
	private boolean validToken(String token, String session) {
		return jwtTokenService.isCsrfTokenValid(token,session);
	}

	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (StringUtils.equals(cookieName, cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	private void initPatterns(String[] ignoreUrlPrefix) {
    	if (uriPatterns == null) {
    		uriPatterns = new ArrayList();
		}
		if (ignoreUrlPrefix == null || ignoreUrlPrefix.length == 0) {
			return;
		}
		for (String urlPrefix : ignoreUrlPrefix) {
			if (urlPrefix.startsWith("/")) {
				continue;
			}
			String configUri = urlPrefix.substring(urlPrefix.indexOf(" ")).trim();
			uriPatterns.add(Pattern.compile(configUri));
		}
	}

	private boolean isMatch(String configUri, HttpServletRequest request) {
		String path = request.getRequestURI();
		// 忽略精准匹配的uri
		if (configUri.startsWith("/")) {
			return false;
		}
		String configMethod = configUri.substring(0, configUri.indexOf(" "));
		if (request.getMethod().equalsIgnoreCase(configMethod) && matchUriWithPatterns(path)) {
			return true;
		}
		return false;
	}

	private boolean matchUriWithPatterns(String uri) {
		if (uriPatterns == null || uriPatterns.size() == 0) {
			return false;
		}
		for (Pattern pattern : uriPatterns) {
			if (pattern.matcher(uri).matches()) {
				return true;
			}
		}
		return false;
	}
}
