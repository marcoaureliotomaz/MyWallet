package com.gryphem.MyWallet.repository;

import com.gryphem.MyWallet.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Titulos extends JpaRepository<Titulo, Long> {

    public List<Titulo> findByDescricaoContaining(String descricao);

}
