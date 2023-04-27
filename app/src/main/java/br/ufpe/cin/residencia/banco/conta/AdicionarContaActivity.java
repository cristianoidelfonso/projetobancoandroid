package br.ufpe.cin.residencia.banco.conta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class AdicionarContaActivity extends AppCompatActivity {

    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText campoNome = findViewById(R.id.nome);
        EditText campoNumero = findViewById(R.id.numero);
        EditText campoCPF = findViewById(R.id.cpf);
        EditText campoSaldo = findViewById(R.id.saldo);

        btnAtualizar.setText("Inserir");
        btnRemover.setVisibility(View.GONE);

        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String numeroConta = campoNumero.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();

                    //TODO: Incluir validações aqui, antes de criar um objeto Conta (por exemplo, verificar que digitou um nome com pelo menos 5 caracteres, que o campo de saldo tem de fato um número, assim por diante). Se todas as validações passarem, aí sim cria a Conta conforme linha abaixo.

                    // Validação para que o número da conta possua exatamente 7 dígitos
                    if(numeroConta.length() != 7){
                        campoNumero.setError("O número da conta deve possuir exatamente 7 dígitos");
                        return;
                    }

                    // Validação para que o nome do cliente possua 5 ou mais caracteres
                    if(nomeCliente.length() < 5){
                        campoNome.setError("O nome deve ter pelo menos 5 caracteres");
                        return;
                    }

                    // Validação para que o cpf do cliente possua exatamente 11 dígitos
                    if(cpfCliente.length() != 11){
                        campoCPF.setError("O CPF deve ter 11 dígitos");
                        return;
                    }

                    // Validação para que uma conta não seja criada sem um saldo informado.
                    // A conta pode ser criada com saldo informado igual a zero (0).
                    if(saldoConta.length() == 0){
                        campoSaldo.setError("Saldo não pode ser vazio");
                        return;
                    }

                    Conta c = new Conta(numeroConta, Double.valueOf(saldoConta), nomeCliente, cpfCliente);

                    //TODO: chamar o método que vai salvar a conta no Banco de Dados
                    viewModel.inserir(c);
                    finish();
                }
        );

    }
}