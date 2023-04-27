package br.ufpe.cin.residencia.banco.conta;

import static br.ufpe.cin.residencia.banco.BancoDB.DB_NAME;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import br.ufpe.cin.residencia.banco.BancoDB;

//Ver métodos anotados com TODO
public class ContaViewModel extends AndroidViewModel {

    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;

    public ContaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = this.repository.getContas();
    }


    /**
     * Método utilizado para listar contas do banco de dados, através do repository
     */
    void listar() {
        new Thread( () -> {
            List<Conta> contas = this.repository.getContas().getValue();
            assert contas != null;
            _contaAtual.postValue(contas.get(0));
        } );
    }

    /**
     * Método utilizado para salvar uma conta no banco de dados, através do repository
     * @param c
     */
    void inserir(Conta c) {
        new Thread(() -> repository.inserir(c)).start();
    }

    /**
     * Método utilizado para atualizar uma conta no banco de dados, através do repository
     * @param c
     */
    void atualizar(Conta c) {
        //TODO implementar
        new Thread(() -> this.repository.atualizar(c)).start();
    }

    /**
     * Método utilizado para remover uma conta no banco de dados, através do repository
     * @param c
     */
    void remover(Conta c) {
        //TODO implementar
        new Thread(() -> this.repository.remover(c)).start();
    }

    /**
     * Método utilizado para buscar uma conta no banco de dados, através do repository
     * @param numeroConta
     */
    void buscarPeloNumero(String numeroConta) {
        new Thread( () -> {
            List<Conta> contas = this.repository.buscarPeloNumero(numeroConta);
            _contaAtual.postValue(contas.get(0));
        } ).start();
    }

    /**
     * Método utilizado para listar as contas de um cliente através do repository, com base no nome informado
     * @param nomeCliente
     */
    void buscarPeloNome(String nomeCliente) {
        new Thread( () -> {
            List<Conta> contas = this.repository.buscarPeloNome(nomeCliente);
            _contaAtual.postValue(contas.get(0));
        } ).start();
    }

    /**
     * Método utilizado para listar as contas de um cliente através do repository, com base no cpf informado,
     * @param cpfCliente
     */
    void buscarPeloCPF(String cpfCliente) {
        new Thread( () -> {
            List<Conta> contas = this.repository.buscarPeloCPF(cpfCliente);
            _contaAtual.postValue(contas.get(0));
        } ).start();
    }


}
