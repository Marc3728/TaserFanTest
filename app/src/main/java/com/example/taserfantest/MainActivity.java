package com.example.taserfantest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taserfantest.API.API;
import com.example.taserfantest.API.Connector;
import com.example.taserfantest.API.Result;
import com.example.taserfantest.base.BaseActivity;
import com.example.taserfantest.base.CallInterface;

public class MainActivity extends BaseActivity implements CallInterface {

    Button buttonlogin;
    TextView correo;
    TextView contrasena;
    Result<Empleado> result;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = findViewById(R.id.username);
        contrasena = findViewById(R.id.password);
        buttonlogin = findViewById(R.id.login);



        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                executeCall(MainActivity.this);

            }
        });
    }

    @Override
    public void doInBackground() {
        API.Routes.URL = "http://"+GestionPreferencias.getIP(this)+":"+GestionPreferencias.getPORT(this);
        result = Connector.getConector().post(Empleado.class,new Empleado("null","null","null",correo.getText().toString(),contrasena.getText().toString()),"/authemp");
    }

    @Override
    public void doInUI() {
        if (result instanceof Result.Success){
            LoggedInUserRepository.getInstance().login((Empleado) ((Result.Success<?>) result).getData());
            Result.Success<Empleado> resultado = (Result.Success<Empleado>) result;
            Intent intent = new Intent(getApplicationContext(),EmpleadoLoggedd.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.configuration):
                Intent intentPreferenciasActivity = new Intent(this, PreferencesActivity.class);
                startActivity(intentPreferenciasActivity);
                return true;
            case (R.id.exit):
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}