package com.example.damian.reservations;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Agregar_Reserva extends AppCompatActivity {

    private Spinner establecimientos_sp;
    private Resources res;
    private String [] est;
    private boolean sw =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__reserva);
        res=this.getResources();
        establecimientos_sp = (Spinner)findViewById(R.id.cbxestablecimientos);
        CargarEstablecimientos();


    }
public  void CargarEstablecimientos(){

   ArrayList<Establecimientos> establecimientos= Model_Estableciminetos.getEstablecimientos();
    est = new String[establecimientos.size()+1];
    est[0]="Seleccione Establecimiento";
    for (int i = 0; i <establecimientos.size() ; i++) {
        est[i+1]=establecimientos.get(i).getNombre();

    }
    ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,est);
    establecimientos_sp.setAdapter(adapter);

}
    
}
