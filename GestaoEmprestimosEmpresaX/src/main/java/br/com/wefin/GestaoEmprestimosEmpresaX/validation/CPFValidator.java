package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

import br.com.wefin.GestaoEmprestimosEmpresaX.validation.ValidaCPF;

public class CPFValidator implements IdentificadorStrategy {

    @Override
    public boolean validar(String identificador) {
        // Implementar lógica para validar CPF
        ValidaCPF validaCPF = new ValidaCPF();
        boolean validar = validaCPF.isCPF(identificador);
        return validar;
    }

    @Override
    public String getTipo() {
        return "PF"; // Pessoa Física
    }

    @Override
    public Double getValorMinimoParcela() {
        return 100.0;
    }

    @Override
    public Double getValorMaximoEmprestimo() {
        return 10000.0;
    }
}
