package br.com.unifacol.dizimo.model.service;

import java.math.BigDecimal;

public class CalculadoraDeDoacoes {
    public static BigDecimal calcularDizimo(BigDecimal salario){
        return salario.multiply(new BigDecimal(0.1));
    }

    public static BigDecimal calcularOferta(BigDecimal salario,BigDecimal porcentagem){
        BigDecimal porc = porcentagem.divide(new BigDecimal(100));
        return salario.multiply(porc);
    }
}
