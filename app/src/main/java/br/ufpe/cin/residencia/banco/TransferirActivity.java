package br.ufpe.cin.residencia.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Ver anotações TODO no código
public class TransferirActivity extends AppCompatActivity {

    BancoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);

        TextView tipoOperacao = findViewById(R.id.tipoOperacao);
        EditText numeroContaOrigem = findViewById(R.id.numeroContaOrigem);
        TextView labelContaDestino = findViewById(R.id.labelContaDestino);
        EditText numeroContaDestino = findViewById(R.id.numeroContaDestino);
        EditText valorOperacao = findViewById(R.id.valor);
        Button btnOperacao = findViewById(R.id.btnOperacao);

        valorOperacao.setHint(valorOperacao.getHint() + " transferido");
        tipoOperacao.setText("TRANSFERIR");
        btnOperacao.setText("Transferir");

        btnOperacao.setOnClickListener(
                v -> {
                    String numOrigem = numeroContaOrigem.getText().toString();
                    String numDestino = numeroContaDestino.getText().toString();
                    //TODO lembrar de implementar validação dos números das contas e do valor da operação, antes de efetuar a operação de transferência.

                    // Validação para que o numero da conta de origem não seja vazio
                    if (numOrigem.isEmpty()) {
                        numeroContaOrigem.setError("Número da conta de origem não pode ser vazio.");
                        return;
                    }

                    // Validação para que o valor da operação não seja menor ou igual a zero
                    if (Double.parseDouble(valorOperacao.getText().toString()) <= 0) {
                        valorOperacao.setError("Valor da operação não pode ser menor ou igual a zero");
                        return;
                    }

                    // Validação para que o valor da operação não seja vazio
                    if (valorOperacao.getText().toString().isEmpty()) {
                        valorOperacao.setError("Valor da operação não pode ser vazio");
                        return;
                    }

                    // Validação para que o numero da conta de destino não seja igual ao numero da conta de origem
                    if (numOrigem.equals(numDestino)) {
                        numeroContaDestino.setError("Número da conta destino não pode ser igual ao número da conta origem");
                        return;
                    }

                    // Validação para que o numero da conta de destino não seja vazio
                    if (numDestino.isEmpty()) {
                        numeroContaDestino.setError("Número da conta de destino não pode ser vazio.");
                        return;
                    }

                    //TODO O método abaixo está sendo chamado, mas precisa ser implementado na classe BancoViewModel para funcionar.

                    double valor = Double.valueOf(valorOperacao.getText().toString());

                    viewModel.transferir(numOrigem, numDestino, valor);
                    finish();
                }
        );

    }
}