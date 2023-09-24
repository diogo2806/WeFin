package br.com.wefin.GestaoEmprestimosEmpresaX.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data  // Gera getters, setters, equals, hashCode e toString com Lombok
@AllArgsConstructor  // Gera um construtor com todos os campos como argumentos
@NoArgsConstructor  // Gera um construtor sem argumentos
public class EmprestimoDTO {
    private Long id;  // ID único do empréstimo
    private BigDecimal valorEmprestimo;  // Valor total do empréstimo
    private Integer numeroParcelas;  // Número de parcelas para o pagamento
    private String statusPagamento;  // Status atual do pagamento (por exemplo, "Pago")
    private Date dataCriacao;  // Data em que o empréstimo foi criado
    private Long pessoaId;  // ID da Pessoa associada ao empréstimo, em vez do objeto Pessoa para simplificar
}
