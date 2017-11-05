package com.example.damian.reservations;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by DAMIAN on 5/11/2017.
 */

public class Model_Estableciminetos {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Establecimientos");

    public  static void GuardarEstablecimientos(Establecimientos establecimientos) {
        establecimientos.setId(tabla.push().getKey());
        tabla.child(establecimientos.getId()).setValue(establecimientos);


    }

    public static void GuardarManual(){
        Establecimientos a = new Establecimientos("1","El Tiburon","Murillo Toro #36 - 36");
        Establecimientos a1 = new Establecimientos("12","La Bombonera","Olaya Herrera #67 - 23");
        Establecimientos a2 = new Establecimientos("13","La Patiada","Cra 20 #301");
        Establecimientos a3 = new Establecimientos("11","La Masia","Parque el Bosque, CI. 94#54");
        Establecimientos a4 = new Establecimientos("12","El Monumental","88, Cra 48#31");
        GuardarEstablecimientos(a);
        GuardarEstablecimientos(a1);
        GuardarEstablecimientos(a2);
        GuardarEstablecimientos(a3);
        GuardarEstablecimientos(a4);
    }
}
