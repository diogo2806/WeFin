package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

public class CPFValidator implements IdentificadorStrategy {

    @Override
    public boolean validar(String identificador) {
        // Implementar lógica para validar CPF
        return true;
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
