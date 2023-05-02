package br.com.unifacol.dizimo.model.validator;

public class ValidatorCNPJ {
    public boolean verificarCnpj(String cnpj) {

        // Remove a pontuação do CNPJ
        cnpj = cnpj.replaceAll("[^0-9]+", "");

        // Verifica se o CNPJ possui 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos do CNPJ são iguais
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            int num = Integer.parseInt(String.valueOf(cnpj.charAt(i)));
            soma += num * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        int resto = soma % 11;
        int dv1 = (resto < 2) ? 0 : 11 - resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            int num = Integer.parseInt(String.valueOf(cnpj.charAt(i)));
            soma += num * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        resto = soma % 11;
        int dv2 = (resto < 2) ? 0 : 11 - resto;

        // Verifica se os dígitos verificadores calculados são iguais aos dígitos
        // verificadores informados
        String dv = String.valueOf(dv1) + String.valueOf(dv2);
        return cnpj.endsWith(dv);
    }
}
