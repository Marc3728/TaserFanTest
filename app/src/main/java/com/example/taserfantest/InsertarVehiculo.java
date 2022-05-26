package com.example.taserfantest;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertarVehiculo extends BaseActivity implements CallInterface {
    private Spinner spinner;
    private Spinner colores;
    private Button anadirv;
    private Button cancelarv;
    private TipoVehiculo seleccionado;
    private TextView auxuno;
    private TextView auxdos;
    private TextView auxunot;
    private TextView auxdost;
    private String colorsel;
    private TextView matricula;
    private TextView preciohora;
    private TextView marca;
    private TextView descripcion;
    private TextView bateria;
    private TextView fechaadq;
    private Spinner estados;
    private String estadosel;
    private TextView tipocarnet;
    private Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_vehiculo);
        spinner = findViewById(R.id.spinnerinserciontipos);
        colores = findViewById(R.id.colortxti);
        estados = findViewById(R.id.estadotxti);
        auxuno = findViewById(R.id.numplazasins);
        auxdos = findViewById(R.id.numpuertasins);
        auxunot = findViewById(R.id.auxuntxti);
        auxdost = findViewById(R.id.auxdostxti);
        anadirv = findViewById(R.id.anadirvehiculob);
        cancelarv = findViewById(R.id.cancelaranadirvehiculob);
        matricula = findViewById(R.id.matriculatxti);
        preciohora = findViewById(R.id.phtxti);
        marca = findViewById(R.id.marcatxti);
        descripcion = findViewById(R.id.desctxti);
        bateria = findViewById(R.id.bateriatxti);
        fechaadq = findViewById(R.id.fechaadqtxti);
        tipocarnet = findViewById(R.id.tipocarnettxti);
        ArrayAdapter<TipoVehiculo> adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,Arrays.stream(TipoVehiculo.values()).filter(tipoVehiculo -> tipoVehiculo != TipoVehiculo.TODOS).collect(Collectors.toList()));
        spinner.setAdapter(adapter);

        ArrayAdapter<Colores> adaptercol = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,Colores.values());
        colores.setAdapter(adaptercol);

        ArrayAdapter<Colores> adapterest = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,Estados.values());
        estados.setAdapter(adapterest);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seleccionado = (TipoVehiculo) spinner.getSelectedItem();
                auxdos.setVisibility(View.VISIBLE);
                auxdost.setVisibility(View.VISIBLE);
                switch (seleccionado){
                    case COCHE:
                        auxuno.setText("NumeroPlazas");
                        auxdos.setText("NumeroPuertas");
                        break;
                    case MOTO:
                        auxuno.setText("VelocidadMaxmima");
                        auxdos.setText("Cilindrada");
                        break;
                    case BICICLETA:
                        auxdos.setVisibility(View.INVISIBLE);
                        auxdost.setVisibility(View.INVISIBLE);
                        auxuno.setText("Tipo");
                        break;
                    case PATINETE:
                        auxuno.setText("Tamano");
                        auxdos.setText("Ruedas");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner.setSelection(1);
            }
        });

        colores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                colorsel = colores.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                colorsel = Colores.blanco.toString();
            }
        });

        estados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                estadosel = estados.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                estadosel = Estados.alquilado.toString();
            }
        });

        anadirv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                setResult(RESULT_OK);
//                finish();
                if (!matricula.getText().toString().equals("") && !preciohora.getText().toString().equals("") && !marca.getText().toString().equals("") && !descripcion.getText().toString().equals("") && !bateria.getText().toString().equals("") && !fechaadq.getText().toString().equals("") && !tipocarnet.getText().toString().equals("")){
                    /*Intent intent = new Intent();
                    intent.putExtra("matriculai",matricula.getText().toString());
                    intent.putExtra("preciohorai",preciohora.getText().toString());
                    intent.putExtra("marcai",marca.getText().toString());
                    intent.putExtra("colori",colorsel);
                    intent.putExtra("descripcioni",descripcion.getText().toString());
                    intent.putExtra("bateriai",bateria.getText().toString());
                    intent.putExtra("fechaadqi",fechaadq.getText().toString());
                    intent.putExtra("estadoi",estadosel);
                    intent.putExtra("tipocarneti",tipocarnet.getText().toString());
                    intent.putExtra("auxuno",auxunot.getText().toString());
                    intent.putExtra("auxdos",auxdost.getText().toString());
                    intent.putExtra("tiposel",seleccionado.toString());
                    setResult(RESULT_OK);
                    finish();*/
                    executeCall(InsertarVehiculo.this);
                }
            }
        });

        cancelarv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public void doInBackground() {
        switch (seleccionado){
            case COCHE:
                result = Connector.getConector().post(Coche.class,new Coche(matricula.getText().toString(),Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),Integer.parseInt(auxunot.getText().toString()),Integer.parseInt(auxdost.getText().toString())),"/insertc");
                break;
            case MOTO:
                result = Connector.getConector().post(Moto.class,new Moto(matricula.getText().toString(),Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),Double.parseDouble(auxunot.getText().toString()),Double.parseDouble(auxdost.getText().toString())),"/insertm");
                break;
            case BICICLETA:
                result = Connector.getConector().post(Bicicleta.class,new Bicicleta(matricula.getText().toString(),Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),Double.parseDouble(auxunot.getText().toString()),Integer.parseInt(auxdost.getText().toString())),"/insertb");
                break;
            case PATINETE:
                result = Connector.getConector().post(Patinete.class,new Patinete(matricula.getText().toString(),Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),auxunot.getText().toString()),"/insertp");

        }
    }

    @Override
    public void doInUI() {
        if (result instanceof Result.Success){
            LoggedInUserRepository.getInstance().login((Empleado) ((Result.Success<?>) result).getData());
            Result.Success<Object> resultado = (Result.Success<Object>) result;
            Intent intent = new Intent();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Insercion erronea",Toast.LENGTH_SHORT).show();
        }
    }
}