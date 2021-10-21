package com.edu.framework.atividadefisica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.edu.framework.atividadefisica.dto.ModalidadeRepository;
import com.edu.framework.atividadefisica.model.Modalidade;
import com.edu.framework.atividadefisica.utils.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ModalidadeController {
    
    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @GetMapping("/modalidade")
    public String exibirListaModalidades(Model model, HttpServletRequest request) {
        List<Modalidade> modalidades = modalidadeRepository.findAll();
        model.addAttribute("userLoggedEmail", UserDetails.getUserLogged(request).getEmail());

        model.addAttribute("modalidades", modalidades);
        model.addAttribute("selectedOption", "modalidade");
        model.addAttribute("pageTitle", "Modalidades");

        return "modalidade";
    }

}
