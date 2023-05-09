package br.com.unifacol.dizimo.model.service;

import java.math.BigDecimal;

public class CalcularOferta {
    public BigDecimal calcular(BigDecimal salario,BigDecimal porcentagem){
        BigDecimal porc = porcentagem.divide(new BigDecimal(100));
        return salario.multiply(porc);
    }
}
