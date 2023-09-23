package br.com.wefin.GestaoEmprestimosEmpresaX.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal valorEmprestimo;

    @Column(nullable = false)
    private int numeroParcelas;

    @Column(nullable = false, length = 50)
    private String statusPagamento;

    @Column(nullable = false)
    private Date dataCriacao;

    @ManyToOne // Defina o tipo de relacionamento como ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false) // Especifique a coluna de junção
    private Pessoa pessoa; // Use a entidade Pessoa como tipo
}
