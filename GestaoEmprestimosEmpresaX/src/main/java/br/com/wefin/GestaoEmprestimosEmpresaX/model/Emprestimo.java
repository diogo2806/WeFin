package br.com.wefin.GestaoEmprestimosEmpresaX.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data // Gera getters e setters automaticamente
@AllArgsConstructor // Gera um construtor com todos os campos
@NoArgsConstructor // Gera um construtor vazio
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private Integer numeroParcelas;
    private String statusPagamento;
    private Date dataCriacao;
    private Integer parcelas;

    @ManyToOne
    private Pessoa pessoa;

    // A lógica de negócios relacionada ao empréstimo será movida para uma classe de serviço
    // para seguir o princípio de Single Responsibility.
}
