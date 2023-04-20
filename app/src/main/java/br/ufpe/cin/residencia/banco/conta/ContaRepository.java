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

    public LiveData<List<Conta>> getContas() {
        return contas;
    }

    @WorkerThread
    public void inserir(Conta c) {
        dao.adicionar(c);
    }

    @WorkerThread
    public void atualizar(Conta c) {
        //TODO implementar atualizar
        dao.atualizar(c);
    }

    @WorkerThread
    public void remover(Conta c) {
        //TODO implementar remover
        dao.remover(c);
    }

    @WorkerThread
    public List<Conta> buscarPeloNome(String nomeCliente) {
        //TODO implementar busca
        return dao.buscarPeloNome(nomeCliente);
        //return null;
    }

    @WorkerThread
    public List<Conta> buscarPeloCPF(String cpfCliente) {
        //TODO implementar busca
        return dao.buscarPeloCPF(cpfCliente);
        //return null;
    }

    @WorkerThread
    public Conta buscarPeloNumero(String numeroConta) {
        //TODO implementar busca
        return dao.buscarPeloNumero(numeroConta);
        // return null;
    }
}
