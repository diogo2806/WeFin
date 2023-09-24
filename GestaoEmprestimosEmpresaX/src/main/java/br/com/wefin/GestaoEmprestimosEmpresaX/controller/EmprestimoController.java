package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  // Indica que esta classe é um controlador REST
@RequestMapping("/api/emprestimos")  // Define o caminho base para os endpoints deste controlador

public class EmprestimoController {

    @Autowired // Injeta o serviço de empréstimos
    private EmprestimoService emprestimoService;

    // Manipula exceções do tipo IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Defina o status HTTP adequado
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return errorResponse;
    }

    // Endpoint para listar todos os empréstimos
    @GetMapping("/listar")
    public List<EmprestimoDTO> listarEmprestimos() {
        return emprestimoService.findAll();
    }

    // Endpoint para buscar um empréstimo pelo ID
    @GetMapping("/buscar/{id}")
    public EmprestimoDTO buscarEmprestimo(@PathVariable Long id) {
        return emprestimoService.findById(id);
    }

    // Endpoint para criar um novo empréstimo
    @PostMapping("/criar")
    public void criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        emprestimoService.create(emprestimoDTO);
    }

    // Endpoint para pagar um empréstimo pelo ID
    @PutMapping("/pagar/{id}")
    public void pagarEmprestimo(@PathVariable Long id) {
        emprestimoService.pagarEmprestimo(id);
    }

    // Endpoint para deletar um empréstimo pelo ID
    @DeleteMapping("/deletar/{id}")
    public void deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.delete(id);
    }
}

