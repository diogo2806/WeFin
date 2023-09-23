package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

public interface IdentificadorStrategy {
    boolean validar(String identificador);
    String getTipo();
    Double getValorMinimoParcela();
    Double getValorMaximoEmprestimo();
}
