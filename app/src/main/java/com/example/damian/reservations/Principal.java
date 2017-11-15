package com.example.damian.reservations;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdaptadorReservas.OnCanchaClickListener {
    private  FloatingActionButton floatingActionButton;
    private Resources res;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private Bundle bundle;
    private Intent i;
    private String uid_usuario="";
    private String uid;
    private String email="";
    private TextView nombre_sesion,email_sesion;
    detalle_usuarios detalle;
    private RecyclerView listadoreservas;
    static ArrayList<Cancha_Reserva> reservas = new ArrayList<>();

    private AdaptadorReservas adapter;
    private LinearLayoutManager llm;
    private int icon_warning =0;
    private int icon_good =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        res = this.getResources();
        icon_warning =R.drawable.milky_25;
        icon_good =R.drawable.milky_25;
       // nombre_sesion = (TextView) findViewById(R.id.txtnombre_sesion);
        //email_sesion = (TextView) findViewById(R.id.txtcorreo_sesion);
       // nombre_sesion.setText("damian torres");
        i = getIntent();
       // Traer();

        MostrarReservas();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        // toast3.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(layout);
        toast3.show();

    }
    public void  Mostrar(View v){
        detalle = Model_usuarios.ObtenerDetallerPersona();
        System.out.println(detalle + " valor trae");
        if (detalle!=null){
            System.out.println(detalle.getNombres()+ detalle.getApellidos() + " valor nombre");
            System.out.println(email + " valor email");
           // nombre_sesion.setText(detalle.getNombres() + " " + detalle.getApellidos());
            //email_sesion.setText(email);
        }
    }
        public  void Traer(){
            boolean en_session= TraerId_sesion();
            System.out.println(en_session+ "");
            if(en_session) {
                Model_Estableciminetos.CargarEstablecimientos();
                Model_usuarios.TraerInfo(uid_usuario);

            }else{
                System.out.println("error en sesion");
            }
        }

        public void AgregarReserva(View v){

            Intent i = new Intent(Principal.this,Buscar_Cancha.class);
            startActivity(i);
        }
        public boolean TraerId_sesion(){

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                      email = user.getEmail();
                      uid_usuario = user.getUid();

            return  true;

                }
            return false;
        }

    public void MostrarReservas(){
        System.out.println("Tamaño viejo"+reservas.size());
        listadoreservas = (RecyclerView) findViewById(R.id.reservasusuario);
        reservas = Moldel_Reservas.getReservas();
        System.out.println("Tamaño nuevo"+reservas.size());
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorReservas(getApplicationContext(),reservas,this);
        listadoreservas.setLayoutManager(llm);
        listadoreservas.setAdapter(adapter);

    }

    @Override
    public void onCanchaClick(Cancha_Reserva p) {
        Intent i = new Intent(Principal.this,Detaller_Reserva.class);
        Bundle b = new Bundle();
        b.putString("establecimiento",p.getNombre_establecimiento());
        b.putString("cancha",p.getNumero_cancha());
        b.putString("id",p.getId_reserva());
        b.putString("direccion",p.getDireccion());
        b.putString("celular",p.getCelular());
        b.putString("fecha",p.getFecha());
        b.putIntegerArrayList("hora",p.getHora());
        i.putExtra("datos",b);
        startActivity(i);
    }



}

