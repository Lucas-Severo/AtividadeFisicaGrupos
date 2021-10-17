package com.edu.framework.atividadefisica.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.edu.framework.atividadefisica.dto.UsuarioRepository;
import com.edu.framework.atividadefisica.model.Usuario;
import com.edu.framework.atividadefisica.utils.AuthenticationHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    private String exibirTelaLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    private String efetuarLogin(@ModelAttribute("usuario") Usuario usuario, Model model, HttpServletResponse response) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findUsuarioByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioEncontrado.isPresent()) {
            Integer generatedToken = AuthenticationHelper.generate();
            Cookie cookie = new Cookie("sessionToken", Integer.toString(generatedToken));
            response.addCookie(cookie);
        } else {
            model.addAttribute("erroMessage", "Usuário ou Senha inválidos");
            return "login";
        }
        return "redirect:/";
    }

}
