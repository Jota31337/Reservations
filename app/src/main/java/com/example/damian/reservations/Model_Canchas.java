package com.example.damian.reservations;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by DAMIAN on 5/11/2017.
 */

public class Model_Canchas {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference tabla = database.getReference("Canchas");

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
}
