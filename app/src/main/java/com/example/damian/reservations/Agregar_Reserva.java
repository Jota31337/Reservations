package com.example.damian.reservations;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class Agregar_Reserva extends AppCompatActivity  {
    private Resources res;
    private CalendarView fecha;
    private LinearLayout Linerinicial,linerlista;
    private Bundle bundle;
    private Intent i;
    private String id_establecimiento,id_cancha;
    private ArrayList<Integer> programacion;

    ArrayList<Canchas> canchas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__reserva);
        res=this.getResources();

        fecha = (CalendarView)findViewById(R.id.txtcalendar);
       // CargarEstablecimientos();
        Linerinicial = (LinearLayout)findViewById(R.id.layinicial);
        linerlista = (LinearLayout)findViewById(R.id.laylista);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        id_establecimiento = bundle.getString("id_establecimiento");
        id_cancha = bundle.getString("id_cancha");
        programacion = bundle.getIntegerArrayList("programacion");
        Ocultar_Liner();


    }

public void Continuar(View v){
    SimpleDateFormat formatoFecha = new SimpleDateFormat();
    formatoFecha.setTimeZone(TimeZone.getTimeZone("GMT-6"));
    formatoFecha.applyPattern("dd/MM/yyyy");
    String fechaRespuesta = formatoFecha.format(fecha.getDate());
    Toast.makeText(Agregar_Reserva.this,""+fechaRespuesta, Toast.LENGTH_LONG).show();
    Mostrar_Liner();
}
    public void Mensaje(int mensaje){
        //Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
        Toast toast3 = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(mensaje);
        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(layout);
        toast3.show();
    }

    public void Mostrar_Liner(){
        if (Linerinicial.getVisibility() == View.VISIBLE)
        {
            Metodos.animar(false,Linerinicial);

        }
        if (linerlista.getVisibility() == View.GONE)
        {
            Metodos.animar(true,linerlista);

        }
    }
    public void Ocultar_Liner(){
        if (linerlista.getVisibility() == View.VISIBLE)
        {
            Metodos.animar(false,linerlista);

        }
        if (Linerinicial.getVisibility() == View.GONE)
        {
            Metodos.animar(true,Linerinicial);

        }
    }

}
