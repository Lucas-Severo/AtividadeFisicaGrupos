package com.edu.framework.atividadefisica.dto;

import com.edu.framework.atividadefisica.model.Localidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long>{
    
}
