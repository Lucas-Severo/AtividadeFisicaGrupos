package com.edu.framework.atividadefisica.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.framework.atividadefisica.utils.AuthenticationHelper;

import org.springframework.stereotype.Component;

@Component
public class SessionFilter extends HttpFilter {

    private String[] PUBLIC_URLS = new String[]{
        "img",
        "css",
        "login",
        "cadastrar"
    };

    private String[] AUTH_ROUTES = new String[]{
        "login",
        "cadastrar"
    };

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Integer sessionToken = null;

        if (Objects.nonNull(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("sessionToken")) {
                    sessionToken = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }


        if (isAuthRoute(request.getRequestURI().toString()) && AuthenticationHelper.isTokenValidate(sessionToken)) {
            response.sendRedirect("/");
        } else if (!AuthenticationHelper.isTokenValidate(sessionToken) && !isPublicRoute(request.getRequestURI().toString())) {            
            response.sendRedirect("/login");
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthRoute(String url) {
        String pathUrl = convertUrlToPath(url);
        for (String path: AUTH_ROUTES) {
            if (path.equals(pathUrl)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPublicRoute(String url) {
        String pathUrl = convertUrlToPath(url);
        for (String path: PUBLIC_URLS) {
            if (path.equals(pathUrl)) {
                return true;
            }
        }
        return false;
    }

    private String convertUrlToPath(String url) {
        String[] urlSplit = url.split("/");
        if(urlSplit.length > 1) {
            return urlSplit[1];
        } else {
            return "/";
        }
    }

}
