package com.edu.framework.atividadefisica.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class CidadeApiHelper {
    private List<Municipio> municipios;

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public static class Municipio {
        private Long id;
        private String nome;
        private JsonNode microrregiao;

        @JsonProperty("regiao-imediata")
        private JsonNode regiaoImediata;
        
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
    
        public JsonNode getMicrorregiao() {
            return this.microrregiao;
        }
    
        public void setMicrorregiao(JsonNode microrregiao) {
            this.microrregiao = microrregiao;
        }
    
        public JsonNode getRegiaoImediata() {
            return this.regiaoImediata;
        }
    
        public void setRegiaoImediata(JsonNode regiaoImediata) {
            this.regiaoImediata = regiaoImediata;
        }
        
    }

    
}