package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

import br.com.wefin.GestaoEmprestimosEmpresaX.factory.IdentificadorFactory;

public class APValidator implements IdentificadorStrategy {

    @Override
    public boolean validar(String identificador) {
        if (identificador.length() != 10) return false;
        char lastDigit = identificador.charAt(9);
        return !identificador.substring(0, 9).contains(String.valueOf(lastDigit));
    }

    @Override
    public String getTipo() {
        return "AP"; // Aposentado
    }

    @Override
    public Double getValorMinimoParcela() {
        return 50.0;
    }

    @Override
    public Double getValorMaximoEmprestimo() {
        return 5000.0;
    }
}
