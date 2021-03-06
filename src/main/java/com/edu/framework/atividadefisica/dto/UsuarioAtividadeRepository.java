package com.edu.framework.atividadefisica.dto;

import java.util.List;

import com.edu.framework.atividadefisica.model.UsuarioAtividade;
import com.edu.framework.atividadefisica.model.UsuarioAtividadePK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAtividadeRepository extends JpaRepository<UsuarioAtividade, UsuarioAtividadePK>{
    
    List<UsuarioAtividade> findAllByUsuarioAtividadePK_Atividade_Id(Long atividadeId);

    Long countByUsuarioAtividadePK_Atividade_Id(Long atividadeId);

    void deleteAllByUsuarioAtividadePK_Atividade_Id(Long atividadeId);
}
