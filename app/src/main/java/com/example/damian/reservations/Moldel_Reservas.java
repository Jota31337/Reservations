package com.example.damian.reservations;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by DAMIAN on 4/11/2017.
 */

public class Moldel_Reservas {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Reservas");

    public  static void GuardarReserva(Reservas r) {
        r.setId(tabla.push().getKey());
        tabla.child(r.getId()).setValue(r);
    }


}
