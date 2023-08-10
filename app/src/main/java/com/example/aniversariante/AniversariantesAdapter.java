package com.example.aniversariante;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aniversariante.databinding.LinhaDetalheAniversarianteBinding;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AniversariantesAdapter extends RecyclerView.Adapter<AniversariantesAdapter.ViewHolder> {

    LinhaDetalheAniversarianteBinding binding;
    private List<Aniversariante> aniversario;
    private Context aContext;
    AniversariantesDoDia aniversariantesDoDia;
    Aniversariante aniversariante;
    AniverController aniverController;

    public AniversariantesAdapter(List<Aniversariante> aniversario, Context aContext) {
        this.aniversario = aniversario;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LinhaDetalheAniversarianteBinding binding = LinhaDetalheAniversarianteBinding.inflate(inflater, parent, false);
       ViewHolder viewHolder = new ViewHolder(binding);
        aniversariante = new Aniversariante();
      //  aniversariantesDoDia = new AniversariantesDoDia();

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Aniversariante objLinha = aniversario.get(position);
        Log.d("AniversariantesAdapter", "Nome: " + objLinha.getNome());
        Log.d("AniversariantesAdapter", "Data de Nascimento: " + objLinha.getDataNascimento());

        holder.binding.rvNome.setText(objLinha.getNome());
        holder.binding.txtDataNascimento.setText(objLinha.getDataNascimento());

        // Converter e exibir a data de nascimento no formato Unix Timestamp
        long unixTimestamp = convertToUnixTimestamp(objLinha.getDataNascimento());
        holder.binding.txtDataNascimento.setText(String.valueOf(unixTimestamp));



        /*CheckBox ckPessoaFisica  = holder.binding.ckPessoaFisica;

        try{


            ckPessoaFisica.setText(objLinha.isPessoaFisica()?"CPF "+objLinha.getClientePF().getCpf() : "CNPJ "+objLinha.getClientePJ().getCnpj());
        }catch (Exception e){
            ckPessoaFisica.setText(objLinha.isPessoaFisica()?"CPF " : "CNPJ ");
        }*/
    }

    private long convertToUnixTimestamp(String dataNascimento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String dateString = null;
            Date date = format.parse( dateString);
            return date.getTime() / 1000; // Dividido por 1000 para obter em segundos
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0; // Valor padrão em caso de falha na conversão
    }


    @Override
    public int getItemCount() {
        return aniversario.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinhaDetalheAniversarianteBinding binding;

        public ViewHolder(LinhaDetalheAniversarianteBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
