package com.edu.framework.atividadefisica.controller;

import javax.servlet.http.HttpServletRequest;

import com.edu.framework.atividadefisica.model.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String exibirHome(Model model, HttpServletRequest request) {
        model.addAttribute("usuario", new Usuario());

        return "home";
    }

}
