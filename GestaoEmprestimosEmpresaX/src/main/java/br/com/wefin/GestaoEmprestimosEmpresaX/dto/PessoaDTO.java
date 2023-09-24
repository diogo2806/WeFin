package br.com.wefin.GestaoEmprestimosEmpresaX.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data  // Gera getters, setters, equals, hashCode e toString com Lombok
@AllArgsConstructor  // Gera um construtor com todos os campos como argumentos
@NoArgsConstructor  // Gera um construtor sem argumentos
public class PessoaDTO {
    private Long id;  // ID único da pessoa
    private String nome;  // Nome da pessoa
    private String identificador;  // Identificador único da pessoa (por exemplo, CPF)
    private Date dataNascimento;  // Data de nascimento da pessoa
    private String tipoIdentificador;  // Tipo do identificador (por exemplo, "PF", "PJ")
    private BigDecimal valorMinMensal;  // Valor mínimo mensal que a pessoa pode pagar
    private BigDecimal valorMaxEmprestimo;  // Valor máximo que a pessoa pode tomar como empréstimo
}