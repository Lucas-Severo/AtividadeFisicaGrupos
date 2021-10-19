package com.edu.framework.atividadefisica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AtividadeController {

    @GetMapping("/atividade")
    public String exibirListaModalidades() {
        return "atividade";
    }

}
