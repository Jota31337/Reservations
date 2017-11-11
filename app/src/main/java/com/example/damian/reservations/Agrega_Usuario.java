package com.example.damian.reservations;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
    private Spinner sexo;
    private ArrayAdapter<String> adapter;
    private String[] opc;
    private EditText txtCelular;
    private EditText txtNombre;
    private EditText txtApellido,txtnacimiento,txtcorreo;
   String uid = "";
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
        txtCelular = (EditText)findViewById(R.id.txtcelular);
        txtNombre = (EditText)findViewById(R.id.txtnombre);
        txtApellido=(EditText)findViewById(R.id.txtapellidos);
        txtcorreo=(EditText)findViewById(R.id.txtcorreo);
        txtnacimiento=(EditText)findViewById(R.id.txtnacimiento);
        sexo = (Spinner)findViewById(R.id.cbxsexo);
        opc = res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        sexo.setAdapter(adapter);
        Mostrar_Liner();
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
    public void Cancelar(View v){
        String positivo,negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_cancelar));
        builder.setMessage(res.getString(R.string.texto_cancelar_agregar_usuario));
        positivo = res.getString(R.string.si_cancelar);
        negativo = res.getString(R.string.no_cancelar);



        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent r = new Intent(Agrega_Usuario.this,Login.class);
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

    public void RegistrarUsuario(final String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(Agrega_Usuario.this, R.string.cuenta_creada, Toast.LENGTH_LONG).show();
                    /*Intent i = new Intent(Agrega_Usuario.this,Login.class);
                    startActivity(i);
                    Toast.makeText(Agrega_Usuario.this, R.string.cuenta_creada, Toast.LENGTH_LONG).show();
*/
                    detalle_usuarios d = new detalle_usuarios(txtNombre.getText().toString(), txtApellido.getText().toString(), txtCelular.getText().toString(), txtcorreo.getText().toString(), txtnacimiento.getText().toString(),sexo.getSelectedItemPosition(),"0",0);
                    d.Guardar();
                    uid=d.getId();
                    signIn(usuario.getText().toString(),contrasena.getText().toString());
                    Mensaje(R.string.cuenta_creada);

                }else{
                    String m =task.getException().getMessage();
                 int res=  Metodos.TraducirMensaje(m);
                    if (res>0)Mensaje(res);
                    else Toast.makeText(Agrega_Usuario.this, m, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void RegistrarUsuario(View v){
        RegistrarUsuario(usuario.getText().toString(),contrasena.getText().toString());
    }
     public  void Continuar_Registro(View v){
    if (Metodos.ValidarCampo(usuario)){
        Mensaje(R.string.ingrese_usuario);
    }else if (Metodos.ValidarCampo(contrasena)){
        Mensaje(R.string.ingrese_contrasena);
    }else if (Metodos.ValidarCampo(rcontrasena)){
        Mensaje(R.string.ingrese_re_contrasena);
    }else if (!contrasena.getText().toString().equals(rcontrasena.getText().toString())){
        Mensaje(R.string.contrasena_diferentes);
    }else{
        RegistrarUsuario(usuario.getText().toString(),contrasena.getText().toString());

    }
}
  public void RegistrarPersona(View v) {
      if (Metodos.ValidarCampo(txtNombre)) {
          Mensaje(R.string.errado_nombre);
      } else if (Metodos.ValidarCampo(txtApellido)) {
          Mensaje(R.string.errado_apellidos);
      }else if (Metodos.ValidarCampo(txtCelular)) {
          Mensaje(R.string.errado_celular);
      }else if (Metodos.ValidarCampo(txtcorreo)) {
          Mensaje(R.string.errado_correo);
      }else if (Metodos.ValidarCampo(txtnacimiento)) {
          Mensaje(R.string.errado_nacimiento);
      }else{
          Ocultar_Liner();
      }

  }

  public  void Regresar(View v){
      Mostrar_Liner();
  }
    public void signIn(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    Intent i = new Intent(Agrega_Usuario.this,Principal.class);
                    Bundle b = new Bundle();
                    b.putString("id",uid);
                    i.putExtra("datos",b);
                    startActivity(i);
                    finish();
                    Toast.makeText(Agrega_Usuario.this, R.string.bienvenido, Toast.LENGTH_LONG).show();
                }else{
                    String m =task.getException().getMessage();

                    int res=  Metodos.TraducirMensaje(m);
                    if (res>0)Toast.makeText(Agrega_Usuario.this, res, Toast.LENGTH_LONG).show();
                    else Toast.makeText(Agrega_Usuario.this, m, Toast.LENGTH_LONG).show();

                }
            }
        });
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
