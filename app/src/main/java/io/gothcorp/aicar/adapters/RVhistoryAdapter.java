package io.gothcorp.aicar.adapters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intrusoft.squint.DiagonalView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.gothcorp.aicar.R;
import io.gothcorp.aicar.model.Historia;
import io.gothcorp.aicar.ui.activities.Home;


/**
 * Adaptador para la lista que muestra la historia de vehiculos consultados, configura la vista de la placa, foto y fecha de captura
 *
 * @author Jeisson Huerfano
 */

public class RVhistoryAdapter extends RecyclerView.Adapter<RVhistoryAdapter.HistoryViewHolder> {
    private List<Historia> lista;

    public RVhistoryAdapter(List<Historia> lista) {
        Collections.sort(lista, new Comparator<Historia>() {
            public int compare(Historia o1, Historia o2) {
                return o1.getFecha().after(o2.getFecha()) ? -1 : 1;
            }
        });
        this.lista = lista;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history, parent, false);
        HistoryViewHolder pvh = new HistoryViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int i) {
        holder.cardTitle.setText(lista.get(i).getPlaca());
        holder.accurace.setText("Confianza : " + lista.get(i).getAccurace() + " %");


        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(lista.get(i).getFecha());
        holder.fecha.setText(reportDate);
        File destination = new File(lista.get(i).getPhothoPath());
        if (destination.exists()) {
            Picasso.with(holder.personPhoto.getContext()).load(destination).fit().centerCrop().into(holder.personPhoto);

        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardTitle;
        TextView accurace;
        TextView fecha;
        DiagonalView personPhoto;
        View view;

        HistoryViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            cv = (CardView) itemView.findViewById(R.id.cv);
            cardTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            accurace = (TextView) itemView.findViewById(R.id.accurace);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            personPhoto = (DiagonalView) itemView.findViewById(R.id.photo_car);
        }
    }
}
