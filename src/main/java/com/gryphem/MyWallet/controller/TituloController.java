package com.gryphem.MyWallet.controller;

import com.gryphem.MyWallet.model.StatusTitulo;
import com.gryphem.MyWallet.model.Titulo;
import com.gryphem.MyWallet.repository.Titulos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    private static final String CADASTRO_VIEW= "CadastroTitulo";

    @Autowired
    private Titulos titulos;

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(new Titulo());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        if (errors.hasErrors()) {
            return CADASTRO_VIEW;
        }
        try {
            titulos.save(titulo);
            redirectAttributes.addFlashAttribute("mensagem", "Tìtulo salvo com sucesso!");
            return "redirect:/titulos/novo";
        }catch (DataIntegrityViolationException e){
            errors.rejectValue("dataVencimento", null, "Formato de data inválido!");
            return CADASTRO_VIEW;
        }


    }

    @RequestMapping
    public ModelAndView pesquisar() {
        List<Titulo> todosTitulos = titulos.findAll();
        ModelAndView mv = new ModelAndView("PesquisaTitulos");
        mv.addObject("titulos", todosTitulos);
        return mv;
    }

    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }


    @RequestMapping("{codigo}")
    public ModelAndView edicao(@PathVariable Long codigo){
        Titulo titulo = titulos.findById(codigo).orElse(new Titulo());


        ModelAndView mv =  new ModelAndView(CADASTRO_VIEW);

        mv.addObject(titulo);
        return mv;
    }
    @RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
    public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
        titulos.deleteById(codigo);

        attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
        return "redirect:/titulos";
    }

}
