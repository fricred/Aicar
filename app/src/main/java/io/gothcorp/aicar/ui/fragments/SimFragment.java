package io.gothcorp.aicar.ui.fragments;

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

import com.google.gson.Gson;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import io.gothcorp.aicar.R;
import io.gothcorp.aicar.adapters.simFragmentAdapters.TramiteSimTableAdapter;
import io.gothcorp.aicar.adapters.simFragmentAdapters.VehiculosSimTableAdapter;
import io.gothcorp.aicar.model.sim.Conductor;
import io.gothcorp.aicar.model.sim.SimVO;
import io.gothcorp.aicar.model.sim.Tramite;
import io.gothcorp.aicar.model.sim.Vehiculo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimFragment.OnSimFragmentInteraction} interface
 * to handle interaction events.
 * Use the {@link SimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SimVO simVO;
    private TableView tablaTramitesPersona;
    private TableView tableVehiculos;
    private TableView tablaTramitesVehiculo;
    private OnSimFragmentInteraction mListener;

    public SimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimFragment newInstance(String param1, String param2) {
        SimFragment fragment = new SimFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sim, container, false);
        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        Gson gsonObject = new Gson();
        simVO = bundle != null ? gsonObject.fromJson(bundle.getString("simVO"), SimVO.class) : null;
        tablaTramitesPersona = (TableView) rootView.findViewById(R.id.tableTramitesPersona);
        tablaTramitesVehiculo = (TableView) rootView.findViewById(R.id.tableTramitesVehiculos);
        tableVehiculos = (TableView) rootView.findViewById(R.id.tableVehiculos);
        if (simVO != null) {
            populatePersona(simVO.getConductor(), rootView);
            cargarTablaLicencias();
            cargarTablaVehiculos();
        }
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSimFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSimFragmentInteraction) {
            mListener = (OnSimFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSimFragmentInteraction");
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
    public interface OnSimFragmentInteraction {
        // TODO: Update argument type and name
        void onSimFragmentInteraction(Uri uri);
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
        List<Tramite> tramiteList = simVO.getConductor().getTramiteList();

        if (tramiteList != null && !tramiteList.isEmpty()) {

            final TramiteSimTableAdapter tramiteSimTableAdapter = new TramiteSimTableAdapter(getActivity(), simVO.getConductor().getTramiteList());
            tablaTramitesPersona.setDataAdapter(tramiteSimTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "# Radicado", "F Solicitud", "Estado", "Resultado");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tablaTramitesPersona.setHeaderElevation(10);
            tablaTramitesPersona.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tablaTramitesPersona.getLayoutParams();
            Log.i("h", layoutParams.height + "");
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            layoutParams.height = tramiteList.size() < 5 ? (tramiteList.size() + 1) * refHegith : 6 * refHegith;
            tablaTramitesPersona.setLayoutParams(layoutParams);
        }
        List<Tramite> tramiteVehiculoList = simVO.getVehiculo().getTramiteList();

        if (tramiteVehiculoList != null && !tramiteVehiculoList.isEmpty()) {

            final TramiteSimTableAdapter tramiteSimTableAdapter = new TramiteSimTableAdapter(getActivity(), tramiteVehiculoList);
            tablaTramitesVehiculo.setDataAdapter(tramiteSimTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(),  "# Radicado", "F Solicitud", "Estado", "Resultado");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tablaTramitesVehiculo.setHeaderElevation(10);
            tablaTramitesVehiculo.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tablaTramitesVehiculo.getLayoutParams();
            Log.i("h", layoutParams.height + "");
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            layoutParams.height = tramiteList.size() < 5 ? (tramiteVehiculoList.size() + 1) * refHegith : 6 * refHegith;
            tablaTramitesVehiculo.setLayoutParams(layoutParams);
        }

    }

    @SuppressWarnings("unchecked")
    void cargarTablaVehiculos() {
        List<Vehiculo> vehiculoList = simVO.getConductor().getVehiculoList();

        if (vehiculoList != null && !vehiculoList.isEmpty()) {

            final VehiculosSimTableAdapter vehiculosSimitTableAdapter = new VehiculosSimTableAdapter(getActivity(), simVO.getConductor().getVehiculoList());
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

}
