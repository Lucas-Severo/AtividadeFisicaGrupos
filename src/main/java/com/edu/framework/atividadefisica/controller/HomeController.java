package com.edu.framework.atividadefisica.controller;

import com.edu.framework.atividadefisica.model.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String exibirHome(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("pageTitle", "Home");

        return "home";
    }

}
