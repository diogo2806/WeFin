package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Padrão Facade: Este método simplifica a obtenção de todas as pessoas
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        return ResponseEntity.ok(pessoaService.findAll());
    }

    // Padrão Facade: Este método simplifica a obtenção de uma pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.findById(id));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de criar uma nova pessoa
    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO) {
        return ResponseEntity.ok(pessoaService.save(pessoaDTO));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de atualizar uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        pessoaDTO.setId(id);
        return ResponseEntity.ok(pessoaService.save(pessoaDTO));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de deletar uma pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
