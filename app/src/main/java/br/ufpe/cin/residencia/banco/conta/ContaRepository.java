package br.ufpe.cin.residencia.banco.conta;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import java.util.List;

//Ver anotações TODO no código
public class ContaRepository {
    private ContaDAO dao;
    private LiveData<List<Conta>> contas;

    public ContaRepository(ContaDAO dao) {
        this.dao = dao;
        this.contas = dao.contas();
    }

    /**
     * Método utilizado para listar as contas cadastradas no banco de dados
     * @return contas
     */
    public LiveData<List<Conta>> getContas() {
        return contas;
    }

    public List<Conta> ListarContas() {
        return dao.contas().getValue();
    }

    /**
     * Método utilizado para persistir uma conta no banco de dados
     * @param c
     */
    @WorkerThread
    public void inserir(Conta c) {
        dao.adicionar(c);
    }

    /**
     * Método utilizado para atualziar uma conta no banco de dados
     * @param c
     */
    @WorkerThread
    public void atualizar(Conta c) {
        //TODO implementar atualizar
        dao.atualizar(c);
    }

    /**
     * Método utilizado para remover uma conta no banco de dados
     * @param c
     */
    @WorkerThread
    public void remover(Conta c) {
        //TODO implementar remover
        dao.remover(c);
    }

    /**
     * Método utilizado para listar as contas de um cliente com base no nome do cliente
     * @param nomeCliente
     */
    @WorkerThread
    public List<Conta> buscarPeloNome(String nomeCliente) {
        //TODO implementar busca
        return dao.buscarPeloNome(nomeCliente);
    }

    /**
     * Método utilizado para listar as contas de um cliente com base no cpf do cliente
     * @param cpfCliente
     */
    @WorkerThread
    public List<Conta> buscarPeloCPF(String cpfCliente) {
        //TODO implementar busca
        return dao.buscarPeloCPF(cpfCliente);
    }

    /**
     * Método utilizado para buscar uma conta no banco de dados com base no numero da conta
     * @param numeroConta
     */
    @WorkerThread
    public List<Conta> buscarPeloNumero(String numeroConta) {
        //TODO implementar busca
        return dao.buscarPeloNumero(numeroConta);
    }
}
