package com.edu.framework.atividadefisica.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.edu.framework.atividadefisica.dto.AtividadeRepository;
import com.edu.framework.atividadefisica.dto.LocalidadeRepository;
import com.edu.framework.atividadefisica.dto.ModalidadeRepository;
import com.edu.framework.atividadefisica.dto.UsuarioAtividadeRepository;
import com.edu.framework.atividadefisica.model.Atividade;
import com.edu.framework.atividadefisica.model.Localidade;
import com.edu.framework.atividadefisica.model.Modalidade;
import com.edu.framework.atividadefisica.model.Usuario;
import com.edu.framework.atividadefisica.model.UsuarioAtividade;
import com.edu.framework.atividadefisica.model.UsuarioAtividadePK;
import com.edu.framework.atividadefisica.utils.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AtividadeController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private LocalidadeRepository localidadeRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private UsuarioAtividadeRepository usuarioAtividadeRepository;

    @GetMapping("/atividade")
    public String exibirListaModalidades(Model model, @ModelAttribute Optional<Atividade.Filtro> atividadeFiltro) {
        List<Atividade> atividades;

        if(Objects.nonNull(atividadeFiltro.get().getConteudo()) && !atividadeFiltro.get().getConteudo().isEmpty()) {
            String filtro = atividadeFiltro.get().getConteudo();
            atividades = atividadeRepository.findAllByNomeAndRitmoAndDistancia(filtro);
            model.addAttribute("atividadeFiltro", new Atividade.Filtro());
        } else if(atividadeFiltro.isPresent() && !buscaVazia(atividadeFiltro.get())) {
            Atividade.Filtro atividadeFiltrada = atividadeFiltro.get();
            atividades = atividadeRepository.findAllByNomeAndLocalidadeAndRitmoAndModalidadeAndDataAndDistancia(atividadeFiltrada.getNome(), atividadeFiltrada.getLocalidade(), atividadeFiltrada.getRitmo(), atividadeFiltrada.getModalidade(), atividadeFiltrada.getData(), atividadeFiltrada.getDistancia());
            model.addAttribute("atividadeFiltro", atividadeFiltro.get());
            System.out.println(atividadeFiltrada);
        } else {
            atividades = atividadeRepository.findAll();
            model.addAttribute("atividadeFiltro", new Atividade.Filtro());
        }
        
        model.addAttribute("atividades", atividades);
        model.addAttribute("selectedOption", "atividade");
        model.addAttribute("pageTitle", "Atividades");

        List<Modalidade> modalidades = modalidadeRepository.findAll();
        model.addAttribute("modalidades", modalidades);

        return "atividade";
    }

    public boolean buscaVazia(Atividade.Filtro atividade) {
        return (Objects.isNull(atividade.getNome()) || 
            atividade.getNome().isEmpty()) &&
            (Objects.isNull(atividade.getNome()) ||
            atividade.getLocalidade().isEmpty()) &&
            Objects.isNull(atividade.getRitmo()) &&
            (Objects.isNull(atividade.getModalidade()) ||
            atividade.getModalidade().isEmpty()) &&
            Objects.isNull(atividade.getData()) &&
            Objects.isNull(atividade.getDistancia());
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
        Usuario usuario = UserDetails.getUserLogged(request);
        atividade.setCriador(usuario);
        Atividade atividadeSalva = atividadeRepository.save(atividade);

        UsuarioAtividadePK usuarioAtividadePK = new UsuarioAtividadePK();
        usuarioAtividadePK.setAtividade(atividadeSalva);
        usuarioAtividadePK.setUsuario(usuario);

        UsuarioAtividade usuarioAtividade = new UsuarioAtividade();
        usuarioAtividade.setUsuarioAtividadePK(usuarioAtividadePK);
        usuarioAtividade.setDataRegistro(LocalDateTime.now());
        usuarioAtividadeRepository.save(usuarioAtividade);

        return "redirect:/visualizarAtividade/"+atividadeSalva.getId();
    }

    @GetMapping("/excluirAtividade/{id}")
    @Transactional
    public String excluirAtividade(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuario = UserDetails.getUserLogged(request);
       
        Optional<Atividade> atividade = atividadeRepository.findById(id);

        if (atividade.isPresent() && atividade.get().getCriador().getId().equals(usuario.getId())) {
            if(usuarioAtividadeRepository.countByUsuarioAtividadePK_Atividade_Id(atividade.get().getId()) == 1) {
                Long atividadeId = atividade.get().getId();    
                usuarioAtividadeRepository.deleteAllByUsuarioAtividadePK_Atividade_Id(atividadeId);
                atividadeRepository.deleteById(atividadeId);
            }
        }

        return "redirect:/atividade";
    }

    @GetMapping("/visualizarAtividade/{id}")
    public String visualizarAtividade(Model model, @PathVariable Long id, HttpServletRequest request) {
        Atividade atividade = null;

        Optional<Atividade> atividadeInformada = atividadeRepository.findById(id);
        if(atividadeInformada.isPresent()) {
            atividade = atividadeInformada.get();
            Usuario usuarioLogado = UserDetails.getUserLogged(request);
            boolean usuarioCriador = retornaSeUsuarioCriadoDaAtividade(atividade.getCriador(), usuarioLogado);

            UsuarioAtividadePK usuarioAtividadePK = new UsuarioAtividadePK();
            usuarioAtividadePK.setAtividade(atividade);
            usuarioAtividadePK.setUsuario(usuarioLogado);

            Optional<UsuarioAtividade> usuarioAtividade = usuarioAtividadeRepository.findById(usuarioAtividadePK);

            List<UsuarioAtividade> usuarios = buscarParticipantesAtividade(atividade.getId());
            Long usuariosRegistrados = buscarQuantidadeParticipantesAtividade(atividade.getId());

            model.addAttribute("usuarioParticipante", usuarioAtividade.isPresent());
            model.addAttribute("usuarioCriador", usuarioCriador);
            model.addAttribute("usuariosAtividade", usuarios);
            model.addAttribute("quantidadeUsuariosAtividade", usuariosRegistrados);
        }

        model.addAttribute("atividade", atividade);
        model.addAttribute("selectedOption", "atividade");
        model.addAttribute("pageTitle", "Visualizar Atividade");

        return "visualizarAtividade";
    }

    public boolean retornaSeUsuarioCriadoDaAtividade(Usuario usuarioCriadorAtividade, Usuario usuarioLogado) {
        return usuarioCriadorAtividade.getId().equals(usuarioLogado.getId());
    }

    
    private List<UsuarioAtividade> buscarParticipantesAtividade(Long atividadeId) {
        List<UsuarioAtividade> usuarios = usuarioAtividadeRepository.findAllByUsuarioAtividadePK_Atividade_Id(atividadeId);
        return usuarios;
    }

    private Long buscarQuantidadeParticipantesAtividade(Long atividadeId) {
        return usuarioAtividadeRepository.countByUsuarioAtividadePK_Atividade_Id(atividadeId);
    }

    @GetMapping("/participar/{id}")
    public String participarAtividade(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuario = UserDetails.getUserLogged(request);
        Optional<Atividade> atividade = atividadeRepository.findById(id);

        if(!atividade.isPresent()) {
            return "redirect:/visualizarAtividade/" + id;
        }

        UsuarioAtividadePK usuarioAtividadePK = new UsuarioAtividadePK();
        usuarioAtividadePK.setAtividade(atividade.get());
        usuarioAtividadePK.setUsuario(usuario);

        Optional<UsuarioAtividade> usuarioAtividade = usuarioAtividadeRepository.findById(usuarioAtividadePK);

        if(!usuarioAtividade.isPresent()){
            UsuarioAtividade usuarioAtividadeNovo = new UsuarioAtividade();
            usuarioAtividadeNovo.setUsuarioAtividadePK(usuarioAtividadePK);
            usuarioAtividadeNovo.setDataRegistro(LocalDateTime.now());

            usuarioAtividadeRepository.save(usuarioAtividadeNovo);
        }

        return "redirect:/visualizarAtividade/" + id;
    }

    @GetMapping("/sair/{id}")
    public String sairDaAtividade(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuario = UserDetails.getUserLogged(request);
        Optional<Atividade> atividade = atividadeRepository.findById(id);

        if(!atividade.isPresent()) {
            return "redirect:/visualizarAtividade/" + id;
        }

        UsuarioAtividadePK usuarioAtividadePK = new UsuarioAtividadePK();
        usuarioAtividadePK.setAtividade(atividade.get());
        usuarioAtividadePK.setUsuario(usuario);

        Optional<UsuarioAtividade> usuarioAtividade = usuarioAtividadeRepository.findById(usuarioAtividadePK);

        if(usuarioAtividade.isPresent() && 
           !retornaSeUsuarioCriadoDaAtividade(usuario, atividade.get().getCriador())
        ){
            UsuarioAtividade usuarioAtividadeRemover = new UsuarioAtividade();
            usuarioAtividadeRemover.setUsuarioAtividadePK(usuarioAtividadePK);

            usuarioAtividadeRepository.delete(usuarioAtividadeRemover);
        }

        return "redirect:/visualizarAtividade/" + id;
    }

}
