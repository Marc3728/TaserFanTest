package com.example.taserfantest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
    private TextView matriculat;
    private TextView preciohora;
    private TextView marca;
    private TextView descripcion;
    private TextView bateria;
    private TextView fechaadq;
    private Spinner estados;
    private String estadosel;
    private TextView tipocarnet;
    private TextView infoarriba;
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
        preciohora = findViewById(R.id.phtxtu);
        marca = findViewById(R.id.marcatxtu);
        descripcion = findViewById(R.id.desctxtu);
        bateria = findViewById(R.id.bateriatxtu);
        fechaadq = findViewById(R.id.fechaadqtxtu);
        tipocarnet = findViewById(R.id.tipocarnettxtu);
        matr = getIntent().getExtras().getString("matr");
        tip = getIntent().getExtras().getString("tip");

        infoarriba = findViewById(R.id.infouppdd);
        infoarriba.setText("Updateando "+matr);

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
                auxdost.setVisibility(View.GONE);
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
                if (!matricula.getText().toString().equals("") && !preciohora.getText().toString().equals("") && !marca.getText().toString().equals("") && !descripcion.getText().toString().equals("") && !bateria.getText().toString().equals("") && !fechaadq.getText().toString().equals("") && !tipocarnet.getText().toString().equals("") && !auxunot.getText().toString().equals("")){
                    executeCall(UpdatearVehiculo.this);
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
        } else {
            switch (tipoVehiculo){
                case COCHE:
                    Coche cop = new Coche(matr,Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),Integer.parseInt(auxunot.getText().toString()),Integer.parseInt(auxdost.getText().toString()));
                    result = Connector.getConector().put(Coche.class,cop,"/coche");
                    break;
                case MOTO:
                    Moto mop = new Moto(matr,Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),Double.parseDouble(auxunot.getText().toString()),Double.parseDouble(auxdost.getText().toString()));
                    result = Connector.getConector().put(Moto.class,mop,"/moto");
                    break;
                case BICICLETA:
                    Bicicleta bip = new Bicicleta(matr,Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),auxunot.getText().toString());
                    result = Connector.getConector().put(Bicicleta.class,bip,"/bicicleta");
                    break;
                case PATINETE:
                    Patinete pap = new Patinete(matr,Double.parseDouble(preciohora.getText().toString()),marca.getText().toString(),descripcion.getText().toString(),colorsel,Double.parseDouble(bateria.getText().toString()),fechaadq.getText().toString(),estadosel,tipocarnet.getText().toString(),Double.parseDouble(auxunot.getText().toString()),Integer.parseInt(auxdost.getText().toString()));
                    result = Connector.getConector().put(Patinete.class,pap,"/patinete");
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
                switch (Integer.parseInt(tipocarnet.getText().toString())){
                    case 0:
                        tipocarnet.setText("NO");
                        break;
                    case 1:
                        tipocarnet.setText("AM");
                        break;
                    case 2:
                        tipocarnet.setText("A");
                        break;
                    case 3:
                        tipocarnet.setText("B");
                        break;
                    case 4:
                        tipocarnet.setText("C");
                        break;
                    case 5:
                        tipocarnet.setText("D");
                        break;
                    case 6:
                        tipocarnet.setText("E");
                        break;
                    case 9:
                        tipocarnet.setText("Z");

                }
                primer = false;
            } else {
                Toast.makeText(getApplicationContext(),tipoVehiculo.toString(),Toast.LENGTH_SHORT).show();

            }
        } else {
            if (result instanceof Result.Success){

                Intent intent = new Intent();
                setResult(RESULT_OK);
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatearVehiculo.this);
                Result.Error error = (Result.Error) result;
                builder.setMessage("Error message: "+error.getMessage()).setTitle("Error Insert").setPositiveButton("Vale",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }
}