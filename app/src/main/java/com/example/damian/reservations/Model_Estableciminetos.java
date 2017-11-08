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
        Establecimientos a = new Establecimientos("1","El Tiburon","Murillo Toro #36 - 36","325689541");
        Establecimientos a1 = new Establecimientos("12","La Bombonera","Olaya Herrera #67 - 23","325689541");
        Establecimientos a2 = new Establecimientos("13","La Patiada","Cra 20 #301","325689541");
        Establecimientos a3 = new Establecimientos("11","La Masia","Parque el Bosque, CI. 94#54","325689541");
        Establecimientos a4 = new Establecimientos("12","El Monumental","88, Cra 48#31","325689541");
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

    public  static ArrayList<String> BuscarDatosEstablecimiento(String bus){


        ArrayList<String> x = new ArrayList<>();

        for (int i = 0; i <establecimientos.size() ; i++) {

            if (establecimientos.get(i).getId().equals(bus)){
                x.add(establecimientos.get(i).getNombre());
                x.add(establecimientos.get(i).getDireccion());
                x.add(establecimientos.get(i).getCelular());
                return x;
            }

        }


        return x;
    }
    public  static String BuscarIDEstablecimiento(String bus){


        ArrayList<String> x = new ArrayList<>();

        for (int i = 0; i <establecimientos.size() ; i++) {

            if (establecimientos.get(i).getNombre().equalsIgnoreCase(bus)){
              return establecimientos.get(i).getId();
            }

        }


        return "";
    }
}
