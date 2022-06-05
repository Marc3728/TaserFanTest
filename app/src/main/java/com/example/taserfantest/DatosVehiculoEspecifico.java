package com.example.taserfantest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

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
    private TextView auxunot;
    private TextView auxdost;
    private TextView matricula;
    private TextView preciohora;
    private TextView marca;
    private TextView descripcion;
    private TextView bateria;
    private TextView tipocarnet;
    private ImageView imgv;
    private ImageView estadoicono;
    private TextView fechaadq;
    private String color;
    private String estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_vehiculo_especifico);

        matr = getIntent().getExtras().getString("matr");
        tip = getIntent().getExtras().getString("tip");
        auxuno = findViewById(R.id.textauxun);
        auxdos = findViewById(R.id.textauxdos);
        auxunot = findViewById(R.id.auxuninfo);
        auxdost = findViewById(R.id.auxdosinfo);
        matricula = findViewById(R.id.matriculainfo);
        marca = findViewById(R.id.marcainfo);
        preciohora = findViewById(R.id.prhrinfo);
        tipocarnet = findViewById(R.id.carnetinfo);
        bateria = findViewById(R.id.batrinfo);
        fechaadq = findViewById(R.id.fechaadqinfo);
        descripcion = findViewById(R.id.descinfo);
        imgv = findViewById(R.id.imginfoo);
        estadoicono = findViewById(R.id.estticon);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
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
        if (result instanceof Result.Success){
            switch (tipoVehiculo){
                case COCHE:
                    Coche resultadoc = (Coche) ((Result.Success<?>) result).getData();

                    matricula.setText(resultadoc.getMatricula());
                    preciohora.setText(String.valueOf(resultadoc.getPreciohora()));
                    marca.setText(resultadoc.getMarca());
                    descripcion.setText(resultadoc.getDescripcion());
                    bateria.setText(String.valueOf(resultadoc.getBateria()));
                    fechaadq.setText(resultadoc.getFecha());
                    tipocarnet.setText(resultadoc.getTipocarnet());
                    auxunot.setText(String.valueOf(resultadoc.getNumplazas()));
                    auxdost.setText(String.valueOf(resultadoc.getNumpuertas()));
                    color = resultadoc.getColor();
                    estado = resultadoc.getEstado();
                    break;
                case MOTO:
                    Moto resultadom = (Moto) ((Result.Success<?>) result).getData();

                    matricula.setText(resultadom.getMatricula());
                    preciohora.setText(String.valueOf(resultadom.getPreciohora()));
                    marca.setText(resultadom.getMarca());
                    descripcion.setText(resultadom.getDescripcion());
                    bateria.setText(String.valueOf(resultadom.getBateria()));
                    fechaadq.setText(resultadom.getFecha());
                    tipocarnet.setText(resultadom.getTipocarnet());
                    auxunot.setText(String.valueOf(resultadom.getVelocidadmax()));
                    auxdost.setText(String.valueOf(resultadom.getCilindrada()));
                    color = resultadom.getColor();
                    estado = resultadom.getEstado();
                    break;
                case BICICLETA:
                    Bicicleta resultadob = (Bicicleta) ((Result.Success<?>) result).getData();

                    matricula.setText(resultadob.getMatricula());
                    preciohora.setText(String.valueOf(resultadob.getPreciohora()));
                    marca.setText(resultadob.getMarca());
                    descripcion.setText(resultadob.getDescripcion());
                    bateria.setText(String.valueOf(resultadob.getBateria()));
                    fechaadq.setText(resultadob.getFecha());
                    tipocarnet.setText(resultadob.getTipocarnet());
                    auxunot.setText(resultadob.getTipo());
                    color = resultadob.getColor();
                    estado = resultadob.getEstado();
                    break;
                case PATINETE:
                    Patinete resultadop = (Patinete) ((Result.Success<?>) result).getData();

                    matricula.setText(resultadop.getMatricula());
                    preciohora.setText(String.valueOf(resultadop.getPreciohora()));
                    marca.setText(resultadop.getMarca());
                    descripcion.setText(resultadop.getDescripcion());
                    bateria.setText(String.valueOf(resultadop.getBateria()));
                    fechaadq.setText(resultadop.getFecha());
                    tipocarnet.setText(resultadop.getTipocarnet());
                    auxunot.setText(String.valueOf(resultadop.getTamano()));
                    auxdost.setText(String.valueOf(resultadop.getRuedas()));
                    color = resultadop.getColor();
                    estado = resultadop.getEstado();
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
            switch (tipoVehiculo){
                case COCHE:
                    imgv.setImageResource(R.drawable.ic_baseline_directions_car_24);
                    break;
                case MOTO:
                    imgv.setImageResource(R.drawable.ic_baseline_two_wheeler_24);
                    break;
                case BICICLETA:
                    imgv.setImageResource(R.drawable.ic_baseline_pedal_bike_24);
                    break;
                case PATINETE:
                    imgv.setImageResource(R.drawable.ic_baseline_electric_scooter_24);
                    break;
                default:
                    imgv.setImageResource(R.drawable.ic_baseline_remove_circle_24);
            }

            switch (color){
                case "rojo":
                    imgv.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.rojo));
                    break;
                case "azul":
                    imgv.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.azul));
                    break;
                case "negro":
                    imgv.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.black));
                    break;
                case "blanco":
                    imgv.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.blanco));
                    break;
                case "amarillo":
                    imgv.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.amarillo));
                    break;
                case "verde":
                    imgv.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.verde));
                    break;
            }

            switch (estado){
                case "preparado":
                    estadoicono.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.verde));
                    break;
                default:
                    estadoicono.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.rojo));
            }
        }
    }
}