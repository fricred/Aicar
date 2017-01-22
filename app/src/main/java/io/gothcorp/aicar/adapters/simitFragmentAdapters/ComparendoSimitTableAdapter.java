package io.gothcorp.aicar.adapters.simitFragmentAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import io.gothcorp.aicar.model.simit.Comparendo;
import io.gothcorp.aicar.model.simit.LicenciaSimit;
/**
 * Adaptador para la tabla de los comparendos del servicio SIMIT.
 * @author Jeisson Huerfano
 */
public class ComparendoSimitTableAdapter extends TableDataAdapter<Comparendo> {
    private static final int TEXT_SIZE = 11;

    public ComparendoSimitTableAdapter(Context context, List<Comparendo> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Comparendo comparendo = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(comparendo.getNumero().toString());
                break;
            case 1:
                renderedView = renderString(formatDate(comparendo.getFecha()));
                break;
            case 2:
                renderedView = renderString(comparendo.getDireccion());
                break;
            case 3:
                renderedView = renderString(comparendo.getSecretaria());
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
