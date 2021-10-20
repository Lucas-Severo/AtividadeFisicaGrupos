package com.edu.framework.atividadefisica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_localidade")
public class Localidade {
    
    @Id
    @Column(name = "lo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lo_estado")
    private String estado;

    @Column(name = "lo_cidade")
    private String cidade;

    @Column(name = "lo_estado_sigla")
    private String estadoSigla;

    public Localidade() {}

    public Localidade(String estado, String cidade, String estadoSigla) {
        this.estado = estado;
        this.cidade = cidade;
        this.estadoSigla = estadoSigla;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSigla() {
        return this.estadoSigla;
    }

    public void setSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

}
