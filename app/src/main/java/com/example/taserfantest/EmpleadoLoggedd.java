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
import com.example.taserfantest.API.Result;
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

    private final int UPDT_CODE = 2121;

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

    private Vehiculo vehiculoeliminar;
    private EliminaVehiuclo eliminaVehiuclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_logged);
        recyclerView = findViewById(R.id.recyclerview);
        spinner = findViewById(R.id.spinnertipo);
        tipoVehiculo = TipoVehiculo.TODOS;
        anadirvehiculo = findViewById(R.id.anadirvehiculo);
        eliminaVehiuclo = new EliminaVehiuclo();
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

        ItemTouchHelper.SimpleCallback sck = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction){
                    case ItemTouchHelper.LEFT:
                        vehiculoeliminar = vehiculos.get(viewHolder.getAdapterPosition());
                        //vehiculos.remove(viewHolder.getAdapterPosition());
                        //myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        eliminaVehiuclo.setVehiculo(vehiculoeliminar);
                        eliminaVehiuclo.ejecuta();
                        vehiculos.remove(viewHolder.getAdapterPosition());
                        myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        break;
                    case ItemTouchHelper.RIGHT:
                        Intent intent = new Intent(getApplicationContext(),UpdatearVehiculo.class);
                        intent.putExtra("matr",vehiculos.get(viewHolder.getAdapterPosition()).getMatricula());
                        intent.putExtra("tip",vehiculos.get(viewHolder.getAdapterPosition()).getTipo().toString());
                        startActivityForResult(intent,UPDT_CODE);
                }
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

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        Vehiculo vp = vehiculos.get(recyclerView.getChildAdapterPosition(view));
        Intent intent = new Intent(getApplicationContext(),DatosVehiculoEspecifico.class);
        intent.putExtra("matr",vp.getMatricula());
        intent.putExtra("tip",vp.getTipo().toString());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==MIUB_CODE){
            if (resultCode==RESULT_OK){
                matricula = data.getStringExtra("matricula");
                Toast.makeText(getApplicationContext(),"Se ha insertado el vehiculo:" + matricula,Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){

            }
        } else if (requestCode==UPDT_CODE){
            if (resultCode==RESULT_OK){

                Toast.makeText(getApplicationContext(),"Se ha updateado el vehiculo:",Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){

            }
        }
    }

    public class EliminaVehiuclo extends BaseActivity implements CallInterface{

        private Result result;
        private Vehiculo vehiculo;
        public void ejecuta(){
            executeCall(EliminaVehiuclo.this);
        }

        @Override
        public void doInBackground() {
            switch (vehiculo.getTipo()){
                case COCHE:
                    result = Connector.getConector().deleteVehiculo(String.class, vehiculo.getMatricula(), "/coche");
                    break;
                case MOTO:
                    result = Connector.getConector().deleteVehiculo(String.class, vehiculo.getMatricula(), "/moto");
                    break;
                case BICICLETA:
                    result = Connector.getConector().deleteVehiculo(String.class, vehiculo.getMatricula(), "/bicicleta");
                    break;
                case PATINETE:
                    result = Connector.getConector().deleteVehiculo(String.class, vehiculo.getMatricula(), "/patinete");
            }
        }

        @Override
        public void doInUI() {

        }

        public void setVehiculo(Vehiculo vehiculo) {
            this.vehiculo = vehiculo;
        }

        public Result getResult() {
            return result;
        }
    }
}