package com.gryphem.MyWallet.service;

import com.gryphem.MyWallet.model.StatusTitulo;
import com.gryphem.MyWallet.model.Titulo;
import com.gryphem.MyWallet.repository.Titulos;
import com.gryphem.MyWallet.repository.filter.TituloFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void receber(Long codigo) {
        Titulo titulo =  titulos.findById(codigo).orElseThrow(()-> new NoSuchElementException("Título de número " + codigo+"não encontrado"));;
        titulo.setStatus(StatusTitulo.RECEBIDO);
        titulos.save(titulo);
    }

    public List<Titulo> filtrarTitulo(TituloFilter filtro) {
        return titulos.findByDescricaoContaining(filtro.getDescricao());
    }
}


