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
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String identificador;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false, length = 50)
    private String tipoIdentificador;

    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal valorMinMensal;

    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal valorMaxEmprestimo;

 
    

}
