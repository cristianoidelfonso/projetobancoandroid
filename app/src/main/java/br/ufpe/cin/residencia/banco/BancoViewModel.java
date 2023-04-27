package br.ufpe.cin.residencia.banco;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.residencia.banco.conta.Conta;
import br.ufpe.cin.residencia.banco.conta.ContaRepository;

//Ver anotações TODO no código
public class BancoViewModel extends AndroidViewModel {
    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    private MutableLiveData<List<Conta>> _listaContasAtual = new MutableLiveData<>();
    public LiveData<List<Conta>> listaContasAtual = _listaContasAtual;

    public BancoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = this.repository.getContas();
    }

    /**
     * Método utilizado para efetuar o somatório dos valores das contas do banco
     * @return double saldoTotal
     */
    public double saldoTotalBanco() {
        double saldoTotal = 0;
        for (Conta conta : this.contas.getValue()) {
            saldoTotal += conta.saldo;
        }
        return saldoTotal;
    }


    /**
     * Método utilizado para transferir valores entre contas
     * @param numeroContaOrigem
     * @param numeroContaDestino
     * @param valor
     */
    void transferir(String numeroContaOrigem, String numeroContaDestino, double valor) {
        //TODO implementar transferência entre contas (lembrar de salvar no BD os objetos Conta modificados)
        new Thread( () -> {
            List<Conta> contas = this.repository.buscarPeloNumero(numeroContaOrigem);
            Conta contaOrigem = contas.get(0);
            contaOrigem.debitar(valor);
            this.repository.atualizar(contaOrigem);

            contas = this.repository.buscarPeloNumero(numeroContaDestino);
            Conta contaDestino = contas.get(0);
            contaDestino.creditar(valor);
            this.repository.atualizar(contaDestino);
        } ).start();
    }

    /**
     * Método utilizado para creditar valor em uma conta especificada pelo numero
     * @param numeroConta
     * @param valor
     */
    void creditar(String numeroConta, double valor) {
        //TODO implementar creditar em conta (lembrar de salvar no BD o objeto Conta modificado)
        new Thread( () -> {
            List<Conta> contas = this.repository.buscarPeloNumero(numeroConta);
            Conta conta = contas.get(0);
            conta.creditar(valor);
            this.repository.atualizar(conta);
        } ).start();
    }

    /**
     * Método utilizado para debitar valor em uma conta especificada pelo numero
     * @param numeroConta
     * @param valor
     */
    void debitar(String numeroConta, double valor) {
        //TODO implementar debitar em conta (lembrar de salvar no BD o objeto Conta modificado)
        new Thread( () -> {
            List<Conta> contas = this.repository.buscarPeloNumero(numeroConta);
            Conta conta = contas.get(0);
            conta.debitar(valor);
            this.repository.atualizar(conta);
        } ).start();
    }

    /**
     * Método utilizado para listar contas de um cliente com base no nome informado
     * @param nomeCliente
     */
    void buscarPeloNome(String nomeCliente) {
        //TODO implementar busca pelo nome do Cliente
        new Thread( () -> {
            List<Conta> listaContas = this.repository.buscarPeloNome(nomeCliente);
            _listaContasAtual.postValue(listaContas);
        } ).start();
    }

    /**
     * Método utilizado para listar contas de um cliente com base no cpf informado
     * @param cpfCliente
     */
    void buscarPeloCPF(String cpfCliente) {
        //TODO implementar busca pelo CPF do Cliente
        new Thread( () -> {
            List<Conta> listaContas = this.repository.buscarPeloCPF(cpfCliente);
            _listaContasAtual.postValue(listaContas);
        } ).start();
    }

    /**
     * Método utilizado para buscar uma conta com base no numero da conta informado
     * @param numeroConta
     */
    void buscarPeloNumero(String numeroConta) {
        new Thread( () -> {
            List<Conta> listaContas = this.repository.buscarPeloNumero(numeroConta);
            _listaContasAtual.postValue(listaContas);
        } ).start();
    }

}
