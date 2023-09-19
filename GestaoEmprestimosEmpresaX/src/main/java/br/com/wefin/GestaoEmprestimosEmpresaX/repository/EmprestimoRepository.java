package br.com.wefin.GestaoEmprestimosEmpresaX.repository;

import br.com.wefin.GestaoEmprestimosEmpresaX.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    // Métodos adicionais de consulta podem ser definidos aqui, se necessário
}
