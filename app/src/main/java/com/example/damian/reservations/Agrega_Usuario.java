package com.example.damian.reservations;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Agrega_Usuario extends AppCompatActivity {

    private Resources res;
    private LinearLayout LinerPersonas,LinerUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega__usuario);
        res=this.getResources();
        LinerPersonas = (LinearLayout)findViewById(R.id.laydatospersona);
        LinerUsuarios = (LinearLayout)findViewById(R.id.laydatosusuario);
        Mostrar_Liner();
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



}
