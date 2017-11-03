package com.example.damian.reservations;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private LinearLayout LinerLogin;
    private Resources res;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        res=this.getResources();
        LinerLogin = (LinearLayout)findViewById(R.id.layalogin);
        Mostrar_Liner();
        email= (EditText)findViewById(R.id.txtemaillogin);
        password = (EditText)findViewById(R.id.txtcontrasenalogin);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            }
        };
    }

    public void Logear(View v){
        if (Metodos.ValidarCampo(email)){
            Mensaje(R.string.ingrese_usuario);
        }else  if (Metodos.ValidarCampo(password)){
            Mensaje(R.string.ingrese_contrasena);
        }else{
        signIn(email.getText().toString(),password.getText().toString());
    }
    }
    public void signIn(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent i = new Intent(Login.this,Principal.class);
                    Bundle b = new Bundle();
                    b.putString("id","");
                    i.putExtra("datos",b);
                    startActivity(i);
                    finish();
                    Toast.makeText(Login.this, R.string.bienvenido, Toast.LENGTH_LONG).show();
                }else{
                    String m =task.getException().getMessage();

                   int res=  Metodos.TraducirMensaje(m);
                    if (res>0)Toast.makeText(Login.this, res, Toast.LENGTH_LONG).show();
                    else Toast.makeText(Login.this, m, Toast.LENGTH_LONG).show();

                }
            }
        });
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
    public void Mensaje(int mensaje){
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
