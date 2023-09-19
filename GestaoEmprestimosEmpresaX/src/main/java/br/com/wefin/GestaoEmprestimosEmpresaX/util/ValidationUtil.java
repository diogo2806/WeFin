package br.com.wefin.GestaoEmprestimosEmpresaX.util;

public class ValidationUtil {

    /**
     * Valida um CPF brasileiro.
     *
     * @param cpf O CPF a ser validado.
     * @return true se o CPF for válido, false caso contrário.
     */
    public static boolean isValidCPF(String cpf) {
        // Verifica se o CPF é nulo, tem tamanho diferente de 11 ou contém apenas dígitos idênticos
        if (cpf == null || cpf.length() != 11 || cpf.chars().allMatch(Character::isDigit)) {
            return false;
        }

        // Converte a string para um array de inteiros
        int[] numbers = cpf.chars().map(Character::getNumericValue).toArray();

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += numbers[i] * (10 - i);
        }
        int firstDigit = (sum * 10) % 11;
        firstDigit = (firstDigit == 10) ? 0 : firstDigit;

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += numbers[i] * (11 - i);
        }
        sum += firstDigit * 2;
        int secondDigit = (sum * 10) % 11;
        secondDigit = (secondDigit == 10) ? 0 : secondDigit;

        // Verifica se os dígitos verificadores são válidos
        return firstDigit == numbers[9] && secondDigit == numbers[10];
    }

    /**
     * Valida um CNPJ brasileiro.
     *
     * @param cnpj O CNPJ a ser validado.
     * @return true se o CNPJ for válido, false caso contrário.
     */
    public static boolean isValidCNPJ(String cnpj) {
        // Verifica se o CNPJ é nulo, tem tamanho diferente de 14 ou contém apenas dígitos idênticos
        if (cnpj == null || cnpj.length() != 14 || cnpj.chars().allMatch(Character::isDigit)) {
            return false;
        }

        // Converte a string para um array de inteiros
        int[] numbers = cnpj.chars().map(Character::getNumericValue).toArray();

        // Pesos para o cálculo dos dígitos verificadores
        int[] weight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += numbers[i] * weight1[i];
        }
        int firstDigit = (sum % 11 < 2) ? 0 : 11 - (sum % 11);

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (i < 12 ? numbers[i] : firstDigit) * weight2[i];
        }
        int secondDigit = (sum % 11 < 2) ? 0 : 11 - (sum % 11);

        // Verifica se os dígitos verificadores são válidos
        return firstDigit == numbers[12] && secondDigit == numbers[13];
    }
}
