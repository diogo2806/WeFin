package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

import br.com.wefin.GestaoEmprestimosEmpresaX.validation.ValidaCNPJ;

public class CNPJValidator implements IdentificadorStrategy {

    @Override
    public boolean validar(String identificador) {
        // Implementar lógica para validar CPF
        ValidaCNPJ validaCNPJ = new ValidaCNPJ();
        boolean validar = validaCNPJ.isCNPJ(identificador);
        return validar;
    }

    @Override
    public String getTipo() {
        return "PJ"; // Pessoa Jurídica
    }

    @Override
    public Double getValorMinimoParcela() {
        return 500.0;
    }

    @Override
    public Double getValorMaximoEmprestimo() {
        return 50000.0;
    }
}
