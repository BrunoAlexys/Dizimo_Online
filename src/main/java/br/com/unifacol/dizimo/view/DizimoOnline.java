package br.com.unifacol.dizimo.view;

import javax.swing.*;
import java.sql.SQLException;

public class DizimoOnline {
    private static MenuMembro membro = new MenuMembro();
    private static MenuIgreja igreja = new MenuIgreja();
    private static MenuContaMembro contaMembro = new MenuContaMembro();
    private static MenuContaIgreja contaIgreja = new MenuContaIgreja();

    public static void main(String[] args) throws SQLException {
        dizimoOnline();
    }

    private static void dizimoOnline() throws SQLException {
        Integer menu =
                Integer.parseInt(JOptionPane.showInputDialog(new StringBuilder().append("---- DIZÍMO ONLINE ----").append("\nOpção 1 - Membro |").append("\nOpção 2 - Igreja |").append("\nOpção 3 - Sair |").toString()));
        switch (menu) {
            case 1:
                menuMembro();
                dizimoOnline();
            case 2:
                menuIgreja();
                dizimoOnline();
            case 3:
                System.exit(0);
            default:
                System.out.println("Operação invalida");
        }
    }

    private static void menuMembro() throws SQLException {
        Integer menu =
                Integer.parseInt(JOptionPane.showInputDialog(new StringBuilder().append("\n---- DIZÍMO ONLINE ----").append("\nOpção 1 - Cadastrar Membro|").append("\nOpção 2 - Alterar Membro|").append("\nOpção 3 - Excluir Membro|").append("\nOpção 4 - Listar Membro|").append("\nOpção 5 - Abrir Conta |").append("\nOpção 7 - Sair |").toString()));

        switch (menu) {
            case 1:
                membro.cadastrarMembro();
                menuMembro();
            case 2:
                membro.atualizarMembro();
                menuMembro();
            case 3:
                membro.excluir();
                menuMembro();
            case 4:
                membro.listMembros();
                menuMembro();
            case 5:
                contaMembro();
            case 6:
                dizimoOnline();
        }
    }

    private static void contaMembro() throws SQLException {
        Integer menu =
                Integer.parseInt(JOptionPane.showInputDialog(new StringBuilder().append("\n--- ABRIR CONTA ---").append("\nOpção 1 - Cadastrar Conta Membro |").append("\nOpção 2 - Aleterar Conta Membro | ").append("\nOpção 3 - Excluir Conta Membro |").append("\nOpção 4 - Listar Conta Membro |").append("\nOpção 5 - Depositar |").append("\nOpção 6 - Sacar |").append("\nOpção 7 - Transferir |").append("\nOpção 8 - Calcular Dizímo |").append("\nOpção 9 - Calcular Oferta |").append("\nOpção 10 - Sair |").toString()));

        switch (menu) {
            case 1:
                contaMembro.cadastrarConta();
                contaMembro();
            case 2:
                contaMembro.atualizarConta();
                contaMembro();
            case 3:
                contaMembro.excluirConta();
                contaMembro();
            case 4:
                contaMembro.listarContaMembro();
                contaMembro();
            case 5:
                contaMembro.depositar();
                contaMembro();
            case 6:
                contaMembro.sacar();
                contaMembro();
            case 7:
                contaMembro.transferir();
                contaMembro();
            case 8:
                contaMembro.calcularDizimo();
                contaMembro();
            case 9:
                contaMembro.calcularOferta();
                contaMembro();
            case 10:
                menuMembro();
        }
    }

    private static void menuIgreja() throws SQLException {

        Integer menu =
                Integer.parseInt(JOptionPane.showInputDialog(new StringBuilder().append("\n---- DIZÍMO ONLINE ----").append("\nOpção 1 - Cadastrar Igreja |").append("\nOpção 2 - Alterar Igreja |").append("\nOpção 3 - Excluir Igreja |").append("\nOpção 4 - Listar Igreja |").append("\nOpção 5 - Abrir conta |").append("\nOpção 6 - Sair |").toString()));

        switch (menu) {
            case 1:
                igreja.cadastrarIgreja();
                menuIgreja();
            case 2:
                igreja.alterarIgreja();
                menuIgreja();
            case 3:
                igreja.excluirIgreja();
                menuIgreja();
            case 4:
                igreja.listarIgreja();
                menuIgreja();
            case 5:
                contaIgreja();
            case 6:
                dizimoOnline();
        }
    }

    private static void contaIgreja() throws SQLException {
        Integer menu =
                Integer.parseInt(JOptionPane.showInputDialog(new StringBuilder().append("--- ABRIR CONTA ---").append("\nOpção 1 - Cadastrar Conta Igreja |").append("\nOpção 2 - Aleterar Conta Igreja | ").append("\nOpção 3 - Excluir Conta Igreja |").append("\nOpção 4 - Listar Conta Igreja |").append("\nOpção 5 - Depositar |").append("\nOpção 6 - Sacar |").append("\nOpção 7 - Transferir |").append("\nOpção 8 - Sair |").toString()));

        switch (menu) {
            case 1:
                contaIgreja.criarConta();
                contaIgreja();
            case 2:
                contaIgreja.atualizarConta();
                contaIgreja();
            case 3:
                contaIgreja.excluirConta();
                contaIgreja();
            case 4:
                contaIgreja.listarConta();
                contaIgreja();
            case 5:
                contaIgreja.depositar();
                contaIgreja();
            case 6:
                contaIgreja.sacar();
                contaIgreja();
            case 7:
                contaIgreja.transferir();
                contaIgreja();
            case 8:
                menuIgreja();
        }
    }

}
