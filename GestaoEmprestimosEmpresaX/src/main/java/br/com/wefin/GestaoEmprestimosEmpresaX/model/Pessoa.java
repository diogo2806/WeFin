package br.com.wefin.GestaoEmprestimosEmpresaX.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * Classe Pessoa representa uma entidade de pessoa no sistema de empréstimos da Empresa X.
 * 
 * @author Diogo Santos
 */
@Data // Gera getters e setters automaticamente
@AllArgsConstructor // Gera um construtor com todos os campos
@NoArgsConstructor // Gera um construtor vazio
@Entity // Indica que esta classe é uma entidade JPA
public class Pessoa {

    /**
     * ID único da pessoa. Este campo é gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo da pessoa.
     */
    private String nome;

    /**
     * Identificador único da pessoa, como CPF ou CNPJ.
     */
    private String identificador;

    /**
     * Data de nascimento ou criação da pessoa.
     */
    private Date dataNascimento;

    /**
     * Tipo do identificador. Pode ser PF para Pessoa Física, PJ para Pessoa Jurídica, etc.
     */
    private String tipoIdentificador;

    /**
     * Valor mínimo que pode ser parcelado em um empréstimo para esta pessoa.
     */
    private Double valorMinimoParcela;

    /**
     * Valor máximo que pode ser emprestado para esta pessoa.
     */
    private Double valorMaximoEmprestimo;

    // A lógica para definir valores mínimos e máximos será movida para uma classe de serviço
    // para seguir o princípio de Single Responsibility.
}
