package com.example.aniversariante;

import android.content.ContentValues;
import android.content.Context;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

public class AniverController extends  AniverDAO {
    private AniverDAO db;
    public static final String TABELA = AniverModel.TABELA;

    private ContentValues dados;

    public AniverController(@Nullable Context context) {
        super(context);
        db = AniverDAO.getInstance(context);
    }

        //recebendo o obj;
        public boolean incluir(Context context, Aniversariante obj) {
            dados = new ContentValues();
            // preparando os dados para enviar para o banco de dados
            // dados.put(VisitanteDataModel.ID,obj.getId());
            dados.put(AniverModel.NOME, obj.getNome());
            dados.put(AniverModel.DATA_NASCIMENTO_UNIX, obj.getDataNascimento());
            AniverDAO db = AniverDAO.getInstance(context);

            return db.insert(TABELA, dados);

        }

        public boolean alterar(Context context, Aniversariante obj) {
            dados = new ContentValues();
            dados.put(AniverModel.ID, obj.getId());
            dados.put(AniverModel.NOME, obj.getNome());
            dados.put(AniverModel.DATA_NASCIMENTO_UNIX, obj.getDataNascimento());

           AniverDAO db = AniverDAO.getInstance(context);
            return db.update(TABELA, dados, "id=?", new String[]{String.valueOf(obj.getId())});
            //  return db.update(TABELA,dados);
        }

        public boolean deletar(Context context, Aniversariante obj) {
            AniverDAO db = AniverDAO.getInstance(context);
            return db.delete(TABELA, obj.getId());
        }

        public List<Aniversariante> listar(Context context) {
            Log.d("aniverController", "Listar: Iniciando busca por aniversariante");
           AniverDAO db = AniverDAO.getInstance(context);
            return db.buscarListAniversarianteDoDia();
        }


// Agora você pode usar a lista aniversariantesDoDia como necessário


        public int getUltimoID(Context context) {
            AniverDAO db = AniverDAO.getInstance(context);

            return db.getLastPK(TABELA);
        }

        public Aniversariante getVisitante2ById(Context context, Aniversariante obj) {
            AniverDAO db = AniverDAO.getInstance(context);

            return db.getAniversarioById(AniverModel.TABELA, obj);
        }

    }

