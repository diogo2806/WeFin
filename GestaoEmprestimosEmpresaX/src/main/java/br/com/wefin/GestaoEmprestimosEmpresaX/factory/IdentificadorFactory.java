package br.com.wefin.GestaoEmprestimosEmpresaX.factory;

import br.com.wefin.GestaoEmprestimosEmpresaX.validation.EUValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.CPFValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.CNPJValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.APValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;

public class IdentificadorFactory {

    // Método para criar uma estratégia de validação com base no tamanho do identificador
    public static IdentificadorStrategy createIdentificadorStrategy(String identificador) {
        IdentificadorStrategy strategy = null;

        // Escolhe a estratégia de validação com base no tamanho do identificador
        switch (identificador.length()) {
            case 11:
                strategy = new CPFValidator();  // CPF tem 11 dígitos
                break;
            case 14:
                strategy = new CNPJValidator(); // CNPJ tem 14 dígitos
                break;
            case 8:
                strategy = new EUValidator();   // Estudante Universitário tem 8 dígitos
                break;
            case 10:
                strategy = new APValidator();   // Aposentado tem 10 dígitos
                break;
        }

        // Retorna a estratégia escolhida ou lança uma exceção se desconhecida
        if (strategy != null) {
            return strategy;
        } else {
            throw new IllegalArgumentException("Tipo de identificador desconhecido "+identificador);
        }
    }

    // Método para criar uma estratégia de validação com base na sigla do identificador
    public static IdentificadorStrategy createIdentificadorStrategyBySigla(String sigla) {
        switch (sigla) {
            case "PF":
                return new CPFValidator();  // Pessoa Física
            case "PJ":
                return new CNPJValidator(); // Pessoa Jurídica
            case "EU":
                return new EUValidator();   // Estudante Universitário
            case "AP":
                return new APValidator();   // Aposentado
            default:
                throw new IllegalArgumentException("Sigla desconhecida "+sigla);
        }
    }
}
