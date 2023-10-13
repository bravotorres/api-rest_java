package com.quironlabs.api.config.filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.quironlabs.api.utils.JwtUtils;


@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            if (!hasAuthorizationBearer(request)) {
                // throw new ServletException("JWT No autorizado");
                logger.info("JWT No autorizado");
                filterChain.doFilter(request, response);
                return;
            }
    
            String token = getAccessToken(request);
    
            // Pass if JWT is ok.
            jwtUtils.validateAccessToken(token);
    
            setAuthenticationContext(token, request);
            filterChain.doFilter(request, response);
        } catch(Exception e) {
            logger.info("Filter JWT Error: {}", e.getMessage());
            this.exceptionResolver.resolveException(request, response, null, e);
        }
    }

    private boolean hasAuthorizationBearer(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("Authorization");
        
        logger.info("False: {} - {}", ObjectUtils.isEmpty(header), !header.startsWith("Bearer"));
        
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            
            return false;
        }
        return true;
        
    }
    
    private String getAccessToken(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        
        return token;
    }

    private void setAuthenticationContext(String token, ServletRequest request) throws IOException {
        Authentication authentication = jwtUtils.getAuthentication((HttpServletRequest)request, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // filterChain.doFilter(request, response);
        
//        UserDetails userDetails = getUserDetails(token);
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
//
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.setAuthentication(authentication);
        
        
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);

        // Authenticate the user
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//
//        // Create a new session and add the security context.
//        HttpSession session = request.getSession(true);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

//    private AuthUser getUserDetails(String token) {
//        String sub = jwtUtils.getSubject(token);
//
//        AuthUser user = new AuthUser(sub, "");
//
//        return user;
//    }


//    @Override
//    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
//        javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain)
//        throws javax.servlet.ServletException, IOException {
//        // TODO Auto-generated method stub
//        
//    }

}
