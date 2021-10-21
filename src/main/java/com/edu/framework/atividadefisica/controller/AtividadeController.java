package com.edu.framework.atividadefisica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.edu.framework.atividadefisica.dto.AtividadeRepository;
import com.edu.framework.atividadefisica.dto.LocalidadeRepository;
import com.edu.framework.atividadefisica.dto.ModalidadeRepository;
import com.edu.framework.atividadefisica.model.Atividade;
import com.edu.framework.atividadefisica.model.Localidade;
import com.edu.framework.atividadefisica.model.Modalidade;
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
public class AtividadeController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private LocalidadeRepository localidadeRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @GetMapping("/atividade")
    public String exibirListaModalidades(Model model) {
        List<Atividade> atividades = atividadeRepository.findAll();
        model.addAttribute("atividades", atividades);
        model.addAttribute("selectedOption", "atividade");
        model.addAttribute("pageTitle", "Atividades");

        return "atividade";
    }

    @GetMapping("/cadastrarAtividade")
    public String exibirTelaCadastroAtividade(Model model) {
        model.addAttribute("atividade", new Atividade());
        model.addAttribute("selectedOption", "atividade");
        model.addAttribute("pageTitle", "Cadastrar Atividade");

        List<Localidade> localidades = localidadeRepository.findAll();
        List<Modalidade> modalidades = modalidadeRepository.findAll();

        model.addAttribute("localidades", localidades);
        model.addAttribute("modalidades", modalidades);
        
        return "cadastrarAtividade";
    }

    @PostMapping("/cadastrarAtividade")
    public String cadastrarAtividade(@ModelAttribute Atividade atividade, HttpServletRequest request) {
        atividade.setCriador(UserDetails.getUserLogged(request));
        atividadeRepository.save(atividade);
        return "redirect:/atividade";
    }

}
