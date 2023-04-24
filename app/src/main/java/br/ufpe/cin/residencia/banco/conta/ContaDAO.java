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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void adicionar(Conta c);

    //TODO incluir métodos para atualizar conta e remover conta
    @Update
    void atualizar(Conta c);

    @Delete
    void remover(Conta c);

    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();

    //TODO incluir métodos para buscar pelo (1) número da conta, (2) pelo nome e (3) pelo CPF do Cliente

    @Query("SELECT * FROM contas WHERE numero = :numeroConta")
    List<Conta> buscarPeloNumero(String numeroConta);

    @Query("SELECT * FROM contas WHERE nomeCliente = :nomeCliente")
    List<Conta> buscarPeloNome(String nomeCliente);

    @Query("SELECT * FROM contas WHERE cpfCliente = :cpfCliente")
    List<Conta> buscarPeloCPF(String cpfCliente);

}
