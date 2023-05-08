package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.ContaMembro;
import java.sql.SQLException;

public interface IContaMembroService {
    void cadastrar(ContaMembro contaMembro) throws SQLException;
    void atualizar(Integer numeroAtualDaConta, Integer senhaAtual, ContaMembro contaMembro) throws SQLException;
    void excluir(Integer numeroDaConta, Integer senha)throws SQLException;
    void listContaMembro() throws SQLException;
    void listMembrosPorNumeroESenha(Integer numberAccount, Integer password) throws SQLException;
}
