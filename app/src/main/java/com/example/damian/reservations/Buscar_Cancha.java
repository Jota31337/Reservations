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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Buscar_Cancha extends AppCompatActivity {

    private String[] opc_busqueda;
    private Spinner busqueda;
    private ArrayAdapter<String> adapter;
    private Resources res;
    private String [] est,can;
    private boolean sw =true;
    private LinearLayout Linerinicial;
private EditText dato ;
    static ArrayList<Cancha_Establecimiento> canchas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__cancha);
        res=this.getResources();
        busqueda = (Spinner)findViewById(R.id.cbxbusqueda);
        Linerinicial = (LinearLayout)findViewById(R.id.layinicial_buscar);
        dato = (EditText) findViewById(R.id.txtdato);
        opc_busqueda = res.getStringArray(R.array.op_busqueda);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc_busqueda);
        busqueda.setAdapter(adapter);
        Mostrar_Liner();
    }

        public void Mostrar_Liner(){

            if (Linerinicial.getVisibility() == View.GONE)
            {
                Metodos.animar(true,Linerinicial);

            }
        }
        public void BuscarCanchas(View v){
             int op=busqueda.getSelectedItemPosition();
            if (op == 0) {
              Mensaje(R.string.metodo_busqueda);
            }else if (dato.getText().toString().length()==0){
                Mensaje(R.string.dato_busqueda);
            } else{
                canchas = Model_Canchas.BuscarCancha(dato.getText().toString(), op);
            if (canchas.isEmpty()){
                Mensaje(R.string.sin_resultados);
            }else {
                Intent i = new Intent(Buscar_Cancha.this, Resultado_Busqueda.class);
                startActivity(i);
            }
            }
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
}
