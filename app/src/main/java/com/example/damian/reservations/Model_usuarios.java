package com.example.damian.reservations;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by android on 02/11/2017.
 */

public class Model_usuarios {
    static FirebaseDatabase  database = FirebaseDatabase.getInstance();
    static DatabaseReference  tabla = database.getReference("DetallePersona");
    //static ArrayList<detalle_usuarios> detalle = new ArrayList<detalle_usuarios>();
static detalle_usuarios detalle_persona ;
    public Model_usuarios() {
    }

    public static detalle_usuarios ObtenerDetallerPersona(){

        return detalle_persona;
    }

    public  static void GuardarDetalleUsuario(detalle_usuarios detalle) {
        detalle.setId(tabla.push().getKey());
        tabla.child(detalle.getId()).setValue(detalle);


    }
    public static String  GenereLlave(){
      return  tabla.push().getKey();
    }
    public static void ModificarLlaveID(String id_detalle,String id_usuario) {
        tabla.child(id_detalle).child("id_usuarios").setValue(id_usuario);
    }
public static void TraerInfo(final String id_usuario){


    tabla.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                  //  System.out.println(snapshot.getValue()+"valor");
                    detalle_usuarios user = snapshot.getValue(detalle_usuarios.class);
                 if (user.getId_usuarios().toString().equals(id_usuario.toString())){
                    detalle_persona =user;
                    // detalle.add(user);
                     System.out.println("encontro valor");
                     System.out.println(detalle_persona.getNombres() +"valor");
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
