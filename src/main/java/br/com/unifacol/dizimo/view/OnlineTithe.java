package br.com.unifacol.dizimo.view;

import java.sql.SQLException;

public class OnlineTithe {
    private static MenuMember menuMember = new MenuMember();
    private static MenuChurch menuChurch = new MenuChurch();
    private static MenuAccountMember accountMember = new MenuAccountMember();
    private static MenuAccountChurch accountChurch = new MenuAccountChurch();

    public static void main(String[] args) throws SQLException {
      //  menuMember.signUpMember();
        //accountMember.registerMemberAccount();
        //menuChurch.singUpChurch();
        //accountChurch.registerChurchAccount();
        //accountMember.deposit();
       // accountMember.withdraw();
        accountMember.transfer();
        //accountChurch.deposit();
        //accountChurch.withdraw();
        //accountChurch.transfer();
    }
}
