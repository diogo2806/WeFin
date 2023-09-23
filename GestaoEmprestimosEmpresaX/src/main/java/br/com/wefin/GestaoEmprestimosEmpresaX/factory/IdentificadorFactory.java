package br.com.wefin.GestaoEmprestimosEmpresaX.factory;

import br.com.wefin.GestaoEmprestimosEmpresaX.validation.EUValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.CPFValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.CNPJValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.APValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;

public class IdentificadorFactory {

    public static IdentificadorStrategy createIdentificadorStrategy(String identificador) {
        IdentificadorStrategy strategy = null;

        switch (identificador.length()) {
            case 11:
                strategy = new CPFValidator();
                break;
            case 14:
                strategy = new CNPJValidator();
                break;
            case 8:
                strategy = new EUValidator();
                break;
            case 10:
                strategy = new APValidator();
                break;
        }

        if (strategy != null) {
            return strategy;
        } else {
            throw new IllegalArgumentException("Tipo de identificador desconhecido "+identificador);
        }
    }

    public static IdentificadorStrategy createIdentificadorStrategyBySigla(String sigla) {
        switch (sigla) {
            case "PF":
                return new CPFValidator();
            case "PJ":
                return new CNPJValidator();
            case "EU":
                return new EUValidator();
            case "AP":
                return new APValidator();
            default:
                throw new IllegalArgumentException("Sigla desconhecida "+sigla);
        }
    }
}
