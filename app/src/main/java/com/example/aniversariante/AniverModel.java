package com.example.aniversariante;

public class AniverModel {
    public static final String TABELA = "viva";
    public static final String ID = "id";
    public static final String NOME = "nomeCompleto";
    public static final String DATA_NASCIMENTO_UNIX = "dataNascimetno";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static  String query;

    // metodo de criar a estrutura da tabela
    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += NOME+" TEXT, ";
        query += DATA_NASCIMENTO_UNIX+" TEXT, ";
        query += DATA_INC+" datetime default current_timestamp, ";
        query += DATA_ALT+" datetime default current_timestamp ";
        //datatime default current_timestamp,
        query += ")";

        return query;
    }
}
