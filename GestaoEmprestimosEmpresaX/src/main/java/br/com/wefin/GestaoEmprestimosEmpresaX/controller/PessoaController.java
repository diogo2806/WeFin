package br.com.wefin.GestaoEmprestimosEmpresaX.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.PessoaService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
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

    @GetMapping("/listar")
    public List<PessoaDTO> listarPessoas() {
        logger.warn("Iniciando a listagem de pessoas.");
        List<PessoaDTO> pessoas = pessoaService.findAll();
        logger.warn("Listagem de pessoas concluída.");
        return pessoas;
    }

    @PostMapping("/criar")
    public void criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        logger.info("Iniciando o processo de criação de uma nova pessoa. " + pessoaDTO.toString());
        pessoaService.save(pessoaDTO);
        logger.info("Pessoa criada com sucesso.");
    }

    @GetMapping("/buscar/{id}")
    public PessoaDTO buscarPessoa(@PathVariable Long id) {
        logger.info("Buscando pessoa com ID: " + id);
        PessoaDTO pessoa = pessoaService.findById(id);
        logger.info("Pessoa encontrada.");
        return pessoa;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        logger.info("Deletando pessoa com ID: " + id);
        pessoaService.delete(id);
        logger.info("Pessoa deletada com sucesso.");
    }
}
