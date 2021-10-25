package com.edu.framework.atividadefisica.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.edu.framework.atividadefisica.model.Atividade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
    @Query("SELECT atividade FROM Atividade atividade WHERE" +
    "(:nome is '' OR atividade.nome LIKE :nome) " +
    "AND (:localidade is '' OR atividade.localidade.cidade LIKE :localidade) " +
    "AND (:ritmo is null OR atividade.ritmo LIKE :ritmo) " +
    "AND (:modalidade is '' OR atividade.modalidade.nome LIKE :modalidade) " +
    "AND (:data is null OR atividade.data = :data) " +
    "AND (:distancia is null OR atividade.distancia LIKE :distancia)")
    List<Atividade> findAllByNomeAndLocalidadeAndRitmoAndModalidadeAndDataAndDistancia(String nome, String localidade, Double ritmo, String modalidade, LocalDateTime data, Double distancia);

    @Query("SELECT atividade FROM Atividade atividade WHERE (atividade.nome LIKE :conteudo)"
        + "OR (atividade.ritmo LIKE :conteudo) OR (atividade.distancia LIKE :conteudo)")
    List<Atividade> findAllByNomeAndRitmoAndDistancia(String conteudo);
}
