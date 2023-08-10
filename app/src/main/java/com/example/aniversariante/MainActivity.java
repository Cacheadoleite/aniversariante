package com.example.aniversariante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aniversariante.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Aniversariante aniversariante;
    AniverController aniverController;
    int aniverID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        aniversariante = new Aniversariante();
        aniverController = new AniverController(this);

        binding.btnsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aniversariante.setNome(binding.editPrimeiroNome.getText().toString());
                aniversariante.setDataNascimento(binding.editdata.getText().toString());

                aniverController.incluir(getApplicationContext(), aniversariante);
                aniverID = aniverController.getUltimoID(getApplicationContext());
            }
        });
        binding.btnConsutlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AniversariantesDoDia.class);
                startActivity(intent);
            }
        });
    }
}