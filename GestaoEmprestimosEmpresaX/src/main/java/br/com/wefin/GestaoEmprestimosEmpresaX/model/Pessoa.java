package br.com.wefin.GestaoEmprestimosEmpresaX.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data  // Gera getters, setters, equals, hashCode e toString com Lombok
@AllArgsConstructor  // Gera um construtor com todos os campos como argumentos
@NoArgsConstructor  // Gera um construtor sem argumentos
@Entity  // Indica que esta classe é uma entidade JPA
public class Pessoa {

    @Id  // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Gera automaticamente o valor do ID
    private Long id;  // ID único da pessoa

    @Column(nullable = false, length = 50)  // Configurações da coluna no banco de dados
    private String nome;  // Nome da pessoa

    @Column(nullable = false, length = 50)  // Configurações da coluna no banco de dados
    private String identificador;  // Identificador único da pessoa (por exemplo, CPF)

    @Column(nullable = false)  // Configurações da coluna no banco de dados
    private Date dataNascimento;  // Data de nascimento da pessoa

    @Column(nullable = false, length = 50)  // Configurações da coluna no banco de dados
    private String tipoIdentificador;  // Tipo do identificador (por exemplo, "CPF", "CNPJ")

    @Column(nullable = false, precision = 18, scale = 4)  // Configurações da coluna no banco de dados
    private BigDecimal valorMinMensal;  // Valor mínimo mensal que a pessoa pode pagar

    @Column(nullable = false, precision = 18, scale = 4)  // Configurações da coluna no banco de dados
    private BigDecimal valorMaxEmprestimo;  // Valor máximo que a pessoa pode tomar como empréstimo
}
