package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.controller.PessoaController;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.factory.IdentificadorFactory;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service   
public class PessoaService {


    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para converter Pessoa em PessoaDTO
    public PessoaDTO toDTO(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getIdentificador(),
                pessoa.getDataNascimento(),
                pessoa.getTipoIdentificador(),
                pessoa.getValorMinMensal(),
                pessoa.getValorMaxEmprestimo()
        );
    }

    // Método para converter PessoaDTO em Pessoa
    public Pessoa toEntity(PessoaDTO dto) {
        return new Pessoa(
                dto.getId(),
                dto.getNome(),
                dto.getIdentificador(),
                dto.getDataNascimento(),
                dto.getTipoIdentificador(),
                dto.getValorMinMensal(), // Já é BigDecimal
                dto.getValorMaxEmprestimo() // Já é BigDecimal
        );
    }

    // Método para buscar todas as pessoas
    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Método para buscar uma pessoa por ID
    public PessoaDTO findById(Long id) {

        logger.warn("Iniciando a findById.");
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        return toDTO(pessoa);
    }

    // Método para salvar uma pessoa
    public PessoaDTO save(PessoaDTO dto) {
        Pessoa pessoa = toEntity(dto);

        // Utiliza o padrão Strategy para validar o identificador
        IdentificadorStrategy strategy = IdentificadorFactory.createIdentificadorStrategy(pessoa.getIdentificador());
        if (!strategy.validar(pessoa.getIdentificador())) {
            throw new IllegalArgumentException("Identificador inválido");
        }

        // Define o TipoIdentificador e os valores de empréstimo com base na estratégia
        pessoa.setTipoIdentificador(strategy.getTipo());
        pessoa.setValorMinMensal(BigDecimal.valueOf(strategy.getValorMinimoParcela())); // Convertendo para BigDecimal
        pessoa.setValorMaxEmprestimo(BigDecimal.valueOf(strategy.getValorMaximoEmprestimo())); // Convertendo para
                                                                                               // BigDecimal

        pessoa = pessoaRepository.save(pessoa);
        return toDTO(pessoa);
    }

    // Método para deletar uma pessoa por ID
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }
}
