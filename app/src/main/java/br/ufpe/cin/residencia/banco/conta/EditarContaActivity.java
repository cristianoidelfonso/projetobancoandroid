package br.ufpe.cin.residencia.banco.conta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class EditarContaActivity extends AppCompatActivity {

    public static final String KEY_NUMERO_CONTA = "numeroDaConta";
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
        campoNumero.setEnabled(false);

        Intent i = getIntent();
        String numeroConta = i.getStringExtra(KEY_NUMERO_CONTA);
        //TODO usar o número da conta passado via Intent para recuperar informações da conta

        btnAtualizar.setText("Editar");
        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();
                    //TODO: Incluir validações aqui, antes de criar um objeto Conta. Se todas as validações passarem, aí sim monta um objeto Conta.
                    //TODO: chamar o método que vai atualizar a conta no Banco de Dados
                }
        );

        btnRemover.setOnClickListener(v -> {
            //TODO implementar remoção da conta
        });
    }
}