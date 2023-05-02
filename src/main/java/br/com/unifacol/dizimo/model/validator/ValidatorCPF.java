package br.com.unifacol.dizimo.model.validator;

public class ValidatorCPF {
    public boolean verificarCpf(String cpf) {
        // Remover pontuação do CPF
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        // Verificar se o tamanho do CPF é válido (11 dígitos)
        if (cpf.length() != 11) {
            return false;
        }

        // Realizar cálculo de verificação dos dígitos
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(cpf.charAt(i) + "") * peso;
            peso--;
        }

        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(cpf.charAt(i) + "") * peso;
            peso--;
        }

        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        // Verificar se os dígitos de verificação são válidos
        return (digito1 == Integer.parseInt(cpf.charAt(9) + "") && digito2 == Integer.parseInt(cpf.charAt(10) + ""));
    }
}
