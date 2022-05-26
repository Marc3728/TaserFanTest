package com.example.taserfantest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taserfantest.API.Connector;
import com.example.taserfantest.base.BaseActivity;
import com.example.taserfantest.base.CallInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoLoggedd extends BaseActivity implements CallInterface, View.OnClickListener {
    TextView nombreemp;
    private RecyclerView recyclerView;
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosaux;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    private Spinner spinner;
    private TipoVehiculo tipoVehiculo;
    private Button anadirvehiculo;
    private final int correctinsert = 1234;
    private final int canceledinsert = 4321;
    private final int MIUB_CODE = 1212;

    private String matricula;
    private String preciohora;
    private String marca;
    private String descripcion;
    private double bateria;
    private String fechaadq;
    private String colorsel;
    private String estadosel;
    private String tipocarnet;
    private String auxun;
    private String auxdos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_logged);
        recyclerView = findViewById(R.id.recyclerview);
        spinner = findViewById(R.id.spinnertipo);
        tipoVehiculo = TipoVehiculo.TODOS;
        anadirvehiculo = findViewById(R.id.anadirvehiculo);
        vehiculos = new ArrayList<Vehiculo>();
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, vehiculos);
        myRecyclerViewAdapter.setListener(this);

        recyclerView.setAdapter(myRecyclerViewAdapter);
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setOnClickListener(this);

        ArrayAdapter<TipoVehiculo> adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,TipoVehiculo.values());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoVehiculo = (TipoVehiculo) spinner.getSelectedItem();
                executeCall(EmpleadoLoggedd.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tipoVehiculo = tipoVehiculo.TODOS;
            }
        });

        ItemTouchHelper.SimpleCallback sck = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                vehiculos.remove(viewHolder.getAdapterPosition());
                myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(sck);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        anadirvehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InsertarVehiculo.class);
                startActivityForResult(intent,MIUB_CODE);
            }
        });
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        //List<Vehiculo> vehiculoss = Connector.getConector().getAsList(Vehiculo.class,"/tipovehiculos");
//        List<Vehiculo> vehiculoss = Connector.getConector().postAsList(Vehiculo.class,TipoVehiculo.MOTO,"/tipovehiculo");
//        vehiculos.add(new Vehiculo("seat","5645FHF","ACTIVO","rojo"));
//        vehiculos.add(new Vehiculo("bmw","5645FHF","ACTIVO","rojo"));
//        for (Vehiculo v : vehiculoss){
//            vehiculos.add(v);
//        }
        switch (tipoVehiculo){
            case COCHE:
                vehiculosaux = vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==TipoVehiculo.COCHE).collect(Collectors.toList());
                break;
            case MOTO:
                vehiculosaux = vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==TipoVehiculo.MOTO).collect(Collectors.toList());
                break;
            case BICICLETA:
                vehiculosaux = vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==TipoVehiculo.BICICLETA).collect(Collectors.toList());
                break;
            case PATINETE:
                vehiculosaux = vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==TipoVehiculo.PATINETE).collect(Collectors.toList());
                break;
            case TODOS:
                vehiculos = Connector.getConector().getAsList(Vehiculo.class,"/vehiculos");
        }
    }

    @Override
    public void doInUI() {
        if (tipoVehiculo==TipoVehiculo.TODOS){
            myRecyclerViewAdapter.notifyData(vehiculos);
        } else {
            myRecyclerViewAdapter.notifyData(vehiculosaux);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==MIUB_CODE){
            if (resultCode==RESULT_OK){
                matricula = data.getStringExtra("matricula");
                Toast.makeText(getApplicationContext(),"matricula:" + matricula,Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){

            }
        }
    }

    public class EliminaVehiuclo implements CallInterface{

        @Override
        public void doInBackground() {

        }

        @Override
        public void doInUI() {

        }
    }
}