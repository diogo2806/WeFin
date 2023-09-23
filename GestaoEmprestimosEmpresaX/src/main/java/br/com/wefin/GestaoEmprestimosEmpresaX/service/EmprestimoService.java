package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.model.Emprestimo;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.factory.IdentificadorFactory;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.EmprestimoRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service  
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    public static EmprestimoDTO toDTO(Emprestimo emprestimo) {
        return new EmprestimoDTO(
            emprestimo.getId(),
            emprestimo.getValorEmprestimo(),  // Campo atualizado
            emprestimo.getNumeroParcelas(),
            emprestimo.getStatusPagamento(),
            emprestimo.getDataCriacao(),
            emprestimo.getPessoa().getId()
        );
    }

    public static Emprestimo toEntity(EmprestimoDTO dto, Pessoa pessoa) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(dto.getId());
        emprestimo.setValorEmprestimo(dto.getValorEmprestimo()); // Campo atualizado
        emprestimo.setNumeroParcelas(dto.getNumeroParcelas());
        emprestimo.setStatusPagamento(dto.getStatusPagamento());
        emprestimo.setDataCriacao(dto.getDataCriacao());
        emprestimo.setPessoa(pessoa);
        return emprestimo;
    }

     // Método para criar um novo empréstimo
    public EmprestimoDTO create(EmprestimoDTO emprestimoDTO) {
        PessoaDTO pessoaDTO = pessoaRepository.findById((long) emprestimoDTO.getPessoaId())
                .map(pessoaService::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        

        // Utiliza o padrão Strategy para validar o identificador
        IdentificadorStrategy strategy = IdentificadorFactory.createIdentificadorStrategyBySigla(pessoaDTO.getTipoIdentificador());

        if (emprestimoDTO.getNumeroParcelas() > 24) {
            throw new IllegalArgumentException("Condições de empréstimo inválidas");
        }


        // Verificar se o empréstimo ultrapassa o limite máximo
        double valorMaxEmprestimo = emprestimoDTO.getValorEmprestimo().doubleValue();
        if (valorMaxEmprestimo > strategy.getValorMaximoEmprestimo()) {
            throw new IllegalArgumentException(
                    "O valor do empréstimo ultrapassa o limite máximo para este tipo de identificador");
        }

        // Verificar se o valor das parcelas é maior ou igual ao valor mínimo permitido
        double valorMinParcela = emprestimoDTO.getValorEmprestimo().doubleValue();
        if (valorMinParcela < strategy.getValorMinimoParcela()) {
            throw new IllegalArgumentException(
                    "O valor das parcelas é inferior ao valor mínimo permitido para este tipo de identificador");
        }

        Emprestimo emprestimo = toEntity(emprestimoDTO, pessoaService.toEntity(pessoaDTO));
        emprestimo = emprestimoRepository.save(emprestimo);
        return toDTO(emprestimo);
    }

    // Método para buscar todos os empréstimos
    public List<EmprestimoDTO> findAll() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream().map(EmprestimoService::toDTO).collect(Collectors.toList());
    }

    // Método para buscar um empréstimo por ID
    public EmprestimoDTO findById(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));
        return toDTO(emprestimo);
    }

    // Método para executar o pagamento de um empréstimo
    public void pagarEmprestimo(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));
        emprestimo.setStatusPagamento("Pago");
        emprestimoRepository.save(emprestimo);
    }

    // Método para deletar um empréstimo por ID
    public void delete(Long id) {
        emprestimoRepository.deleteById(id);
    }
}

