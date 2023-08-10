package com.example.aniversariante;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AniverAdapter extends RecyclerView.Adapter<AniverAdapter.ViewHolder>{

    private List<Aniversariante> aAniversario;
    private Context aContext;

    public AniverAdapter(List<Aniversariante> aAniversario, Context aContext) {
        this.aAniversario = aAniversario;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the item layout and create the ViewHolder
        View itemView = inflater.inflate(R.layout.linha_detalhe_aniversariante, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AniverAdapter.ViewHolder holder, int position) {
        // Bind data to the views in the ViewHolder
        Aniversariante aniversariante = aAniversario.get(position);
        holder.bind(aniversariante);
    }
    private long convertToUnixTimestamp(String dataNascimento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dataNascimento);
            return date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public int getItemCount() {
        return aAniversario.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeTextView;
        private TextView dataNascimentoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            nomeTextView = itemView.findViewById(R.id.rvNome);
            dataNascimentoTextView = itemView.findViewById(R.id.txtDataNascimento);
        }

        public void bind(Aniversariante aniversariante) {
            // Bind data to views
            nomeTextView.setText(aniversariante.getNome());
            dataNascimentoTextView.setText(aniversariante.getDataNascimento());
        }
    }

}

