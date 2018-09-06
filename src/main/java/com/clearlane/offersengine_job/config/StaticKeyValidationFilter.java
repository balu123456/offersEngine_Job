package com.clearlane.offersengine_job.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;

@Component
public class StaticKeyValidationFilter extends GenericFilterBean {

	@Autowired
    private OffersEngineJobProperties fixedservicesProperties;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        /*String authKey = httpServletRequest.getHeader(fixedservicesProperties.getAuthkey_header());
        if (authKey != null
                && authKey.equalsIgnoreCase(fixedservicesProperties.getAuthkey_header_value())) {
            filterChain.doFilter(request, response);
        }
        else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletRequest.getRequestDispatcher("/general-error.html")
                    .forward(httpServletRequest, httpServletResponse);
            return;
        }*/
        filterChain.doFilter(request, response);
    }

}
