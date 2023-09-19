package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Padrão Facade: Este método simplifica a obtenção de todas as pessoas
    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Padrão Facade: Este método simplifica a obtenção de uma pessoa por ID
    public PessoaDTO findById(Long id) {
        return convertToDTO(pessoaRepository.findById(id).orElse(null));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de salvar uma pessoa
    public PessoaDTO save(PessoaDTO pessoaDTO) {
        Pessoa pessoa = convertToEntity(pessoaDTO);
        return convertToDTO(pessoaRepository.save(pessoa));
    }

    // Princípio SOLID: Single Responsibility
    // Este método tem a única responsabilidade de deletar uma pessoa
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    private PessoaDTO convertToDTO(Pessoa pessoa) {
        // Implemente a lógica de conversão aqui
        return new PessoaDTO();
    }

    private Pessoa convertToEntity(PessoaDTO pessoaDTO) {
        // Implemente a lógica de conversão aqui
        return new Pessoa();
    }
}
