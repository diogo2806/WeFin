package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/listar")
    public List<EmprestimoDTO> listarEmprestimos() {
        return emprestimoService.findAll();
    }

    @GetMapping("/{id}")
    public EmprestimoDTO getEmprestimo(@PathVariable Long id) {
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

