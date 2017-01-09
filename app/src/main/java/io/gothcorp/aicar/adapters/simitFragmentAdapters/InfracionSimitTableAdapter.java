package io.gothcorp.aicar.adapters.simitFragmentAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.simit.Infraccion;
import io.gothcorp.aicar.model.simit.LicenciaSimit;

/**
 * Created by dixfricred on 05/01/2017.
 */

public class InfracionSimitTableAdapter extends TableDataAdapter<Infraccion> {
    private static final int TEXT_SIZE = 11;

    public InfracionSimitTableAdapter(Context context, List<Infraccion> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Infraccion infraccion = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(infraccion.getCodigo());
                break;
            case 1:
                renderedView = renderString(infraccion.getArticulo());
                break;
            case 2:
                renderedView = renderString(infraccion.getDescripcion());
                break;
            case 3:
                renderedView = renderString(infraccion.getSalarios());
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
