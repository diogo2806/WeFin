package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.model.Emprestimo;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.EmprestimoRepository;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para buscar todos os empréstimos
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    // Método para buscar um empréstimo por ID
    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));
    }

    // Método para criar um novo empréstimo
    public Emprestimo create(Emprestimo emprestimo) {
        // Validações de negócio (identificador válido, limites de empréstimo, etc.)
        Pessoa pessoa = pessoaRepository.findById(emprestimo.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        if (emprestimo.getValor() > pessoa.getValorMaximoEmprestimo() ||
            emprestimo.getParcelas() < pessoa.getValorMinimoParcela() ||
            emprestimo.getNumeroParcelas() > 24) {
            throw new IllegalArgumentException("Condições de empréstimo inválidas");
        }

        return emprestimoRepository.save(emprestimo);
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
