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

    private Spinner establecimientos_sp,canchas_sp;
    private Resources res;
    private String [] est,can;
    private boolean sw =true;
    private CalendarView fecha;
    private LinearLayout Linerinicial,linerlista;
    ArrayList<Establecimientos> establecimientos;
    ArrayList<Canchas> canchas;
    ArrayList<Canchas> canchas_bus ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__reserva);
        res=this.getResources();
        establecimientos_sp = (Spinner)findViewById(R.id.cbxestablecimientos);

        canchas_sp = (Spinner)findViewById(R.id.cbxcanchas);
        fecha = (CalendarView)findViewById(R.id.txtcalendar);
       // CargarEstablecimientos();
        Linerinicial = (LinearLayout)findViewById(R.id.layinicial);
        linerlista = (LinearLayout)findViewById(R.id.laylista);
        Ocultar_Liner();
        canchas=Model_Canchas.getCanchas();

    }

    /*
public  void CargarEstablecimientos(){

     establecimientos= Model_Estableciminetos.getEstablecimientos();
    est = new String[establecimientos.size()];

    for (int i = 0; i <establecimientos.size() ; i++) {
        est[i]=establecimientos.get(i).getNombre();

    }
    ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,est);
    establecimientos_sp.setAdapter(adapter);
    establecimientos_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
        {

            String idestablecimiento = establecimientos.get(pos).getId();
            CargarCanchas(idestablecimiento);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {    }
    });


}
    public  void CargarCanchas(String establecimiento){
        canchas_bus = new ArrayList<>();
    for (int i = 0; i <canchas.size() ; i++) {
           if (canchas.get(i).getId_establecimiento().equals(establecimiento))
               canchas_bus.add(canchas.get(i));
        }
        if (canchas_bus.size()>0) {
            can = new String[canchas_bus.size()];
            for (int i = 0; i < canchas_bus.size(); i++) {
                can[i] = "Cancha " + canchas_bus.get(i).getNumCancha() + ", Tamaño " + canchas_bus.get(i).getTamano();

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, can);
            canchas_sp.setAdapter(adapter);
        }else{
            Mensaje(R.string.sin_canchas);
        }
    }

    */
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
