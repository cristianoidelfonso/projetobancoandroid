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

        // viewModel.contas.observe(this, contas -> { adapter.submitList(contas); });

        btnPesquisar.setOnClickListener(
                v -> {
                    String oQueFoiDigitado = aPesquisar.getText().toString();

                    //TODO implementar a busca de acordo com o tipo de busca escolhido pelo usuário
                    if (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloNomeCliente) {
                        viewModel.buscarPeloNome(oQueFoiDigitado);
                        Log.i("PesquisarActivity", "Pesquisar pelo nome do cliente: " + oQueFoiDigitado + " | Contas: " + viewModel.contas );
                    } else if (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloCPFcliente) {
                        viewModel.buscarPeloCPF(oQueFoiDigitado);
                        Log.i("PesquisarActivity", "Pesquisar pelo cpf do cliente: " + oQueFoiDigitado );
                    } else if (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloNumeroConta){
                        viewModel.buscarPeloNumero(oQueFoiDigitado);
                        Log.i("PesquisarActivity", "Pesquisar pelo numero da conta: " + oQueFoiDigitado + " | ");
                    } else {
                        Log.e("PesquisarActivity", "Nenhum tipo de pesquisa foi selecionado.");
                    }

                    viewModel.contas.observe(this, contas -> { adapter.submitList(contas); });
                }
        );

        //TODO atualizar o RecyclerView com resultados da busca na medida que encontrar
        //
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}