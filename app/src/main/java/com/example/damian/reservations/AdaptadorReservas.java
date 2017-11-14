package com.example.damian.reservations;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */
public class AdaptadorReservas extends RecyclerView.Adapter<AdaptadorReservas.ReservasViewHolder> {
    private ArrayList<Cancha_Reserva> reservas;
    private Resources res;
    private OnCanchaClickListener clickListener;

    public AdaptadorReservas(Context contexto, ArrayList<Cancha_Reserva> reservas, OnCanchaClickListener clickListener){
        this.reservas =reservas;
        res = contexto.getResources();
        this.clickListener=clickListener;
    }
    @Override
    public ReservasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_reserva,parent,false);
        return new ReservasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReservasViewHolder holder, int position) {

        final Cancha_Reserva p = reservas.get(position);
        holder.foto.setImageDrawable(ResourcesCompat.getDrawable(res,R.drawable.socc,null));
        holder.fecha_hora.setText(p.getFecha());
        holder.establecimiento.setText(p.getNombre_establecimiento());
        holder.num_cancha.setText("Cancha :"+ p.getNumero_cancha() +" - Hora: "+p.getHora_tras());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Principal.Cancelar(contexto,res);
                // Moldel_Reservas.eliminarRserva(p.getId_reserva());
                Moldel_Reservas.setId_cancelar(p.getId_reserva());
                Principal.Cancelar(res);//System.out.println("Eliminado");

            }
        });
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onCanchaClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public static class ReservasViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView establecimiento;
        private TextView fecha_hora,num_cancha;
        private Button eliminar;
        private View v;

        public ReservasViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = (ImageView)itemView.findViewById(R.id.imgFoto_res);
            fecha_hora = (TextView)itemView.findViewById(R.id.lblfecha_hora);
            establecimiento =(TextView)itemView.findViewById(R.id.lblestablecimiento_res);
            num_cancha =(TextView)itemView.findViewById(R.id.lbl_num_cancha);
            eliminar =(Button)itemView.findViewById(R.id.btneliminar);
        }
    }

    public interface OnCanchaClickListener {
        void onCanchaClick(Cancha_Reserva p);
    }
}