package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Emprestimo;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // Padrão Facade: Este método simplifica a obtenção de todos os empréstimos
    public List<EmprestimoDTO> findAll() {
        return emprestimoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Padrão Facade: Este método simplifica a obtenção de um empréstimo por ID
    public EmprestimoDTO findById(Long id) {
        return emprestimoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de salvar um empréstimo
    public EmprestimoDTO save(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = convertToEntity(emprestimoDTO);
        return convertToDTO(emprestimoRepository.save(emprestimo));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de deletar um empréstimo
    public void delete(Long id) {
        emprestimoRepository.deleteById(id);
    }

    private EmprestimoDTO convertToDTO(Emprestimo emprestimo) {
        // Lógica de conversão de Emprestimo para EmprestimoDTO
        if (emprestimo == null) return null;
        return new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getValor(),
                emprestimo.getNumeroParcelas(),
                emprestimo.getStatusPagamento(),
                emprestimo.getDataCriacao(),
                emprestimo.getPessoa().getId()  // Supondo que Emprestimo tem um campo Pessoa
        );
    }

    private Emprestimo convertToEntity(EmprestimoDTO emprestimoDTO) {
        // Lógica de conversão de EmprestimoDTO para Emprestimo
        if (emprestimoDTO == null) return null;
        Emprestimo emprestimo = new Emprestimo(
                emprestimoDTO.getId(),
                emprestimoDTO.getValor(),
                emprestimoDTO.getNumeroParcelas(),
                emprestimoDTO.getStatusPagamento(),
                emprestimoDTO.getDataCriacao()
        );
        emprestimo.setPessoa(new Pessoa(emprestimoDTO.getPessoaId()));  // Supondo que Emprestimo tem um campo Pessoa
        return emprestimo;
    }
}
