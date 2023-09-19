package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // Padrão Facade: Este método simplifica a obtenção de todos os empréstimos
    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> findAll() {
        return ResponseEntity.ok(emprestimoService.findAll());
    }

    // Padrão Facade: Este método simplifica a obtenção de um empréstimo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.findById(id));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de criar um novo empréstimo
    @PostMapping
    public ResponseEntity<EmprestimoDTO> create(@RequestBody EmprestimoDTO emprestimoDTO) {
        return ResponseEntity.ok(emprestimoService.save(emprestimoDTO));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de atualizar um empréstimo
    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO) {
        emprestimoDTO.setId(id);
        return ResponseEntity.ok(emprestimoService.save(emprestimoDTO));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de deletar um empréstimo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
