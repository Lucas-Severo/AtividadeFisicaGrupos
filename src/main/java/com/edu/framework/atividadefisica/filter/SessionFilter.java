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
        "login",
        "cadastrar"
    };

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Integer sessionToken = null;

        if (!isPublicRoute(request.getRequestURI().toString())) {
            if (Objects.nonNull(request.getCookies())) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("sessionToken")) {
                        sessionToken = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }

            if (!AuthenticationHelper.isTokenValidate(sessionToken)) {
                response.sendRedirect("login");
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicRoute(String path) {
        for (String url: PUBLIC_URLS) {
            if (path.contains(url)) {
                return true;
            }
        }
        return false;
    }

}
