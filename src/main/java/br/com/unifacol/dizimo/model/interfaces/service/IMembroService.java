package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.Membro;
import java.sql.SQLException;

public interface IMembroService {
    void cadastrar(Membro membro) throws SQLException;
    void atualizar(String cpf, Integer senha, Membro membro) throws SQLException;
    void excluir(String cpf, Integer senha)throws SQLException;
    void listMembros() throws SQLException;
    void listarMembroPorCPFESenha(String cpf, Integer password) throws SQLException;
}
