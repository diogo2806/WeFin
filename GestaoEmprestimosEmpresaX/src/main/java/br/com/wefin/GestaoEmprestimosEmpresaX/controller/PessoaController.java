package br.com.wefin.GestaoEmprestimosEmpresaX.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.PessoaService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController  // Indica que esta classe é um controlador REST
@RequestMapping("/api/pessoas")  // Define o caminho base para os endpoints deste controlador

public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    @Autowired // Injeta o serviço de pessoas
    private PessoaService pessoaService;


    // Manipula exceções do tipo IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Define o status HTTP para Bad Request
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return errorResponse;
    }

    // Configura o WebDataBinder para tratar datas
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    // Endpoint para listar todas as pessoas
    @GetMapping("/listar")
    public List<PessoaDTO> listarPessoas() {
        logger.warn("Iniciando a listagem de pessoas.");
        List<PessoaDTO> pessoas = pessoaService.findAll();
        logger.warn("Listagem de pessoas concluída.");
        return pessoas;
    }

    // Endpoint para criar uma nova pesso
    @PostMapping("/criar")
    public void criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        logger.info("Iniciando o processo de criação de uma nova pessoa. " + pessoaDTO.toString());
        pessoaService.save(pessoaDTO);
        logger.info("Pessoa criada com sucesso.");
    }

    // Endpoint para buscar uma pessoa pelo ID
    @GetMapping("/buscar/{id}")
    public PessoaDTO buscarPessoa(@PathVariable Long id) {
        logger.info("Buscando pessoa com ID: " + id);
        PessoaDTO pessoa = pessoaService.findById(id);
        logger.info("Pessoa encontrada.");
        return pessoa;
    }
    
    // Endpoint para deletar uma pessoa pelo ID
    @DeleteMapping("/deletar/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        logger.info("Deletando pessoa com ID: " + id);
        pessoaService.delete(id);
        logger.info("Pessoa deletada com sucesso.");
    }
}
