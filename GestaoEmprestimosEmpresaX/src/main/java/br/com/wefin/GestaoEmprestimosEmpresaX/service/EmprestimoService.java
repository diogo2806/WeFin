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
        return convertToDTO(emprestimoRepository.findById(id).orElse(null));
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
        // Implemente a lógica de conversão aqui
        return new EmprestimoDTO();
    }

    private Emprestimo convertToEntity(EmprestimoDTO emprestimoDTO) {
        // Implemente a lógica de conversão aqui
        return new Emprestimo();
    }
}
