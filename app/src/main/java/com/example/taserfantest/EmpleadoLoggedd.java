package com.example.taserfantest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.taserfantest.base.BaseActivity;
import com.example.taserfantest.base.CallInterface;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoLoggedd extends BaseActivity implements CallInterface, View.OnClickListener {
    TextView nombreemp;
    private RecyclerView recyclerView;
    private List<Vehicle> vehiculos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_logged);
    }

    @Override
    public void doInBackground() {
        vehiculos = new ArrayList<Vehicle>();
        //vehiculos.add(new Vehicle("seat","5645FHF","ACTIVO","rojo"));
        //vehiculos.add(new Vehicle("bmw","5645FHF","ACTIVO","rojo"));
    }

    @Override
    public void doInUI() {
        recyclerView = findViewById(R.id.recyclerview);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, vehiculos);
        myRecyclerViewAdapter.setListener(this);

        recyclerView.setAdapter(myRecyclerViewAdapter);
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}