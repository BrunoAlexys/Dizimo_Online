package br.com.unifacol.dizimo.model.service;

import java.math.BigDecimal;

public class CalcularDizimo {
    public BigDecimal calcular(BigDecimal salario){
        return salario.multiply(new BigDecimal(0.1));
    }
}
