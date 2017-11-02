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
    }


    public void mostrar_liner(View button)
    {
        if (LinerUsuarios.getVisibility() == View.VISIBLE)
        {
            animar(false,LinerUsuarios);

        }
        if (LinerPersonas.getVisibility() == View.GONE)
        {
            animar(true,LinerPersonas);

        }
    }

    public void ocultar_liner(View button)
    {
        if (LinerPersonas.getVisibility() == View.VISIBLE)
        {
            animar(false,LinerPersonas);

        }
        if (LinerUsuarios.getVisibility() == View.GONE)
        {
            animar(true,LinerUsuarios);

        }

    }


    private void animar(boolean mostrar,LinearLayout layoutAnimado)
    {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        if (mostrar)
        {
            //desde la esquina inferior derecha a la superior izquierda
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        }
        else
        {    //desde la esquina superior izquierda a la esquina inferior derecha
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        }
        //duración en milisegundos
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

        layoutAnimado.setLayoutAnimation(controller);
        layoutAnimado.startAnimation(animation);
        if (mostrar){
            layoutAnimado.setVisibility(View.VISIBLE);
        }else{
            layoutAnimado.setVisibility(View.GONE);
        }
    }
}
