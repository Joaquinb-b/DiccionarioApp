package com.example.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.VerActivity;
import com.example.myapplication.entidades.Palabras;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listaPalabrasAdapter extends RecyclerView.Adapter<listaPalabrasAdapter.PalabraViewHolder> {

    ArrayList<Palabras> listaPalabras;
    ArrayList<Palabras> listaOriginal;

    public listaPalabrasAdapter(ArrayList<Palabras> listaPalabras){
        this.listaPalabras = listaPalabras;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaPalabras);
    }

    @NonNull
    @Override
    public PalabraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_palabra, null, false);
        return new PalabraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalabraViewHolder holder, int position) {
        holder.viewPalabra.setText(listaPalabras.get(position).getPalabra());
        Palabras palabraActual = listaPalabras.get(position);
        holder.viewDefinicion.setText("Definicion: " + palabraActual.getDefinicion());
    }

    public void filtrado(String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaPalabras.clear();
            listaPalabras.addAll(listaOriginal);
        } else {
            List<Palabras> coleccion = listaOriginal.stream()
                    .filter(i -> i.getPalabra().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            listaPalabras.clear();
            listaPalabras.addAll(coleccion);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { //tamaño de la lista
        return listaPalabras.size();//para conecer el tamano de la lista
    }

    public class PalabraViewHolder extends RecyclerView.ViewHolder {

        TextView viewPalabra, viewDefinicion;

        public PalabraViewHolder(@NonNull View itemView) {
            super(itemView);

            viewPalabra = itemView.findViewById(R.id.viewPalabra);
            viewDefinicion = itemView.findViewById(R.id.viewDefinicion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPalabras.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}