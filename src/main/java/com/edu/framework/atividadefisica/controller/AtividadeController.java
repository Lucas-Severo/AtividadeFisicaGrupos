package com.edu.framework.atividadefisica.controller;

import com.edu.framework.atividadefisica.model.Atividade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AtividadeController {

    @GetMapping("/atividade")
    public String exibirListaModalidades() {
        return "atividade";
    }

    @GetMapping("/cadastrarAtividade")
    public String cadastrarAtividade(Model model) {
        model.addAttribute("atividade", new Atividade());
        
        return "cadastrarAtividade";
    }

}
