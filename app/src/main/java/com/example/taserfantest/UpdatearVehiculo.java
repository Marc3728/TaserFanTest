package com.example.taserfantest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class UpdatearVehiculo extends BaseActivity implements CallInterface {

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
    private String matr;
    private String tip;
    private boolean primer;
    private Result result;
    private TipoVehiculo tipoVehiculo;
    private ArrayAdapter<Estados> adapterest;
    private ArrayAdapter<Colores> adaptercol;
    private Colores color;
    private Estados estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatear_vehiculo);
        colores = findViewById(R.id.colortxtu);
        estados = findViewById(R.id.estadotxtu);
        auxuno = findViewById(R.id.numplazasupd);
        auxdos = findViewById(R.id.numpuertasupd);
        auxunot = findViewById(R.id.auxuntxtu);
        auxdost = findViewById(R.id.auxdostxtu);
        anadirv = findViewById(R.id.updatearvehiculou);
        cancelarv = findViewById(R.id.cancelaranadirvehiculou);
        matricula = findViewById(R.id.matriculatxtu);
        preciohora = findViewById(R.id.phtxtu);
        marca = findViewById(R.id.marcatxtu);
        descripcion = findViewById(R.id.desctxtu);
        bateria = findViewById(R.id.bateriatxtu);
        fechaadq = findViewById(R.id.fechaadqtxtu);
        tipocarnet = findViewById(R.id.tipocarnettxtu);
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
        primer = true;
        adaptercol = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,Colores.values());
        colores.setAdapter(adaptercol);

        adapterest = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,Estados.values());
        estados.setAdapter(adapterest);
        executeCall(this);

        anadirv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cancelarv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void doInBackground() {
        if (primer==true){
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
    }

    @Override
    public void doInUI() {
        if (primer==true){
            if (result instanceof Result.Success){
                switch (tipoVehiculo){
                    case COCHE:
                        Coche resultadoc = (Coche) ((Result.Success<?>) result).getData();
                        Toast.makeText(getApplicationContext(),resultadoc.getTipocarnet(),Toast.LENGTH_SHORT).show();
                        matricula.setText(resultadoc.getMatricula());
                        preciohora.setText(String.valueOf(resultadoc.getPreciohora()));
                        marca.setText(resultadoc.getMarca());
                        color = Arrays.stream(Colores.values()).filter(colores1 -> colores1.toString().equals(resultadoc.getColor())).collect(Collectors.toList()).get(0);
                        colores.setSelection(adaptercol.getPosition(color));
                        descripcion.setText(resultadoc.getDescripcion());
                        bateria.setText(String.valueOf(resultadoc.getBateria()));
                        fechaadq.setText(resultadoc.getFecha());
                        tipocarnet.setText(resultadoc.getTipocarnet());
                        estado = Arrays.stream(Estados.values()).filter(estados1 -> estados1.toString().equals(resultadoc.getEstado())).collect(Collectors.toList()).get(0);
                        estados.setSelection(adapterest.getPosition(estado));
                        auxunot.setText(String.valueOf(resultadoc.getNumplazas()));
                        auxdost.setText(String.valueOf(resultadoc.getNumpuertas()));
                        break;
                    case MOTO:
                        Moto resultadom = (Moto) ((Result.Success<?>) result).getData();
                        Toast.makeText(getApplicationContext(),resultadom.getTipocarnet(),Toast.LENGTH_SHORT).show();
                        matricula.setText(resultadom.getMatricula());
                        preciohora.setText(String.valueOf(resultadom.getPreciohora()));
                        marca.setText(resultadom.getMarca());
                        color = Arrays.stream(Colores.values()).filter(colores1 -> colores1.toString().equals(resultadom.getColor())).collect(Collectors.toList()).get(0);
                        colores.setSelection(adaptercol.getPosition(color));
                        descripcion.setText(resultadom.getDescripcion());
                        bateria.setText(String.valueOf(resultadom.getBateria()));
                        fechaadq.setText(resultadom.getFecha());
                        tipocarnet.setText(resultadom.getTipocarnet());
                        estado = Arrays.stream(Estados.values()).filter(estados1 -> estados1.toString().equals(resultadom.getEstado())).collect(Collectors.toList()).get(0);
                        estados.setSelection(adapterest.getPosition(estado));
                        auxunot.setText(String.valueOf(resultadom.getVelocidadmax()));
                        auxdost.setText(String.valueOf(resultadom.getCilindrada()));
                        break;
                    case BICICLETA:
                        Bicicleta resultadob = (Bicicleta) ((Result.Success<?>) result).getData();
                        Toast.makeText(getApplicationContext(),resultadob.getTipocarnet(),Toast.LENGTH_SHORT).show();
                        matricula.setText(resultadob.getMatricula());
                        preciohora.setText(String.valueOf(resultadob.getPreciohora()));
                        marca.setText(resultadob.getMarca());
                        color = Arrays.stream(Colores.values()).filter(colores1 -> colores1.toString().equals(resultadob.getColor())).collect(Collectors.toList()).get(0);
                        colores.setSelection(adaptercol.getPosition(color));
                        descripcion.setText(resultadob.getDescripcion());
                        bateria.setText(String.valueOf(resultadob.getBateria()));
                        fechaadq.setText(resultadob.getFecha());
                        tipocarnet.setText(resultadob.getTipocarnet());
                        estado = Arrays.stream(Estados.values()).filter(estados1 -> estados1.toString().equals(resultadob.getEstado())).collect(Collectors.toList()).get(0);
                        estados.setSelection(adapterest.getPosition(estado));
                        auxunot.setText(resultadob.getTipo());
                        break;
                    case PATINETE:
                        Patinete resultadop = (Patinete) ((Result.Success<?>) result).getData();
                        Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                        matricula.setText(resultadop.getMatricula());
                        preciohora.setText(String.valueOf(resultadop.getPreciohora()));
                        marca.setText(resultadop.getMarca());
                        color = Arrays.stream(Colores.values()).filter(colores1 -> colores1.toString().equals(resultadop.getColor())).collect(Collectors.toList()).get(0);
                        colores.setSelection(adaptercol.getPosition(color));
                        descripcion.setText(resultadop.getDescripcion());
                        bateria.setText(String.valueOf(resultadop.getBateria()));
                        fechaadq.setText(resultadop.getFecha());
                        tipocarnet.setText(resultadop.getTipocarnet());
                        estado = Arrays.stream(Estados.values()).filter(estados1 -> estados1.toString().equals(resultadop.getEstado())).collect(Collectors.toList()).get(0);
                        estados.setSelection(adapterest.getPosition(estado));
                        auxunot.setText(String.valueOf(resultadop.getTamano()));
                        auxdost.setText(String.valueOf(resultadop.getRuedas()));
                }
            } else {
                Toast.makeText(getApplicationContext(),tipoVehiculo.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}