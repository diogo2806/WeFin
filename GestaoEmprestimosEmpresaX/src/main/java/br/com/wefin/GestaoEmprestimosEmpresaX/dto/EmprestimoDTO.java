package br.com.wefin.GestaoEmprestimosEmpresaX.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {
    private Long id;
    private BigDecimal valorEmprestimo;
    private Integer numeroParcelas;
    private String statusPagamento;
    private Date dataCriacao;
    private Long pessoaId; // ID da Pessoa em vez do objeto Pessoa para simplificar
}
