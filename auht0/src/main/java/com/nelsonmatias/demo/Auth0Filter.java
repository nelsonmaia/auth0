package com.nelsonmatias.demo;

import com.auth0.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *  Filter responsible for intercepting requests and check if they are authenticated. If not, uses Auth0 to authenticate.
 * @author Nelson.Matias
 *
 */
public class Auth0Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        HttpServletResponse res = (HttpServletResponse) response;
        String accessToken = (String) SessionUtils.get(req, "accessToken");
        String idToken = (String) SessionUtils.get(req, "idToken");
        if (accessToken == null && idToken == null) {
            res.sendRedirect("/login");
            return;
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}