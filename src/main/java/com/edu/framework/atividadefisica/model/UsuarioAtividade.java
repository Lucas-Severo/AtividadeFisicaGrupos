package com.edu.framework.atividadefisica.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_atividade_usuario")
public class UsuarioAtividade implements Serializable {
    
    @EmbeddedId
    private UsuarioAtividadePK usuarioAtividadePK;

    @Column(name = "au_dthr_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataRegistro;

    public UsuarioAtividadePK getUsuarioAtividadePK() {
        return this.usuarioAtividadePK;
    }

    public void setUsuarioAtividadePK(UsuarioAtividadePK usuarioAtividadePK) {
        this.usuarioAtividadePK = usuarioAtividadePK;
    }

    public LocalDateTime getDataRegistro() {
        return this.dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
