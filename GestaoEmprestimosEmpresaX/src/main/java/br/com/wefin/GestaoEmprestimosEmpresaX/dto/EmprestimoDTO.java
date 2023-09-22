package br.com.wefin.GestaoEmprestimosEmpresaX.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {
    private Long id;
    private Double valor;
    private Integer numeroParcelas;
    private String statusPagamento;
    private Date dataCriacao;
    private Long pessoaId; // Usamos o ID da Pessoa em vez do objeto Pessoa para simplificar
    private Integer parcelas;
}
