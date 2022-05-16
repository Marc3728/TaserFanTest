package com.example.taserfantest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    public View.OnClickListener listener;
    private Context contextt;
    private List<Vehicle> vehiculos;

    public MyRecyclerViewAdapter(Context context, List<Vehicle> vehiculos){
        this.vehiculos = vehiculos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contextt = context;
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
        holder.modelo.setText(vehiculos.get(position).getModelo());
        holder.matricula.setText(vehiculos.get(position).getMatricula());
        holder.estado.setText(vehiculos.get(position).getEstado());
        holder.color.setText(vehiculos.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView modelo;
        TextView matricula;
        TextView estado;
        TextView color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imagevehicle);
            modelo = itemView.findViewById(R.id.modelvehiculo);
            matricula = itemView.findViewById(R.id.matriculavehiculo);
            estado = itemView.findViewById(R.id.estadovehiculo);
            color = itemView.findViewById(R.id.colorvehiculo);
        }
    }
}
