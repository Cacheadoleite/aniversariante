package com.example.aniversariante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AniverDAO extends SQLiteOpenHelper{
    public static final String DB_ANINAME = "aniverdados.sqlite";
    public static final int DB_VERSION = 1;
    Cursor cursor;
    Context context;


    SQLiteDatabase db;
    private static AniverDAO instance;

    public AniverDAO(@Nullable Context context) {
        super(context, DB_ANINAME, null, 1);
        //escreve no bd
        this.context = context;
        db = getWritableDatabase();

    }
    public static synchronized AniverDAO getInstance(Context context) {
        if (instance == null) {
            instance = new AniverDAO(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(AniverModel.gerarTabela());

        //    Log.i(AppUtil.LOG_APP, " tabela visitante" + VisitanteDataModel.gerarTabela());

        } catch (SQLException e) {

         //   Log.e(AppUtil.LOG_APP, "Erro tabela visitante" + e.getMessage());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Atualizar os BD incluse as tabelas

    }

    public boolean insert(String tabela, ContentValues dados) {
        boolean sucesso = true;

        try {
            sucesso = db.insert(tabela, null, dados) > 0;
         //   Log.i(AppUtil.LOG_APP, tabela + " insert(), executado com sucesso;");


        } catch (SQLException e) {

        //    Log.e(AppUtil.LOG_APP, tabela + "falhou ao executar o insert" + e.getMessage());

        }

        return sucesso;
    }

    /**
     * Deletar dados do banco de dodos
     *
     * @return
     */
    public boolean delete(String tabela, int id) {
        boolean sucesso = true;
        try {
            sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;

          //  Log.i(AppUtil.LOG_APP, tabela + " deletar(), executado com sucesso;");

        } catch (SQLException e) {

        //    Log.e(AppUtil.LOG_APP, tabela + "falhou ao executar o delete" + e.getMessage());

        }
        return sucesso;

    }

    /**
     * Alterar dados do banco de dados
     *
     * @return
     */
    public boolean update(String tabela, ContentValues dados){
        boolean sucesso = true;
        try{
            int id = dados.getAsInteger("id");
            sucesso = db.update(tabela,dados,"id=?", new String[]{Integer.toString(id)})>0;
         //   Log.i(AppUtil.LOG_APP,tabela+ " update(), executado com sucesso;");

        }catch (SQLException e){

         //   Log.e(AppUtil.LOG_APP,tabela+"falhou ao executar o update"+ e.getMessage());
        }
        return sucesso;
    }

    public boolean update(String tabela, ContentValues dados, String clausulaWhere, String[] argumentosWhere) {
        boolean sucesso = true;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            int id = dados.getAsInteger("id");
            sucesso = db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)}) > 0;
         //   Log.i(AppUtil.LOG_APP, tabela + " update(), executado com sucesso.");
        } catch (SQLException e) {
         //   Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o update: " + e.getMessage());
            sucesso = false;
        }
        return sucesso;
    }
    public List<Aniversariante> buscarListAniversarianteDoDia() {
        List<Aniversariante> aniversariantes = new ArrayList<>();

        long currentTimeUnix = System.currentTimeMillis() / 1000; // Current Unix Time (seconds since epoch)
        long startOfDayUnix = currentTimeUnix - (currentTimeUnix % 86400); // Start of the current day in Unix Time

        String query = "SELECT * FROM " + AniverModel.TABELA +
                " WHERE " + AniverModel.DATA_NASCIMENTO_UNIX + " >= " + startOfDayUnix +
                " AND " + AniverModel.DATA_NASCIMENTO_UNIX + " < " + (startOfDayUnix + 86400);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Aniversariante aniversarianteDoDia = new Aniversariante();
                aniversarianteDoDia.setId(cursor.getInt(cursor.getColumnIndexOrThrow(AniverModel.ID)));
                aniversarianteDoDia.setNome(cursor.getString(cursor.getColumnIndexOrThrow(AniverModel.NOME)));
                aniversarianteDoDia.setDataNascimento(cursor.getString(cursor.getColumnIndexOrThrow(AniverModel.DATA_NASCIMENTO_UNIX)));

                aniversariantes.add(aniversarianteDoDia);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return aniversariantes;
    }


    /*public List<Aniversariante> buscarListAniversarianteDoDia(String formattedCurrentDate) {
        List<Aniversariante> aniversariantes = new ArrayList<>();
        // Obter a data atual no formato "dd/MM/yyyy"
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        //String formattedCurrentDate = formatador.format(new Date()); //TODO

        String query = "SELECT * FROM " + AniverModel.TABELA +
                " WHERE DATE(" + AniverModel.DATA_NASCIMENTO + ") = strftime('%d/%m/%Y','now')";
      //  Log.d(AppUtil.LOG_APP, "Consulta SQL: " + query);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Aniversariante aniversarianteDoDia = new Aniversariante();
                aniversarianteDoDia.setId(cursor.getInt(cursor.getColumnIndexOrThrow(AniverModel.ID)));
                aniversarianteDoDia.setNome(cursor.getString(cursor.getColumnIndexOrThrow(AniverModel.NOME)));
                aniversarianteDoDia.setDataNascimento(cursor.getString(cursor.getColumnIndexOrThrow(AniverModel.DATA_NASCIMENTO)));


                aniversariantes.add(aniversarianteDoDia);
            } while (cursor.moveToNext());

            cursor.close();
          //  Log.e(AppUtil.LOG_APP, AniverModel.TABELA + " lista gerada");
        }

        return aniversariantes;
    }*/



    public int getLastPK(String tabela) {


        String sql = "SELECT seq FROM sqlite_sequence WHERE name= '" + tabela + "'";
        Cursor cursor = null;
        try {

           // Log.e(AppUtil.LOG_APP, "SQL Raw: " + sql);
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    return cursor.getInt(cursor.getColumnIndexOrThrow("seq"));

                } while (cursor.moveToNext());
            }

        } catch (SQLException e) {
          //  Log.e(AppUtil.LOG_APP, "Erro ao recuperando ultimo PK " + tabela);
         //   Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }
        return -1;
    }

    public Aniversariante getAniversarioById(String tabela, Aniversariante obj) {

        Aniversariante parabens = new Aniversariante();
        ;
        String sql = "SELECT * FROM " + tabela + " WHERE id = " + obj.getId();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {

                parabens.setId(cursor.getInt(cursor.getColumnIndexOrThrow(AniverModel.ID)));
                parabens.setNome(cursor.getString(cursor.getColumnIndexOrThrow(AniverModel.NOME)));
                parabens.setDataNascimento(cursor.getString(cursor.getColumnIndexOrThrow(AniverModel.DATA_NASCIMENTO_UNIX)));



            }
        } catch (SQLException e) {

        }

        return parabens;
    }
    public static long convertDateToUnixTime(String formattedDate) {
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatador.parse(formattedDate);
            return date.getTime() / 1000; // Convert to Unix Time (seconds since epoch)
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Return a negative value to indicate an error
        }
    }

    }
