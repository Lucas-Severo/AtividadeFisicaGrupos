package com.edu.framework.atividadefisica.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.framework.atividadefisica.model.Usuario;
import com.google.gson.Gson;

public class UserDetails {
    
    public static void setUserLogged(HttpServletResponse httpResponse, Usuario usuario) {
       System.out.println(usuario.getId());
        String userLogged = new Gson().toJson(usuario);
        Cookie userAuth = new Cookie("userAuth", URLEncoder.encode(userLogged, StandardCharsets.UTF_8));
        httpResponse.addCookie(userAuth);
    }

    public static Usuario getUserLogged(HttpServletRequest httpRequest) {
        for(Cookie cookie: httpRequest.getCookies()) {
            if (cookie.getName().equals("userAuth")) {
                String value = cookie.getValue();
                String valueDecoded = URLDecoder.decode(value, StandardCharsets.UTF_8);
                Usuario usuario = new Gson().fromJson(valueDecoded, Usuario.class);

                return usuario;
            }
        }
        throw new RuntimeException("Usuário inválido");
    }

}
