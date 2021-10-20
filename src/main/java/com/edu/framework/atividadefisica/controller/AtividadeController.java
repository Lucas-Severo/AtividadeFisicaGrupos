package com.edu.framework.atividadefisica.controller;

import com.edu.framework.atividadefisica.dto.AtividadeRepository;
import com.edu.framework.atividadefisica.model.Atividade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AtividadeController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @GetMapping("/atividade")
    public String exibirListaModalidades() {
        return "atividade";
    }

    @GetMapping("/cadastrarAtividade")
    public String exibirTelaCadastroAtividade(Model model) {
        model.addAttribute("atividade", new Atividade());
        
        return "cadastrarAtividade";
    }

    @PostMapping("/cadastrarAtividade")
    public String cadastrarAtividade(@ModelAttribute Atividade atividade) {
        atividadeRepository.save(atividade);
        return "redirect:/atividade";
    }

}
