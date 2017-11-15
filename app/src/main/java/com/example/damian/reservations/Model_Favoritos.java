package com.example.damian.reservations;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by android on 15/11/2017.
 */

public class Model_Favoritos {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Favoritos");
    static ArrayList<Favoritos> misfavoritos = new ArrayList<>();
    static ArrayList<Favoritos> favoritosGenral = new ArrayList<>();
    public  static void GuardarFavorito(Favoritos f) {
        f.setId(tabla.push().getKey());
        tabla.child(f.getId()).setValue(f);
    }
    public static void TraerFavoritos(final String id_usuario){
        misfavoritos = new ArrayList<>();
        favoritosGenral = new ArrayList<>();
        tabla.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Favoritos f = snapshot.getValue(Favoritos.class);
                        favoritosGenral.add(f);
                        if (f.getId_usuario().toString().equals(id_usuario.toString())){
                            misfavoritos.add(f);
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
