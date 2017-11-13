package com.example.damian.reservations;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DAMIAN on 4/11/2017.
 */

public class Moldel_Reservas {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Reservas");
    private static ArrayList<Cancha_Reserva> reservas = new ArrayList<Cancha_Reserva>();

    public static ArrayList<Cancha_Reserva> getReservas() {
        return reservas;
    }

    public static void setReservas() {
        Moldel_Reservas.reservas = new ArrayList<Cancha_Reserva>();
    }

    public  static void GuardarReserva(Reservas r) {
        r.setId(tabla.push().getKey());
        tabla.child(r.getId()).setValue(r);
    }

    public static void TraerReservas(final String id_usuario){
        System.out.println("llama");
        System.out.println("llama "+id_usuario);
        reservas = new ArrayList<Cancha_Reserva>();
        tabla.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Reservas r = snapshot.getValue(Reservas.class);
                        if (r.getId_usuario().equals(id_usuario.toString())){

                            ArrayList<String> nombre_establecimiento = Model_Estableciminetos.BuscarDatosEstablecimiento(r.getId_establecimiento());
                            System.out.println("llama "+ r.getId());
                              ArrayList<Integer> ho= new ArrayList<Integer>();
                              ho.add(3);

                          Cancha_Reserva a1 = new Cancha_Reserva(r.getId(),nombre_establecimiento.get(0),Model_Estableciminetos.BuscarNumCanchaId(r.getId_cancha(),r.getId_establecimiento()),r.getFecha(),r.getHora());
                          //  Cancha_Reserva a = new Cancha_Reserva("1","prueba","4","13/11/2017",ho);
                            reservas.add(a1);
                        }


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
