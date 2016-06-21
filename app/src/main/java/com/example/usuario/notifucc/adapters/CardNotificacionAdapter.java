package com.example.usuario.notifucc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.notifucc.R;
import com.example.usuario.notifucc.servidor.Notificacion;

import java.util.List;

/**
 * Created by Carlos on May 2016.
 */
public class CardNotificacionAdapter extends RecyclerView.Adapter<CardNotificacionAdapter.CartaViewHolder> {

    private final Context context;
    private List<Notificacion> items;

    public CardNotificacionAdapter(Context context, List<Notificacion> items) {
        this.context = context;
        this.items = items;
    }

    static class CartaViewHolder extends RecyclerView.ViewHolder{
        protected TextView tvGrupo;
        protected TextView tvTitulo;
        protected TextView tvEmisor;


        public CartaViewHolder(View itemView) {
            super(itemView);
            this.tvGrupo = (TextView)itemView.findViewById(R.id.tv_grupo);
            this.tvTitulo = (TextView)itemView.findViewById(R.id.tv_titulo);
            this.tvEmisor = (TextView)itemView.findViewById(R.id.tv_emisor);
        }
    }

    @Override
    public CartaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_notificacion, parent, false);
        return new CartaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CartaViewHolder holder, int position) {
        Notificacion item = items.get(position);
        holder.itemView.setTag(item);

        holder.tvGrupo.setText(item.getGrupo());
        holder.tvTitulo.setText(item.getTitulo());
        holder.tvEmisor.setText(item.getEmisor());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
