package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Membro;

import java.sql.SQLException;
import java.util.List;

public interface IMembroRepository {
    void cadastrar(Membro membro) throws SQLException;
    void atualizar(Membro membro) throws SQLException;
    void excluir(String cpf, Integer senha)throws SQLException;
    List<Membro> listMembros() throws SQLException;
    List<Membro> listmembrosPorCpfESenha(String cpf, Integer senha) throws SQLException;
}
