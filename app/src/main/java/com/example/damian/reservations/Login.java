package com.example.damian.reservations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Logear(View v){
        Intent i = new Intent(Login.this,Principal.class);startActivity(i);

    }
    public void Nuevo_usuario(View v){
        Intent i = new Intent(Login.this,Agrega_Usuario.class);startActivity(i);

    }
}
