package io.gothcorp.aicar.adapters.simFragmentAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.sim.Tramite;
import io.gothcorp.aicar.model.simit.Infraccion;

/**
 * Created by dixfricred on 05/01/2017.
 */

public class TramiteSimTableAdapter extends TableDataAdapter<Tramite> {
    private static final int TEXT_SIZE = 11;

    public TramiteSimTableAdapter(Context context, List<Tramite> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Tramite tramite = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(tramite.getNoRadicado().toString());
                break;
            case 1:
                renderedView = renderString(formatDate(tramite.getFechaSolicitud()));
                break;
            case 2:
                renderedView = renderString(tramite.getEstado());
                break;
            case 3:
                renderedView = renderString(tramite.getResultado());
                break;
        }

        return renderedView;
    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

}
