package br.com.wefin.GestaoEmprestimosEmpresaX.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private String identificador;
    private Date dataNascimento;
    private String tipoIdentificador;
    private Double valorMinimoParcela;
    private Double valorMaximoEmprestimo;
}
