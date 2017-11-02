package com.example.damian.reservations;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Agrega_Usuario extends AppCompatActivity {

    private Resources res;
    private LinearLayout LinerPersonas,LinerUsuarios;
    private EditText usuario,contrasena,rcontrasena;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega__usuario);
        res=this.getResources();
        LinerPersonas = (LinearLayout)findViewById(R.id.laydatospersona);
        LinerUsuarios = (LinearLayout)findViewById(R.id.laydatosusuario);
        usuario = (EditText) findViewById(R.id.txtusuario);
        contrasena = (EditText) findViewById(R.id.txtcontrasena);
        rcontrasena = (EditText) findViewById(R.id.txtrecontrasena);
        Ocultar_Liner();
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            }
        };
    }

public void Mostrar_Liner(){
    if (LinerUsuarios.getVisibility() == View.VISIBLE)
    {
        Metodos.animar(false,LinerUsuarios);

    }
    if (LinerPersonas.getVisibility() == View.GONE)
    {
        Metodos.animar(true,LinerPersonas);

    }
}
public void Ocultar_Liner(){
    if (LinerPersonas.getVisibility() == View.VISIBLE)
    {
        Metodos.animar(false,LinerPersonas);

    }
    if (LinerUsuarios.getVisibility() == View.GONE)
    {
        Metodos.animar(true,LinerUsuarios);

    }
}

    public void Mostrar_Liner(View button)
    {
        Mostrar_Liner();
    }

    public void Ocultar_Liner(View button)
    {
        Ocultar_Liner();

    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    public void RegistrarUsuario(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(Agrega_Usuario.this, R.string.cuenta_creada, Toast.LENGTH_LONG).show();

                }else{
                    String m =task.getException().getMessage();
                   // Toast.makeText(Agrega_Usuario.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                     TraducirMensaje(m);
                }
            }
        });
    }
    public void RegistrarUsuario(View v){
        RegistrarUsuario(usuario.getText().toString(),contrasena.getText().toString());
    }
     public  void Continuar_Registro(View v){
    if (ValidarCampo(usuario)){
        Mensaje(R.string.ingrese_usuario);
    }else if (ValidarCampo(contrasena)){
        Mensaje(R.string.ingrese_contrasena);
    }else if (ValidarCampo(rcontrasena)){
        Mensaje(R.string.ingrese_re_contrasena);
    }else if (!contrasena.getText().toString().equals(rcontrasena.getText().toString())){
        Mensaje(R.string.contrasena_diferentes);
    }else{
       RegistrarUsuario(usuario.getText().toString(),contrasena.getText().toString());
        //Mostrar_Liner();

    }
}

public boolean ValidarCampo(EditText campo){
    if (campo.getText().toString().length()==0){
        campo.requestFocus();
return true;
    }
    return false;
}

public void Mensaje(int mensaje){
    Toast.makeText(Agrega_Usuario.this, mensaje, Toast.LENGTH_LONG).show();
}
public void TraducirMensaje(String mensaje){
if (mensaje.equalsIgnoreCase("The email address is badly formatted.")){
    Toast.makeText(Agrega_Usuario.this, R.string.email_errado, Toast.LENGTH_LONG).show();
}else if ((mensaje.equalsIgnoreCase("The given password is invalid. [ Password should be at least 6 characters ]"))){
    Toast.makeText(Agrega_Usuario.this, R.string.contrasena_errado, Toast.LENGTH_LONG).show();
}else if ((mensaje.equalsIgnoreCase("The email address is already in use by another account."))){
    Toast.makeText(Agrega_Usuario.this, R.string.cuenta_exite, Toast.LENGTH_LONG).show();
}else{
    Toast.makeText(Agrega_Usuario.this, mensaje, Toast.LENGTH_LONG).show();
}
}
}
