package com.example.damian.reservations;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by android on 02/11/2017.
 */

public class Model_usuarios {
    static FirebaseDatabase  database = FirebaseDatabase.getInstance();
    static DatabaseReference  tabla = database.getReference("DetallePersona");


    public Model_usuarios() {
    }

    public  static void GuardarDetalleUsuario(detalle_usuarios detalle) {
        detalle.setId(tabla.push().getKey());
    //    databaseReference.child(db).child(p.getId()).setValue(p);
        tabla.child(detalle.getId()).setValue(detalle);


    }
    public static String  GenereLlave(){
      return  tabla.push().getKey();
    }
    public static void ModificarLlaveID(String id_detalle,String id_usuario) {
        tabla.child(id_detalle).child("id_usuarios").setValue(id_usuario);
    }
    


}
