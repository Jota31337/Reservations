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

public class Model_Canchas {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Canchas");
    static ArrayList<Canchas> canchas = new ArrayList<Canchas>();

    public static ArrayList<Canchas> getCanchas() {
        return canchas;
    }

    public  static void GuardarCanchas(Canchas canchas) {
        String id =tabla.push().getKey();
        canchas.setId(id);
        canchas.setId_foto(id);
        tabla.child(canchas.getId()).setValue(canchas);
    }


    public static void GuardarManual(){
        Canchas a = new Canchas("1","-KyBib3WqehgrGSSIM67",1,8,"2");
        Canchas a1 = new Canchas("1","-KyBib3WqehgrGSSIM67",2,6,"2");
        Canchas a2 = new Canchas("1","-KyBib3WqehgrGSSIM67",3,7,"2");

        Canchas a3 = new Canchas("1","-KyBib4P2pxTHz0MKtdZ",1,6,"2");
        Canchas a4 = new Canchas("1","-KyBib4P2pxTHz0MKtdZ",2,9,"2");

        Canchas a5 = new Canchas("1","-KyBib4P2pxTHz0MKtd_",1,8,"2");
        Canchas a6 = new Canchas("1","-KyBib4P2pxTHz0MKtd_",2,8,"2");


        Canchas a7 = new Canchas("1","-KyBib4Ra6YRYLZXW9yV",1,6,"2");
        Canchas a8 = new Canchas("1","-KyBib4SDkniidsFFWfz",1,9,"2");
        GuardarCanchas(a);
        GuardarCanchas(a1);
        GuardarCanchas(a2);
        GuardarCanchas(a3);
        GuardarCanchas(a4);
        GuardarCanchas(a5);
        GuardarCanchas(a6);
        GuardarCanchas(a7);
        GuardarCanchas(a8);
    }



    public static void CargarCanchas(){
    tabla.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Canchas c = snapshot.getValue(Canchas.class);
                        canchas.add(c);
                     }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println("error" + databaseError.toException());
            }
        });


    }

    public static ArrayList<Cancha_Establecimiento>  BuscarCancha(String buscar,int metodo){
        ArrayList<Cancha_Establecimiento>   canchas_bus = new ArrayList<>();
        if (metodo==1){
            buscar = Model_Estableciminetos.BuscarIDEstablecimiento(buscar);
            if (buscar.toString().length()==0){
                return canchas_bus;
            }
        }

        for (int i = 0; i <canchas.size() ; i++) {

            if (metodo==1) {


                if (canchas.get(i).getId_establecimiento().equals(buscar)){
                    Canchas b = canchas.get(i);
                    //(String id_cacha, String nombre_establecimiento, String direccion, String celular, String tamano)
                    ArrayList<String> datos_establecimiento = Model_Estableciminetos.BuscarDatosEstablecimiento(b.getId_establecimiento());
                    Cancha_Establecimiento a = new Cancha_Establecimiento(b.getId(),datos_establecimiento.get(0),datos_establecimiento.get(1),"30455442",b.getTamano()+"");
                    canchas_bus.add(a);
                }
            }else{
                int x= Integer.parseInt(buscar);
                if (canchas.get(i).getTamano()==x){
                    Canchas b = canchas.get(i);
                    ArrayList<String> datos_establecimiento = Model_Estableciminetos.BuscarDatosEstablecimiento(b.getId_establecimiento());
                    Cancha_Establecimiento a = new Cancha_Establecimiento(b.getId(),datos_establecimiento.get(0),datos_establecimiento.get(1),"30455442",b.getTamano()+"");
                    canchas_bus.add(a);
                }
            }

        }
        return canchas_bus;
    }
}
