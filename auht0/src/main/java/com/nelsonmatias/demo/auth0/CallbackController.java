package com.nelsonmatias.demo.auth0;


import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.auth0.client.auth.AuthAPI;
import com.auth0.json.auth.UserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.net.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
public class CallbackController {

    @Autowired
    private AuthController controller;
    private final String redirectOnFail;
    private final String redirectOnSuccess;
    

    public CallbackController() {
        this.redirectOnFail = "/login";
        this.redirectOnSuccess = "/readGreeting";
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    protected void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void postCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
        	// Responsible for getting an access token from the code that comes in the URL
        	Tokens tokens = controller.handle(req);
         
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());
            DecodedJWT decodeIdToken =	JWT.decode(tokens.getIdToken());
            
            SessionUtils.set(req, "userEmail", decodeIdToken.getClaim("https://myapi.com/email").asString());
            SessionUtils.set(req, "userJobTitle", decodeIdToken.getClaim("https://myapi.com/jobTitle").asString());
            SessionUtils.set(req, "nickName", decodeIdToken.getClaim("https://myapi.com/nickName").asString());
            
            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }

}

