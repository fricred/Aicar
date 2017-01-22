package io.gothcorp.aicar.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.runt.LicenciaRunt;


/**
 * Adaptador para la tabla de las  licencia del servicio RUNT.
 * @author Jeisson Huerfano
 */

public class LicenciaRuntTableAdapter extends TableDataAdapter<LicenciaRunt> {
    private static final int TEXT_SIZE = 11;

    public LicenciaRuntTableAdapter(Context context, List<LicenciaRunt> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        LicenciaRunt licenciaRunt = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(licenciaRunt.getNumeroLicencia().toString());
                break;
            case 1:
                renderedView = renderString(licenciaRunt.getOtExpide());
                break;
            case 2:
                renderedView = renderString(licenciaRunt.getEstadoLicencia());
                break;
            case 3:
                renderedView = renderString(licenciaRunt.getFechaExpedicion());
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
