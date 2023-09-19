package br.com.wefin.GestaoEmprestimosEmpresaX.repository;

import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Métodos adicionais de consulta podem ser definidos aqui, se necessário
}
