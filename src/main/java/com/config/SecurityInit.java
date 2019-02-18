package com.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

@Component
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, new MultipartFilter());
    }
}
