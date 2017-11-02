package com.example.damian.reservations;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Login extends AppCompatActivity {
    private LinearLayout LinerLogin;
    private Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        res=this.getResources();
        LinerLogin = (LinearLayout)findViewById(R.id.layalogin);
        Mostrar_Liner();
    }

    public void Logear(View v){
        Intent i = new Intent(Login.this,Principal.class);startActivity(i);

    }
    public void Nuevo_usuario(View v){
        Intent i = new Intent(Login.this,Agrega_Usuario.class);startActivity(i);

    }
    public void Mostrar_Liner(){
        if (LinerLogin.getVisibility() == View.GONE){
            Metodos.animar(true,LinerLogin);

        }

    }
    public void Mostrar_Liner(View button)
    {
        Mostrar_Liner();
    }
}
