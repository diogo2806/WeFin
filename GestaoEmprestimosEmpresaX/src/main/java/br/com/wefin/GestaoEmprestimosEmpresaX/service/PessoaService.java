package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.factory.IdentificadorFactory;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para buscar todas as pessoas
    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para buscar uma pessoa por ID
    public PessoaDTO findById(Long id) {
        return pessoaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    // Método para salvar uma pessoa
    // Responsável também por definir o TipoIdentificador e os valores de empréstimo
    public PessoaDTO save(PessoaDTO pessoaDTO) {
        // Utiliza o padrão Strategy para validar o identificador
        IdentificadorStrategy strategy = IdentificadorFactory.createIdentificadorStrategy(pessoaDTO.getIdentificador());
        if (!strategy.validar(pessoaDTO.getIdentificador())) {
            throw new IllegalArgumentException("Identificador inválido");
        }

        // Define o TipoIdentificador e os valores de empréstimo com base na estratégia
        pessoaDTO.setTipoIdentificador(strategy.getTipo());
        pessoaDTO.setValorMinimoParcela(strategy.getValorMinimoParcela());
        pessoaDTO.setValorMaximoEmprestimo(strategy.getValorMaximoEmprestimo());

        Pessoa pessoa = convertToEntity(pessoaDTO);
        return convertToDTO(pessoaRepository.save(pessoa));
    }

    // Método para deletar uma pessoa por ID
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    // Conversão de Pessoa para PessoaDTO
    private PessoaDTO convertToDTO(Pessoa pessoa) {
        if (pessoa == null) return null;
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getIdentificador(),
                pessoa.getDataNascimento(),
                pessoa.getTipoIdentificador(),
                pessoa.getValorMinimoParcela(),
                pessoa.getValorMaximoEmprestimo()
        );
    }

    // Conversão de PessoaDTO para Pessoa
    private Pessoa convertToEntity(PessoaDTO pessoaDTO) {
        if (pessoaDTO == null) return null;
        return new Pessoa(
                pessoaDTO.getId(),
                pessoaDTO.getNome(),
                pessoaDTO.getIdentificador(),
                pessoaDTO.getDataNascimento(),
                pessoaDTO.getTipoIdentificador(),
                pessoaDTO.getValorMinimoParcela(),
                pessoaDTO.getValorMaximoEmprestimo()
        );
    }
}
