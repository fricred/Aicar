package io.gothcorp.aicar.adapters.simitFragmentAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.runt.LicenciaRunt;
import io.gothcorp.aicar.model.simit.LicenciaSimit;

/**
 * Adaptador para la tabla de las licencias del servicio SIMIT.
 * @author Jeisson Huerfano
 */

public class LicenciaSimitTableAdapter extends TableDataAdapter<LicenciaSimit> {
    private static final int TEXT_SIZE = 11;

    public LicenciaSimitTableAdapter(Context context, List<LicenciaSimit> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        LicenciaSimit licenciaSimit = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(licenciaSimit.getLicenciaId().toString());
                break;
            case 1:
                renderedView = renderString(licenciaSimit.getFechaVencimiento());
                break;
            case 2:
                renderedView = renderString(licenciaSimit.getCategoria());
                break;
            case 3:
                renderedView = renderString(licenciaSimit.getSecretaria());
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
