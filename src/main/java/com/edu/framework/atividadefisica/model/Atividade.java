package com.edu.framework.atividadefisica.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_atividade")
public class Atividade implements Serializable {
    
    @Id
    @Column(name = "at_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "at_nome")
    private String nome;

    @Column(name = "at_ritmo")
    private Double ritmo;

    @Column(name = "at_distancia")
    private Double distancia;

    @ManyToOne
    @JoinColumn(name = "us_id_criador")
    private Usuario criador;

    @ManyToOne
    @JoinColumn(name = "lo_id")
    private Localidade localidade;
    
    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Modalidade modalidade;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "at_dthr_inicio")
    private LocalDateTime data;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getRitmo() {
        return this.ritmo;
    }

    public void setRitmo(Double ritmo) {
        this.ritmo = ritmo;
    }

    public Double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Usuario getCriador() {
        return this.criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Modalidade getModalidade() {
        return this.modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public static class Filtro {
        private String nome;

        private String localidade;

        private Double ritmo;

        private String modalidade;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime data;

        private Double distancia;

        private String conteudo;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getLocalidade() {
            return localidade;
        }

        public void setLocalidade(String localidade) {
            this.localidade = localidade;
        }

        public Double getRitmo() {
            return ritmo;
        }

        public void setRitmo(Double ritmo) {
            this.ritmo = ritmo;
        }

        public String getModalidade() {
            return modalidade;
        }

        public void setModalidade(String modalidade) {
            this.modalidade = modalidade;
        }

        public LocalDateTime getData() {
            return data;
        }

        public void setData(LocalDateTime data) {
            this.data = data;
        }

        public Double getDistancia() {
            return distancia;
        }

        public void setDistancia(Double distancia) {
            this.distancia = distancia;
        }

        public void setModalidade(LocalDateTime data) {
            this.data = data;
        }

        public void setModalidade(Double distancia) {
            this.distancia = distancia;
        }

        public String getConteudo() {
            return conteudo;
        }

        public void setConteudo(String conteudo) {
            this.conteudo = conteudo;
        }

    }

}
