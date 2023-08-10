package com.example.aniversariante;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aniversariante.databinding.ActivityAniversariantesDoDiaBinding;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AniversariantesDoDia extends AppCompatActivity {
    ActivityAniversariantesDoDiaBinding binding;
    private RecyclerView recyclerView;
    private List<Aniversariante> aniversario;
    private AniverController aniverController;
    Aniversariante aniversariante;
    RecyclerItemClickListener recyclerItemClickListener;


    /*AniverController aniverController;
    private AniverDAO appDataBase;
    public static final int CONSULTA = 0;
    public static final int ATUALIZAR = 1;
    public static final int EXCLUIR = 2;
    int opcoes;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAniversariantesDoDiaBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        aniversariante = new Aniversariante();
        aniverController = new AniverController(this);
        recyclerView = binding.recyclerAniversariantes; // Make sure to match the ID in your layout XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        aniversario = new ArrayList<>();
        AniverDAO appDataBase = AniverDAO.getInstance(this);

        List<Aniversariante> dataFromDatabase = appDataBase.buscarListAniversarianteDoDia();
        // Clear existing aniversario list and add dataFromDatabase
        aniversario.clear();
        aniversario.addAll(dataFromDatabase);

        AniverAdapter adapter = new AniverAdapter(aniversario, this);
        recyclerView.setAdapter(adapter);
    }
    private long convertToUnixTimestamp(String dataNascimento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dataNascimento);
            return date.getTime(); // Removed / 1000 for milliseconds
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Other methods and code...
}


/* // Obter a data atual no formato "dd/MM"
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM");
        String formattedCurrentDate = formatador.format(new Date());
        // Inicialize o RecyclerView aqui
        recyclerView = findViewById(R.id.recyclerAniversariantes);
        aniversario = new ArrayList<>();

        // Inicialize seu banco de dados e recupere dados
        AniverDAO appDataBase = AniverDAO.getInstance(this);
        List<Aniversariante> dataFromDatabase = appDataBase.buscarListAniversarianteDoDia(AniverModel.TABELA);
        Log.d("AniversariantesDoDia", "Tamanho da lista de aniversrintes: " + dataFromDatabase.size());
        // Adicionar dados do banco de dados ao itemList
        aniversario.addAll(dataFromDatabase);
        Log.d("AniversariantesDoDia", "Nome: " + aniversariante.getNome());
        Log.d("AniversariantesDoDia", "Data de Nascimento: " + aniversariante.getDataNascimento());

        //Configure o adaptador personalizado para o RecyclerView
        // VisitanteAdapter adapter = new VisitanteAdapter(aniversario, this);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //  recyclerView.setAdapter(adapter);

        AniversariantesAdapter adapter = new AniversariantesAdapter(aniversario, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
*/





