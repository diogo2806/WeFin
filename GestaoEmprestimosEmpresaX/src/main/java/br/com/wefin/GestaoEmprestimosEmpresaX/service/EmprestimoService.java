package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.model.Emprestimo;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.EmprestimoDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.EmprestimoRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
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

    public static EmprestimoDTO toDTO(Emprestimo emprestimo) {
        return new EmprestimoDTO(
            emprestimo.getId(),
            emprestimo.getValor(),
            emprestimo.getNumeroParcelas(),
            emprestimo.getStatusPagamento(),
            emprestimo.getDataCriacao(),
            emprestimo.getPessoa().getId(),
            emprestimo.getParcelas()  // Novo campo adicionado
        );
    }
    

    public static Emprestimo toEntity(EmprestimoDTO dto, Pessoa pessoa) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(dto.getId());
        emprestimo.setValor(dto.getValor());
        emprestimo.setNumeroParcelas(dto.getNumeroParcelas());
        emprestimo.setStatusPagamento(dto.getStatusPagamento());
        emprestimo.setDataCriacao(dto.getDataCriacao());
        emprestimo.setPessoa(pessoa);
        return emprestimo;
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

    // Método para criar um novo empréstimo
    public EmprestimoDTO create(EmprestimoDTO emprestimoDTO) {
        Pessoa pessoa = pessoaRepository.findById(emprestimoDTO.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        
        Emprestimo emprestimo = toEntity(emprestimoDTO, pessoa);

        // Validações de negócio
        if (emprestimoDTO.getValor() > pessoa.getValorMaximoEmprestimo() ||
            emprestimoDTO.getParcelas() < pessoa.getValorMinimoParcela() ||
            emprestimoDTO.getNumeroParcelas() > 24) {
            throw new IllegalArgumentException("Condições de empréstimo inválidas");
        }

        emprestimo = emprestimoRepository.save(emprestimo);
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

