package br.com.wefin.GestaoEmprestimosEmpresaX.factory;

import br.com.wefin.GestaoEmprestimosEmpresaX.validation.EUValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.CPFValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.CNPJValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.APValidator;
import br.com.wefin.GestaoEmprestimosEmpresaX.validation.IdentificadorStrategy;

public class IdentificadorFactory {

    public static IdentificadorStrategy createIdentificadorStrategy(String identificador) {
        switch (identificador.length()) {
            case 11:
                return new CPFValidator(); // Implementa IdentificadorStrategy
            case 14:
                return new CNPJValidator(); // Implementa IdentificadorStrategy
            case 8:
                return new EUValidator(); // Implementa IdentificadorStrategy
            case 10:
                return new APValidator(); // Implementa IdentificadorStrategy
            default:
                throw new IllegalArgumentException("Tipo de identificador desconhecido");
        }
    }
}
