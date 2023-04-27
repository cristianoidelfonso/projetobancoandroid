package br.ufpe.cin.residencia.banco.conta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class ContasActivity extends AppCompatActivity {
    ContaAdapter adapter;
    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);

        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rvContas);

        adapter = new ContaAdapter(getLayoutInflater());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button adicionarConta = findViewById(R.id.btn_Adiciona);

        // Chamada para listar as contas cadastradas no banco de dados
        viewModel.contas.observe(this, contas -> {
            adapter.submitList(contas);
        });

        // Chamada para adicionar uma nova conta no banco de dados
        adicionarConta.setOnClickListener(
            v -> startActivity(new Intent(this, AdicionarContaActivity.class))
        );
    }

    //TODO Neste arquivo ainda falta implementar o código que atualiza a lista de contas automaticamente na tela
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}