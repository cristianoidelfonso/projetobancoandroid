package br.ufpe.cin.residencia.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import br.ufpe.cin.residencia.banco.conta.Conta;
import br.ufpe.cin.residencia.banco.conta.ContaAdapter;

//Ver anotações TODO no código
public class PesquisarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    ContaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);

        RecyclerView rvResultado = findViewById(R.id.rvResultado);

        adapter = new ContaAdapter(getLayoutInflater());

        rvResultado.setLayoutManager(new LinearLayoutManager(this));
        rvResultado.setAdapter(adapter);

        EditText aPesquisar = findViewById(R.id.pesquisa);
        Button btnPesquisar = findViewById(R.id.btn_Pesquisar);
        RadioGroup tipoPesquisa = findViewById(R.id.tipoPesquisa);

        btnPesquisar.setOnClickListener(
                v -> {
                    String oQueFoiDigitado = aPesquisar.getText().toString();

                    //TODO implementar a busca de acordo com o tipo de busca escolhido pelo usuário

                    if(!oQueFoiDigitado.isEmpty()){
                        switch (tipoPesquisa.getCheckedRadioButtonId()) {
                            //TODO implementar a busca por nome
                            case R.id.peloNomeCliente:
                                viewModel.buscarPeloNome(oQueFoiDigitado.trim());
                                aPesquisar.setText("");
                                break;

                            //TODO implementar a busca por CPF
                            case R.id.peloCPFcliente:
                                viewModel.buscarPeloCPF(oQueFoiDigitado.trim());
                                aPesquisar.setText("");
                                break;

                            //TODO implementar a busca por número
                            case R.id.peloNumeroConta:
                                viewModel.buscarPeloNumero(oQueFoiDigitado.trim());
                                aPesquisar.setText("");
                                break;
                        }
                    } else {
                        String info = (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloNomeCliente) ? "Nome do cliente"
                                    : (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloCPFcliente) ? "CPF do cliente"
                                    : (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloNumeroConta) ? "Número da conta" : "";
                        Toast.makeText(this, "Por favor, informe o " + info + ".", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        //TODO atualizar o RecyclerView com resultados da busca na medida que encontrar
        viewModel.listaContasAtual.observe(this, contas -> {
            if (contas.isEmpty()) {
                Toast.makeText(this, "Nenhum resultado foi encontrado.", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter.submitList(contas);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}