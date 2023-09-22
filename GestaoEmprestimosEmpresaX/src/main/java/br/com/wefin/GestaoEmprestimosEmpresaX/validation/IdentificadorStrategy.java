package br.com.wefin.GestaoEmprestimosEmpresaX.validation;

// Define uma estratégia para validar diferentes tipos de identificadores
public interface IdentificadorStrategy {
    boolean validar(String identificador);
    String getTipo();
    Double getValorMinimoParcela();
    Double getValorMaximoEmprestimo();
}