package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

public class CNPJValidator implements IdentificadorStrategy {

    @Override
    public boolean validar(String identificador) {
        // Implementar lógica para validar CNPJ
        return true;
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
