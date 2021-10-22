package com.edu.framework.atividadefisica.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioAtividadePK implements Serializable {

    @JoinColumn(name = "us_id")
    @ManyToOne
    private Usuario usuario;
    
    @JoinColumn(name = "at_id")
    @ManyToOne
    private Atividade atividade;

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atividade getAtividade() {
        return this.atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
}
