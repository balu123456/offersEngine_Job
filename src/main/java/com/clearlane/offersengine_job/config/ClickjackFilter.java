package com.clearlane.offersengine_job.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

public class ClickjackFilter extends GenericFilterBean {

    /**
     * 
     * Cross-Site Request Forgery (OR) Cross-Site Request Forgery (CSRF)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURL().toString().contains("/general-error.html/")) {
            res.sendRedirect(req.getContextPath() + "/general-error.html");
        }

        String X_HTTP_METHOD_OVERRIDE_HEADER = "X-HTTP-Method-Override";
        String headerValue = req.getHeader(X_HTTP_METHOD_OVERRIDE_HEADER);
        res.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        res.setHeader("Cache-Control", "NO-CACHE, NO-STORE, MUST-REVALIDATE");
        res.setHeader("Pragma", "no-cache");
        res.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
        res.setHeader("EXPIRES", "0");
        res.setHeader("CONTENT-TYPE", "TEXT/HTML; charset=UTF-8");
        res.setHeader("X-CONTENT-TYPE-OPTIONS", "nosniff");
        res.setHeader("ACCESS-CONTROL-ALLOW-METHODS", "POST, GET, OPTIONS, DELETE");
        res.setHeader("ACCESS-CONTROL-ALLOW-HEADERS",
                "ORIGIN, X-REQUESTED-WITH, CONTENT-TYPE, ACCEPT, X-CSRF-TOKEN");

        if ((headerValue == null) || (headerValue.equalsIgnoreCase(req.getMethod()))) {
            chain.doFilter(request, response);
            if (res.getStatus() == HttpServletResponse.SC_FORBIDDEN
                    || res.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                    || res.getStatus() == HttpServletResponse.SC_NOT_IMPLEMENTED
                    || res.getStatus() == HttpServletResponse.SC_NOT_FOUND
                    || res.getStatus() == HttpServletResponse.SC_METHOD_NOT_ALLOWED
                    || res.getStatus() == HttpServletResponse.SC_UNAUTHORIZED
                    || res.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    || res.getStatus() == HttpServletResponse.SC_NOT_ACCEPTABLE
                    || res.getStatus() == HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE) {
                res.sendRedirect(req.getContextPath() + "/general-error.html");
            }
        }
        else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    @Override
    public void destroy() {
        // Do nothing because of default override.
    }
}