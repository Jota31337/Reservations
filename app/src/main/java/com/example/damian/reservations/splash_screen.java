package com.example.damian.reservations;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class splash_screen extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 2000;
    private Bundle bundle;
    private String uid_usuario="";
    private String uid;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = getIntent();
        Traer();
        setContentView(R.layout.activity_splash_screen);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Moldel_Reservas.TraerReservas(uid_usuario);
                Intent i = new Intent(splash_screen.this,Principal.class);
                Bundle b = new Bundle();
                startActivity(i);
                finish();


                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    public void CompletarRegistroPersonaID_USUARIO(){
        bundle = i.getBundleExtra("datos");
        uid = bundle.getString("id");
        if (uid.length()!=0) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {

                uid_usuario = user.getUid();
                Model_usuarios.ModificarLlaveID(uid, uid_usuario);


            }
        }
    }
    public boolean TraerId_sesion(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid_usuario = user.getUid();

            return  true;

        }
        return false;
    }

    public  void Traer(){
        boolean en_session= TraerId_sesion();
        System.out.println(en_session+ "");
        if(en_session) {
            Model_Estableciminetos.CargarEstablecimientos();
            Model_usuarios.TraerInfo(uid_usuario);

            CompletarRegistroPersonaID_USUARIO();

        }else{
            System.out.println("error en sesion");
        }
    }
    }

