package com.example.taserfantest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.taserfantest.API.Connector;
import com.example.taserfantest.API.Result;
import com.example.taserfantest.base.BaseActivity;
import com.example.taserfantest.base.CallInterface;

public class DatosVehiculoEspecifico extends BaseActivity implements CallInterface {

    private String matr;
    private String tip;
    private TipoVehiculo tipoVehiculo;
    private TextView auxuno;
    private TextView auxdos;
    private Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_vehiculo_especifico);

        matr = getIntent().getExtras().getString("matr");
        tip = getIntent().getExtras().getString("tip");
        switch (tip){
            case "COCHE":
                auxuno.setText("NumPlazas");
                auxdos.setText("NumPuertas");
                tipoVehiculo = TipoVehiculo.COCHE;
                break;
            case "MOTO":
                auxuno.setText("VelocidadMax");
                auxdos.setText("Cilindrada");
                tipoVehiculo = TipoVehiculo.MOTO;
                break;
            case "BICICLETA":
                auxuno.setText("tipo");
                auxdos.setVisibility(View.GONE);
                tipoVehiculo = TipoVehiculo.BICICLETA;
                break;
            case "PATINETE":
                auxuno.setText("tamano");
                auxdos.setText("ruedas");
                tipoVehiculo = TipoVehiculo.PATINETE;
        }
    }

    @Override
    public void doInBackground() {
        switch (tipoVehiculo){
            case COCHE:
                result = Connector.getConector().selVehiculo(Coche.class,matr,"/coche");
                break;
            case MOTO:
                result = Connector.getConector().selVehiculo(Moto.class,matr,"/moto");
                break;
            case BICICLETA:
                result = Connector.getConector().selVehiculo(Bicicleta.class,matr,"/bicicleta");
                break;
            case PATINETE:
                result = Connector.getConector().selVehiculo(Patinete.class,matr,"/patinete");
        }
    }

    @Override
    public void doInUI() {

    }
}