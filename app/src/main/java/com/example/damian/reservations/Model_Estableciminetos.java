package com.example.damian.reservations;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DAMIAN on 5/11/2017.
 */

public class Model_Estableciminetos {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Establecimientos");
    static ArrayList<Establecimientos> establecimientos = new ArrayList<Establecimientos>();
    public  static void GuardarEstablecimientos(Establecimientos establecimientos) {
        establecimientos.setId(tabla.push().getKey());
        tabla.child(establecimientos.getId()).setValue(establecimientos);


    }

    public static ArrayList<Establecimientos> getEstablecimientos() {
        return establecimientos;
    }

    public static void setEstablecimientos(ArrayList<Establecimientos> establecimientos) {
        Model_Estableciminetos.establecimientos = establecimientos;
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

    public static void CargarEstablecimientos(){
        tabla.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Establecimientos c = snapshot.getValue(Establecimientos.class);
                        establecimientos.add(c);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println("error" + databaseError.toException());
            }
        });


    }
}
