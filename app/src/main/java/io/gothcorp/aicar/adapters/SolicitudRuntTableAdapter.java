package io.gothcorp.aicar.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.runt.LicenciaRunt;
import io.gothcorp.aicar.model.runt.Solicitud;

/**
 * Created by dixfricred on 05/01/2017.
 */

public class SolicitudRuntTableAdapter extends TableDataAdapter<Solicitud> {
    private static final int TEXT_SIZE = 11;

    public SolicitudRuntTableAdapter(Context context, List<Solicitud> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Solicitud solicitud = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(solicitud.getNumeroSolicitud().toString());
                break;
            case 1:
                renderedView = renderString(formatDate(solicitud.getFechaSolicitud()));
                break;
            case 2:
                renderedView = renderString(solicitud.getEstado());
                break;
            case 3:
                renderedView = renderString(solicitud.getEntidad());
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
        // Create an instance of SimpleDateFormat used for formatting
// the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String reportDate = df.format(date);
        return reportDate;
    }
}
