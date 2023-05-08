package br.com.unifacol.dizimo.view;

import java.sql.SQLException;

public class DizimoOnline {
    private static MenuMembro menuMembro = new MenuMembro();
    private static MenuIgreja menuIgreja = new MenuIgreja();
    private static MenuContaMembro accountMember = new MenuContaMembro();
    private static MenuContaIgreja accountChurch = new MenuContaIgreja();

    public static void main(String[] args) throws SQLException {
      //  menuMember.signUpMember();
        //accountMember.registerMemberAccount();
        //menuChurch.singUpChurch();
        //accountChurch.registerChurchAccount();
        //accountMember.deposit();
       // accountMember.withdraw();
        accountMember.transferir();
        //accountChurch.deposit();
        //accountChurch.withdraw();
        //accountChurch.transfer();
    }
}
