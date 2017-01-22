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
import io.gothcorp.aicar.model.runt.DetalleLicencia;

/**
 * Adaptador para la tabla de los detalles de cada licencia del servicio RUNT.
 * @author Jeisson Huerfano
 */
public class DetalleLicenciaRuntTableAdapter extends TableDataAdapter<DetalleLicencia> {

    private static final int TEXT_SIZE = 11;

    public DetalleLicenciaRuntTableAdapter(Context context, List<DetalleLicencia> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        DetalleLicencia detalleLicencia = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(detalleLicencia.getCategoria());
                break;
            case 1:
                renderedView = renderString(detalleLicencia.getFechaExpedicion());
                break;
            case 2:
                renderedView = renderString(detalleLicencia.getFechaVencimiento());
                break;
            case 3:
                renderedView = renderString(detalleLicencia.getFechaVencimientoExamen());
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


}
