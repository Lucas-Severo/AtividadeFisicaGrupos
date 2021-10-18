package com.edu.framework.atividadefisica.dto;

import com.edu.framework.atividadefisica.model.Modalidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {
    
}
