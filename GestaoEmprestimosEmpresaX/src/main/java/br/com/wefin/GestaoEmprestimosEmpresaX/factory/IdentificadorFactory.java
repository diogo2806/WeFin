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
