package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.factory.IdentificadorFactory;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para buscar todas as pessoas
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    // Método para buscar uma pessoa por ID
    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    // Método para salvar uma pessoa
    public Pessoa save(Pessoa pessoa) {
        // Utiliza o padrão Strategy para validar o identificador
        IdentificadorStrategy strategy = IdentificadorFactory.createIdentificadorStrategy(pessoa.getIdentificador());
        if (!strategy.validar(pessoa.getIdentificador())) {
            throw new IllegalArgumentException("Identificador inválido");
        }

        // Define o TipoIdentificador e os valores de empréstimo com base na estratégia
        pessoa.setTipoIdentificador(strategy.getTipo());
        pessoa.setValorMinimoParcela(strategy.getValorMinimoParcela());
        pessoa.setValorMaximoEmprestimo(strategy.getValorMaximoEmprestimo());

        return pessoaRepository.save(pessoa);
    }

    // Método para deletar uma pessoa por ID
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }
}