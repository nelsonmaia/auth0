package com.nelsonmatias.demo.auth0;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller responsible to create the URL needed for the user authenticatiton.
 * 
 * @author Nelson.Matias
 *
 */
@Component
public class AuthController {

	private final AuthenticationController controller;
	private final String userInfoAudience;

	@Autowired
	public AuthController(AppConfig config) {

		controller = AuthenticationController
				.newBuilder(config.getDomain(), config.getClientId(), config.getClientSecret()).build();

		userInfoAudience = String.format("https://%s/userinfo", config.getDomain());
	}

	public Tokens handle(HttpServletRequest request) throws IdentityVerificationException {
		return controller.handle(request);
	}

	/**
	 * Create the URL responsible for user authentication. It uses
	 * http://localhost:8080/greeting as audience, which allow the user to invoke
	 * the APIs
	 * 
	 * @param request
	 * @param redirectUri
	 * @return
	 */
	public String buildAuthorizeUrl(HttpServletRequest request, String redirectUri) {

		String s = controller.buildAuthorizeUrl(request, redirectUri).withAudience("http://localhost:8080/greeting")
				.build();

		System.out.println("String buildAuthorizeUrl is " + s);

		return s;
	}

}
