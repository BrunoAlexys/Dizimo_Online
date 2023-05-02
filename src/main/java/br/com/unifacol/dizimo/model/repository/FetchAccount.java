package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.*;
import br.com.unifacol.dizimo.model.interfaces.repository.IFetchAccounts;
import java.sql.SQLException;

public class FetchAccount implements IFetchAccounts {

    @Override
    public Member searchMember(String cpf, Integer password) throws SQLException {
        return null;
    }

    @Override
    public Member searchMemberByEmailAndPassword(String email, Integer password) throws SQLException {
        return null;
    }

    @Override
    public Church fetchChurch(String cnpj, Integer password) throws SQLException {
        return null;
    }

    @Override
    public Church searchChurchByEmailAndPassword(String email, Integer password) throws SQLException {
        return null;
    }

    @Override
    public Address searchAddress(Long id) throws SQLException {
        return null;
    }

    @Override
    public MemberAccount fetchMemberAccount(Integer numberAccount, Integer password) throws SQLException {
        return null;
    }

    @Override
    public MemberAccount fetchMemberAccountByNumber(Integer numberAccount) throws SQLException {
        return null;
    }

    @Override
    public ChurchAccount searchChurchAccount(Integer numberAccount, Integer password) throws SQLException {
        return null;
    }

    @Override
    public ChurchAccount searchChurchAccountByNumber(Integer numberAccount) throws SQLException {
        return null;
    }
}
