package io.gothcorp.aicar.adapters.simitFragmentAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.simit.LicenciaSimit;
import io.gothcorp.aicar.model.simit.Vehiculo;

/**
 * Created by dixfricred on 05/01/2017.
 */

public class VehiculosSimitTableAdapter extends TableDataAdapter<Vehiculo> {
    private static final int TEXT_SIZE = 11;

    public VehiculosSimitTableAdapter(Context context, List<Vehiculo> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Vehiculo vehiculo = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(vehiculo.getPlaca());
                break;
            case 1:
                renderedView = renderString(vehiculo.getNoLicencia());
                break;
            case 2:
                renderedView = renderString(vehiculo.getTipo());
                break;
            case 3:
                renderedView = renderString(vehiculo.getServicio());
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
