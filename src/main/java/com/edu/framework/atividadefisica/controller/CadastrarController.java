package com.edu.framework.atividadefisica.controller;

import com.edu.framework.atividadefisica.dto.UsuarioRepository;
import com.edu.framework.atividadefisica.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CadastrarController {
 
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrar")
    public String exibirTelaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (!usuarioRepository.existsByEmail(usuario.getEmail())) {
            usuarioRepository.save(usuario);
            return "redirect:/login";
        } else {
            model.addAttribute("erroMessage", "Email já utilizado");

            return "cadastrar";
        }
    }

}
