package com.example.damian.reservations;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    private ArrayList<Integer> horas_Seleccionadas;
    private TableLayout tabla;
    private String id_user = "";
    private   String fechaSelecionada="";
    ArrayList<Canchas> canchas;
    private int icon_warning =0;
    private int icon_good =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__reserva);
        res=this.getResources();
        icon_warning =R.drawable.milky_25;
        icon_good =R.drawable.icono_ok;
        horas_Seleccionadas = new ArrayList<>();
        fecha = (CalendarView)findViewById(R.id.txtcalendar);
       // CargarEstablecimientos();
        Linerinicial = (LinearLayout)findViewById(R.id.layinicial);
        linerlista = (LinearLayout)findViewById(R.id.laylista);
        tabla = (TableLayout) findViewById(R.id.tablalista);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        id_establecimiento = bundle.getString("id_establecimiento");
        id_cancha = bundle.getString("id_cancha");
        programacion = bundle.getIntegerArrayList("programacion");
        TraerId_sesion();
        Ocultar_Liner();


    }

public void Continuar(View v){

    tabla.removeAllViews();

    SimpleDateFormat formatoFecha = new SimpleDateFormat();
    formatoFecha.setTimeZone(TimeZone.getTimeZone("GMT-6"));
    formatoFecha.applyPattern("dd/MM/yyyy");
     fechaSelecionada = formatoFecha.format(fecha.getDate());

    //CrearFilaPrincipalTabla();
    for(int i = 0 ; i< programacion.size();i++){
        TableRow fila = new TableRow(this);
        TextView c1 = new TextView(this);
        TextView c2 = new TextView(this);

       final CheckBox c4 = new CheckBox(this);
        c4.setText(res.getString(R.string.reservar));
        c4.setId(programacion.get(i));
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c4.isChecked()){
                    if (!existe(c4.getId())){
                        horas_Seleccionadas.add(c4.getId());
                    }
                }else{
                    if (existe(c4.getId())){
                        horas_Seleccionadas.remove(existeIndex(c4.getId()));
                    }

                }


            }
        });

        c1.setText(Metodos.ConvertirHora(programacion.get(i)));
        c2.setText("                           ");


      //  c2.setBackgroundColor(res.getColor(R.color.colorAccent2,null));
        c2.setBackground(res.getDrawable(R.drawable.borde));
        c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      //  c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        c1.setTextSize(16);
        c2.setTextSize(16);
        c4.setTextSize(16);

        c1.setTextColor(res.getColor(R.color.colorPrimaryText,null));
        c2.setTextColor(res.getColor(R.color.colorPrimaryText,null));


        fila.addView(c1);
        fila.addView(c2);
        fila.addView(c4);

fila.setBackground(res.getDrawable(R.drawable.borde_fila));

        tabla.addView(fila);
    }
    Mostrar_Liner();
}
    public void Mensaje(int mensaje,int img){
        //Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
        Toast toast3 = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup ) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        ImageView icon =(ImageView)layout.findViewById(R.id.iconomensaje);
        icon.setImageResource(img);
        txtMsg.setText(mensaje);
         toast3.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
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
            //linerlista.setVisibility(View.VISIBLE);
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
    public void Regresar(View v){
        Ocultar_Liner();
    }
public void CrearFilaPrincipalTabla(){
    TableRow fila_principal = new TableRow(this);
    TextView f1 = new TextView(this);
    TextView f2 = new TextView(this);
    TextView f3 = new TextView(this);
    f1.setText(res.getString(R.string.hora));
    f2.setText(res.getString(R.string.estado));
    f3.setText(res.getString(R.string.opcion));
    f1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    f2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    f3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    f1.setTextColor(res.getColor(R.color.icons,null));
    f2.setTextColor(res.getColor(R.color.icons,null));
    f3.setTextColor(res.getColor(R.color.icons,null));
    f1.setTextSize(18);
    f2.setTextSize(18);
    f3.setTextSize(18);
    f1.setTypeface(null, Typeface.BOLD);
    f2.setTypeface(null, Typeface.BOLD);
    f3.setTypeface(null, Typeface.BOLD);
    fila_principal.addView(f1);
    fila_principal.addView(f2);
    fila_principal.addView(f3);
    fila_principal.setBackground(res.getDrawable(R.drawable.borde_fila_principal));
    tabla.addView(fila_principal);
}

    public void Cancelar(View v){
        String positivo,negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_cancelar));
        builder.setMessage(res.getString(R.string.texto_cancelar_reserva));
        positivo = res.getString(R.string.si_cancelar);
        negativo = res.getString(R.string.no_cancelar);



        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent r = new Intent(Agregar_Reserva.this,Principal.class);
                Bundle b = new Bundle();
                b.putString("id","");
                r.putExtra("datos",b);
                startActivity(r);
                onBackPressed();

            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    public boolean existe(int d){
        for (int j = 0; j < horas_Seleccionadas.size(); j++) {
            if (horas_Seleccionadas.get(j)==d){
                return true;
            }
        }
        return false;
    }
    public int existeIndex(int d){
        for (int j = 0; j < horas_Seleccionadas.size(); j++) {
            if (horas_Seleccionadas.get(j)==d){
                return j;
            }
        }
        return -1;
    }

    public void Reservar(View v){
        String positivo,negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.reservar));
        builder.setMessage(res.getString(R.string.texto_reservar));
        positivo = res.getString(R.string.si_cancelar);
        negativo = res.getString(R.string.no_cancelar);



        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              /*  Intent r = new Intent(Agregar_Reserva.this,Principal.class);
                Bundle b = new Bundle();
                b.putString("id","");
                r.putExtra("datos",b);
                startActivity(r);*/
              GuardarReserva();
                onBackPressed();

            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void TraerId_sesion(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            id_user=  user.getUid();
        }

    }

    public void GuardarReserva(){
        Moldel_Reservas.setReservas();
        Reservas a = new Reservas("1",id_cancha, id_user, fechaSelecionada, horas_Seleccionadas, true, id_establecimiento);
        Moldel_Reservas.GuardarReserva(a);
        Mensaje(R.string.ok_reserva, icon_good);
        Intent r = new Intent(Agregar_Reserva.this,Principal.class);
        Bundle b = new Bundle();
        b.putString("id","");
        r.putExtra("datos",b);
        startActivity(r);
    }
}
