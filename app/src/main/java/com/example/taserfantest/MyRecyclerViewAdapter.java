package com.example.taserfantest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    public View.OnClickListener listener;
    private List<Vehiculo> vehiculos;
    private Context context;

    public MyRecyclerViewAdapter(Context context, List<Vehiculo> vehiculos){
        this.vehiculos = vehiculos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simplevehicle,parent,false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {

        switch (vehiculos.get(position).getTipo()){
            case COCHE:
                holder.image.setImageResource(R.drawable.ic_baseline_directions_car_24);
                break;
            case MOTO:
                holder.image.setImageResource(R.drawable.ic_baseline_two_wheeler_24);
                break;
            case BICICLETA:
                holder.image.setImageResource(R.drawable.ic_baseline_pedal_bike_24);
                break;
            case PATINETE:
                holder.image.setImageResource(R.drawable.ic_baseline_electric_scooter_24);
                break;
            default:
                holder.image.setImageResource(R.drawable.ic_baseline_remove_circle_24);
        }
        String color = vehiculos.get(position).getColor();

        switch (color){
            case "rojo":
                holder.image.setColorFilter(ContextCompat.getColor(context,R.color.rojo));
                break;
            case "azul":
                holder.image.setColorFilter(ContextCompat.getColor(context,R.color.azul));
                break;
            case "negro":
                holder.image.setColorFilter(ContextCompat.getColor(context,R.color.black));
                break;
            case "blanco":
                holder.image.setColorFilter(ContextCompat.getColor(context,R.color.blanco));
                break;
            case "amarillo":
                holder.image.setColorFilter(ContextCompat.getColor(context,R.color.amarillo));
                break;
            case "verde":
                holder.image.setColorFilter(ContextCompat.getColor(context,R.color.verde));
                break;
        }

        switch (vehiculos.get(position).getEstado()){
            case "preparado":
                holder.estado.setColorFilter(ContextCompat.getColor(context,R.color.verde));
                break;
            default:
                holder.estado.setColorFilter(ContextCompat.getColor(context,R.color.rojo));
        }
        holder.modelo.setText(vehiculos.get(position).getModelo());
        holder.matricula.setText(vehiculos.get(position).getMatricula());
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void notifyData(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView modelo;
        TextView matricula;
        ImageView estado;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imagevehicle);
            modelo = itemView.findViewById(R.id.modelvehiculo);
            matricula = itemView.findViewById(R.id.matriculavehiculo);
            estado = itemView.findViewById(R.id.imagestate);
        }
    }
}
