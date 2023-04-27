package br.ufpe.cin.residencia.banco.conta;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Ver anotações TODO no código
@Dao
public interface ContaDAO {

    /**
     * Método utilizado para persistir uma conta no banco de dados
     * @param c
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void adicionar(Conta c);

    //TODO incluir métodos para atualizar conta e remover conta
    /**
     * Método utilizado para atualziar uma conta no banco de dados
     * @param c
     */
    @Update
    void atualizar(Conta c);

    /**
     * Método utilizado para remover uma conta no banco de dados
     * @param c
     */
    @Delete
    void remover(Conta c);

    /**
     * Método utilizado para listar as contas cadastradas no banco de dados
     * @return contas
     */
    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();


    //TODO incluir métodos para buscar pelo (1) número da conta, (2) pelo nome e (3) pelo CPF do Cliente
    /**
     * Método utilizado para buscar uma conta no banco de dados com base no numero da conta
     * @param numeroConta
     */
    @Query("SELECT * FROM contas WHERE numero = :numeroConta")
    List<Conta> buscarPeloNumero(String numeroConta);

    /**
     * Método utilizado para listar as contas de um cliente com base no nome do cliente
     * @param nomeCliente
     */
    @Query("SELECT * FROM contas WHERE nomeCliente = :nomeCliente")
    List<Conta> buscarPeloNome(String nomeCliente);

    /**
     * Método utilizado para listar as contas de um cliente com base no cpf do cliente
     * @param cpfCliente
     */
    @Query("SELECT * FROM contas WHERE cpfCliente = :cpfCliente")
    List<Conta> buscarPeloCPF(String cpfCliente);

}
