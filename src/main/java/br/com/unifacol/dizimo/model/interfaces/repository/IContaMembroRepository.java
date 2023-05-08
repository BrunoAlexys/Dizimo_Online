package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.ContaMembro;

import java.sql.SQLException;
import java.util.List;

public interface IContaMembroRepository {
    void cadastrar(ContaMembro contaMembro) throws SQLException;
    void atualizar(Integer numeroDaContaAtual, Integer senhaAtual, ContaMembro contaMembro) throws SQLException;
    void excluir(Integer numeroDaConta, Integer senha)throws SQLException;
    List<ContaMembro> listMembros() throws SQLException;
    List<ContaMembro> listContaMembroPorNumeroESenha(Integer numeroDaconta, Integer senha) throws SQLException;
}
