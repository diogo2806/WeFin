package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

public class EUValidator implements IdentificadorStrategy {

    @Override
    public boolean validar(String identificador) {
        if (identificador.length() != 8) return false;
        int firstDigit = Character.getNumericValue(identificador.charAt(0));
        int lastDigit = Character.getNumericValue(identificador.charAt(7));
        return firstDigit + lastDigit == 9;
    }

    @Override
    public String getTipo() {
        return "EU"; // Estudante Universitário
    }

    @Override
    public Double getValorMinimoParcela() {
        return 200.0;
    }

    @Override
    public Double getValorMaximoEmprestimo() {
        return 20000.0;
    }
}
