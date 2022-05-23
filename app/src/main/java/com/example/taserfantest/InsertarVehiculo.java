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

import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertarVehiculo extends AppCompatActivity {
    private Spinner spinner;
    private Button anadirv;
    private Button cancelarv;
    private TipoVehiculo seleccionado;
    private TextView auxuno;
    private TextView auxdos;
    private TextView auxdost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_vehiculo);
        spinner = findViewById(R.id.spinnerinserciontipos);
        auxuno = findViewById(R.id.numplazasins);
        auxdos = findViewById(R.id.numpuertasins);
        auxdost = findViewById(R.id.auxdostxti);
        anadirv = findViewById(R.id.anadirvehiculob);
        cancelarv = findViewById(R.id.cancelaranadirvehiculob);
        ArrayAdapter<TipoVehiculo> adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,Arrays.stream(TipoVehiculo.values()).filter(tipoVehiculo -> tipoVehiculo != TipoVehiculo.TODOS).collect(Collectors.toList()));
        spinner.setAdapter(adapter);

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

        anadirv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK);
                finish();
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
}