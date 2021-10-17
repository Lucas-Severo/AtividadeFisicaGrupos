package com.edu.framework.atividadefisica.dto;

import java.util.Optional;

import com.edu.framework.atividadefisica.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public boolean existsByEmail(String email);

    public Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha);

}
