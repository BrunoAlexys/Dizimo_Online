package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.service.CalcularDizimo;
import br.com.unifacol.dizimo.model.service.CalcularOferta;

import java.math.BigDecimal;
import java.sql.SQLException;

public class DizimoOnline {
    private static MenuMembro menuMembro = new MenuMembro();
    private static MenuIgreja menuIgreja = new MenuIgreja();
    private static MenuContaMembro accountMember = new MenuContaMembro();
    private static MenuContaIgreja accountChurch = new MenuContaIgreja();
    private static CalcularDizimo calcularDizimo = new CalcularDizimo();
    private static CalcularOferta calcularOferta = new CalcularOferta();

    public static void main(String[] args) throws SQLException {
         //menuMembro.cadastrarMembro();
            accountMember.cadastrarConta();
    }
}
