package io.gothcorp.aicar.ui.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import io.gothcorp.aicar.R;
import io.gothcorp.aicar.adapters.DetalleLicenciaRuntTableAdapter;
import io.gothcorp.aicar.adapters.LicenciaRuntTableAdapter;
import io.gothcorp.aicar.adapters.simitFragmentAdapters.ComparendoSimitTableAdapter;
import io.gothcorp.aicar.adapters.simitFragmentAdapters.InfracionSimitTableAdapter;
import io.gothcorp.aicar.adapters.simitFragmentAdapters.LicenciaSimitTableAdapter;
import io.gothcorp.aicar.adapters.simitFragmentAdapters.VehiculosSimitTableAdapter;
import io.gothcorp.aicar.model.runt.LicenciaRunt;
import io.gothcorp.aicar.model.runt.Persona;
import io.gothcorp.aicar.model.runt.RuntVO;
import io.gothcorp.aicar.model.simit.Comparendo;
import io.gothcorp.aicar.model.simit.Conductor;
import io.gothcorp.aicar.model.simit.LicenciaSimit;
import io.gothcorp.aicar.model.simit.Vehiculo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Conductor conductor;
    private TableView tablaLicencias;
    private TableView tableComparendos;
    private TableView tableVehiculos;
    private OnFragmentInteractionListener mListener;

    public SimitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimitFragment newInstance(String param1, String param2) {
        SimitFragment fragment = new SimitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_simit, container, false);
        tablaLicencias = (TableView) rootView.findViewById(R.id.tableLicenciasSimit);
        tableComparendos = (TableView) rootView.findViewById(R.id.tableComparendos);
        tableVehiculos = (TableView) rootView.findViewById(R.id.tableVehiculos);
        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        Gson gsonObject = new Gson();
        conductor = bundle != null ? gsonObject.fromJson(bundle.getString("conductor"), Conductor.class) : null;
        if (conductor != null) {
            populatePersona(conductor, rootView);
            cargarTablaLicencias();
            cargarTablaComparendos();
            cargarTablaVehiculos();
        }
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void populatePersona(Conductor conductor, View view) {
        TextView tipoDoc = (TextView) view.findViewById(R.id.personaTipoDocumento);
        tipoDoc.setText(conductor.getTipoDocumento());
        TextView numeroDoc = (TextView) view.findViewById(R.id.numeroDocumento);
        numeroDoc.setText(conductor.getConductorId().toString());
        TextView nombres = (TextView) view.findViewById(R.id.nombres);
        nombres.setText(conductor.getNombres());
        TextView apellidos = (TextView) view.findViewById(R.id.apellidos);
        apellidos.setText(conductor.getApellidos());
        TextView inscripcion = (TextView) view.findViewById(R.id.tipoInfractor);
        inscripcion.setText(conductor.getTipoInfractor().toString());
    }

    @SuppressWarnings("unchecked")
    void cargarTablaLicencias() {
        List<LicenciaSimit> licenciaSimitList = conductor.getLicenciaSimitList();

        if (licenciaSimitList != null && !licenciaSimitList.isEmpty()) {

            final LicenciaSimitTableAdapter licenciaSimitTableAdapter = new LicenciaSimitTableAdapter(getActivity(), conductor.getLicenciaSimitList());
            tablaLicencias.setDataAdapter(licenciaSimitTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Numero", "F Vencimiento", "Cateogria", "Secretaria");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tablaLicencias.setHeaderElevation(10);
            tablaLicencias.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tablaLicencias.getLayoutParams();
            Log.i("h", layoutParams.height + "");
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            layoutParams.height = licenciaSimitList.size() < 5 ? (licenciaSimitList.size() + 1) * refHegith : 6 * refHegith;
            tablaLicencias.setLayoutParams(layoutParams);
        }

    }

    @SuppressWarnings("unchecked")
    void cargarTablaComparendos() {
        List<Comparendo> comparendoList = conductor.getComparendoList();

        if (comparendoList != null && !comparendoList.isEmpty()) {
            tableComparendos.addDataClickListener(new ComparendoClickListener());
            tableComparendos.setFocusable(false);
            final ComparendoSimitTableAdapter comparendoSimitTableAdapter = new ComparendoSimitTableAdapter(getActivity(), conductor.getComparendoList());
            tableComparendos.setDataAdapter(comparendoSimitTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Numero", "Fecha", "Dirección", "Secretaria");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tableComparendos.setHeaderElevation(10);
            tableComparendos.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tableComparendos.getLayoutParams();
            Log.i("h", layoutParams.height + "");
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
            layoutParams.height = comparendoList.size() < 5 ? (comparendoList.size() + 1) * refHegith : 6 * refHegith;
            tableComparendos.setLayoutParams(layoutParams);
        }

    }


    @SuppressWarnings("unchecked")
    private class ComparendoClickListener implements TableDataClickListener<Comparendo> {

        @Override
        public void onDataClicked(final int rowIndex, final Comparendo clickedData) {
            MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                    .title("Comparendo # " + clickedData.getNumero())
                    .titleColorRes(R.color.colorAccent)
                    .customView(R.layout.detalle_comparendo_view, false)
                    .positiveText(R.string.btn_cerrar_lbl)
                    .build();
            if (dialog.getCustomView() != null) {
                TextView comparendoElectronico = (TextView) dialog.getCustomView().findViewById(R.id.comparendoElectronico);
                comparendoElectronico.setText(clickedData.getComparendoElectronico());
                TextView fechaNotificacion = (TextView) dialog.getCustomView().findViewById(R.id.fechaNotificacion);
                fechaNotificacion.setText(formatDate(clickedData.getFechaNotificacion(),"dd/MM/yyyy"));
                TextView agente = (TextView) dialog.getCustomView().findViewById(R.id.agente);
                agente.setText(clickedData.getAgente());

                if (clickedData.getInfraccionList() != null && !clickedData.getInfraccionList().isEmpty()) {
                    TableView tableDetalle = (TableView) dialog.getCustomView().findViewById(R.id.tableInfracciones);
                    final InfracionSimitTableAdapter infracionSimitTableAdapter = new InfracionSimitTableAdapter(getActivity(), clickedData.getInfraccionList());
                    tableDetalle.setDataAdapter(infracionSimitTableAdapter);
                    SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Codigo", "Articulo", "Descripcion", "S.M.L.V");
                    simpleTableHeaderAdapter.setTextSize(12);
                    simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));

                   /* TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(getActivity(), 4, 100);
                    columnModel.setColumnWidth(0, 50);
                    tableDetalle.setColumnModel(columnModel);*/
                    tableDetalle.setHeaderElevation(10);
                    tableDetalle.setHeaderAdapter(simpleTableHeaderAdapter);
                    ViewGroup.LayoutParams layoutParams = tableDetalle.getLayoutParams();
                    Log.i("h", layoutParams.height + "");
                    int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                    layoutParams.height = clickedData.getInfraccionList().size() < 5 ? (clickedData.getInfraccionList().size() + 1) * refHegith : 6 * refHegith;
                    tableDetalle.setLayoutParams(layoutParams);
                }
            }
            dialog.show();
        }
    }

    @SuppressWarnings("unchecked")
    void cargarTablaVehiculos() {
        List<Vehiculo> vehiculoList = conductor.getVehiculoList();

        if (vehiculoList != null && !vehiculoList.isEmpty()) {

            final VehiculosSimitTableAdapter vehiculosSimitTableAdapter = new VehiculosSimitTableAdapter(getActivity(), conductor.getVehiculoList());
            tableVehiculos.setDataAdapter(vehiculosSimitTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Numero", "Fecha", "Dirección", "Secretaria");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tableVehiculos.setHeaderElevation(10);
            tableVehiculos.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tableVehiculos.getLayoutParams();
            Log.i("h", layoutParams.height + "");
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            layoutParams.height = vehiculoList.size() < 5 ? (vehiculoList.size() + 1) * refHegith : 6 * refHegith;
            tableVehiculos.setLayoutParams(layoutParams);
        }

    }


    private String formatDate(Date date, String pattern) {
        // Create an instance of SimpleDateFormat used for formatting
// the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat(pattern);
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String reportDate = df.format(date);
        return reportDate;
    }


}
