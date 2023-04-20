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
        BancoDB db = Room.databaseBuilder(application, BancoDB.class, DB_NAME).build();
        ContaDAO dao = db.contaDAO();
        this.repository = new ContaRepository(dao);
        this.contas = this.repository.getContas();

        // this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        // this.contas = this.repository.getContas();
    }

    void inserir(Conta c) {
        //new Thread(() -> repository.inserir(c)).start();
        rodarEmBackground(
                () -> this.repository.inserir(c)
        );
    }

    void atualizar(Conta c) {
        //TODO implementar
        new Thread(() -> this.repository.atualizar(c)).start();
    }

    void remover(Conta c) {
        //TODO implementar
        new Thread(() -> this.repository.remover(c)).start();
    }

    void buscarPeloNumero(String numeroConta) {
        //TODO implementar
        new Thread( () -> {
            Conta c = this.repository.buscarPeloNumero(numeroConta);
        }).start();
    }

    List<Conta> buscarPeloNome(String nomeCliente) {
        return this.repository.buscarPeloNome(nomeCliente);
    }

    List<Conta> buscarPeloCpf(String cpfCliente) {
        return this.repository.buscarPeloCPF(cpfCliente);
    }

    private void rodarEmBackground(Runnable r) {
        new Thread(r).start();
    }
}
