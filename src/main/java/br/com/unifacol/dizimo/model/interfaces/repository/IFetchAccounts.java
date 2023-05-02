package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.*;

import java.sql.SQLException;

public interface IFetchAccounts {
    Member searchMember(String cpf, Integer password) throws SQLException;

    Member searchMemberByEmailAndPassword(String email,Integer password)throws SQLException;

    Church fetchChurch(String cnpj, Integer password) throws SQLException;

    Church searchChurchByEmailAndPassword(String email,Integer password)throws SQLException;

    Address searchAddress(Long id) throws SQLException;

    MemberAccount fetchMemberAccount(Integer numberAccount, Integer password) throws SQLException;

    MemberAccount fetchMemberAccountByNumber(Integer numberAccount)throws SQLException;

    ChurchAccount searchChurchAccount(Integer numberAccount, Integer password) throws SQLException;

    ChurchAccount searchChurchAccountByNumber(Integer numberAccount)throws SQLException;
}
