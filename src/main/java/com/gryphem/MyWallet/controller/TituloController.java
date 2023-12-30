package com.gryphem.MyWallet.controller;

import com.gryphem.MyWallet.model.Titulo;
import com.gryphem.MyWallet.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    @Autowired
    private Titulos titulos;
    @RequestMapping("/novo")
    public String novo(){
        return "CadastroTitulo";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvar(Titulo titulo){
        System.out.println(">>>>>" + titulo.getDescricao());
        titulos.save(titulo);
        ModelAndView mv = new ModelAndView("CadastroTitulo");
        mv.addObject("mensagem","Tìtulo salvo com sucesso!");
        return mv;

    }
}
