package com.gryphem.MyWallet.service;

import com.gryphem.MyWallet.model.Titulo;
import com.gryphem.MyWallet.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroTituloService {
    @Autowired
    private Titulos titulos;

    public void salvar(Titulo titulo) {
        try {

            titulos.save(titulo);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Formato da data invalido");
        }
    }

    public void excluir(Long codigo) {
        titulos.deleteById(codigo);
    }
}


