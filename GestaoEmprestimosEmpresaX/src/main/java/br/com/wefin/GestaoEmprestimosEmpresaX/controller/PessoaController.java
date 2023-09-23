package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.PessoaService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    @Autowired
    private PessoaService pessoaService;

     @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/listarPessoas")
    public String listarPessoas(Model model) {
        logger.info("Iniciando a listagem de pessoas.");
        List<PessoaDTO> pessoas = pessoaService.findAll();
        model.addAttribute("pessoas", pessoas);
        logger.info("Listagem de pessoas concluída.");
        return "listarPessoas";
    }

    @PostMapping("/criarPessoa")
    public String criarPessoa(@ModelAttribute PessoaDTO pessoaDTO) {
        logger.info("Iniciando o processo de criação de uma nova pessoa. "+pessoaDTO.toString());
        pessoaService.save(pessoaDTO);
        logger.info("Pessoa criada com sucesso.");
        return "redirect:/listarPessoas";
    }

    // Método para buscar uma pessoa por ID
    @GetMapping("/buscarPessoa/{id}")
    public String buscarPessoa(@PathVariable Long id, Model model) {
        logger.info("Buscando pessoa com ID: " + id);
        PessoaDTO pessoa = pessoaService.findById(id);
        model.addAttribute("pessoa", pessoa);
        logger.info("Pessoa encontrada.");
        return "buscarPessoa";
    }

    // Método para deletar uma pessoa por ID
    @PostMapping("/deletarPessoa/{id}")
    public String deletarPessoa(@PathVariable Long id) {
        logger.info("Deletando pessoa com ID: " + id);
        pessoaService.delete(id);
        logger.info("Pessoa deletada com sucesso.");
        return "redirect:/listarPessoas";
    }

}
