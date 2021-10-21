package com.edu.framework.atividadefisica.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.edu.framework.atividadefisica.dto.UsuarioRepository;
import com.edu.framework.atividadefisica.model.Usuario;
import com.edu.framework.atividadefisica.utils.AuthenticationHelper;
import com.edu.framework.atividadefisica.utils.UserDetails;

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

    @GetMapping("/exit")
    private String sair(HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionToken", "-1");
        response.addCookie(cookie);
        return "redirect:/login";
    }

    @PostMapping("/login")
    private String efetuarLogin(@ModelAttribute("usuario") Usuario usuario, Model model, HttpServletResponse response) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findUsuarioByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioEncontrado.isPresent()) {
            Usuario usuarioLogado = usuarioEncontrado.get();
            usuarioLogado.setSenha(null);

            Integer generatedToken = AuthenticationHelper.generate();
            Cookie sessionToken = new Cookie("sessionToken", Integer.toString(generatedToken));
            
            UserDetails.setUserLogged(response, usuarioLogado);
            response.addCookie(sessionToken);
        } else {
            model.addAttribute("erroMessage", "Usuário ou Senha inválidos");
            return "login";
        }
        return "redirect:/";
    }

}
