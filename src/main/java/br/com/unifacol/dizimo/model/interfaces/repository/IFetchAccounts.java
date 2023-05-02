package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.*;

import java.sql.SQLException;

public interface IFetchAccounts {
    Member searchMemberByCpfAndPassword(String cpf, Integer password) throws SQLException;
    Member searchMemberByEmailAndPassword(String email, Integer password) throws SQLException;
    Church fetchChurchByCnpjAndPassword(String cnpj, Integer password) throws SQLException;
    Church searchChurchByEmailAndPassword(String email, Integer password) throws SQLException;
    Address searchAddress(Long id) throws SQLException;
    AccountMember fetchMemberAccount(Integer numberAccount, Integer password) throws SQLException;
    AccountMember fetchMemberAccountByNumber(Integer accountNumber) throws SQLException;
    ChurchAccount searchChurchAccount(Integer accountNumber, Integer password) throws SQLException;
    ChurchAccount searchChurchAccountByNumber(Integer accountNumber) throws SQLException;
}
