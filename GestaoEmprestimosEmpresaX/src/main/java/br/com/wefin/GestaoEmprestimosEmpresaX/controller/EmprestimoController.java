package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Defina o status HTTP adequado
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return errorResponse;
    }

    @GetMapping("/listar")
    public List<EmprestimoDTO> listarEmprestimos() {
        return emprestimoService.findAll();
    }

    @GetMapping("/buscar/{id}")
    public EmprestimoDTO buscarEmprestimo(@PathVariable Long id) {
        return emprestimoService.findById(id);
    }

    @PostMapping("/criar")
    public void criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        emprestimoService.create(emprestimoDTO);
    }

    @PutMapping("/pagar/{id}")
    public void pagarEmprestimo(@PathVariable Long id) {
        emprestimoService.pagarEmprestimo(id);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.delete(id);
    }
}

