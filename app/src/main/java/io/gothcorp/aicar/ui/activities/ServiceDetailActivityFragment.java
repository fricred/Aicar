package io.gothcorp.aicar.ui.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import io.gothcorp.aicar.R;
import io.gothcorp.aicar.adapters.DetalleLicenciaRuntTableAdapter;
import io.gothcorp.aicar.adapters.LicenciaRuntTableAdapter;
import io.gothcorp.aicar.adapters.ServiceAdapter;
import io.gothcorp.aicar.adapters.SolicitudRuntTableAdapter;
import io.gothcorp.aicar.model.runt.LicenciaRunt;
import io.gothcorp.aicar.model.runt.Persona;
import io.gothcorp.aicar.model.runt.RuntVO;
import io.gothcorp.aicar.model.runt.Solicitud;
import io.gothcorp.aicar.model.runt.Vehiculo;

/**
 * A placeholder fragment containing a simple view.
 */
public class ServiceDetailActivityFragment extends Fragment {
    private TableView tableView;
    private TableView tableSolicitudes;
    private RuntVO runtVO;
    TextView personaTipoDoc;

    public ServiceDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_service_runt, container, false);
        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        Gson gsonObject = new Gson();
        String titulo = bundle != null ? bundle.getString("detailTitle") : "Detalle";
        runtVO = bundle != null ? gsonObject.fromJson(bundle.getString("runtVo"), RuntVO.class) : null;
        if (runtVO != null) {
            //Mostramos el contenido al usuario
            populatePersona(runtVO.getPersona(), rootView);
            populateVehiculo(runtVO.getVehiculo(), rootView);
            tableView = (TableView) rootView.findViewById(R.id.tableLicencias);
            tableSolicitudes = (TableView) rootView.findViewById(R.id.tableSolicitudes);
            cargarTablaLicencias();
            cargarTablaSolicitudes();
        }
        final ImageView overflow = (ImageView) rootView.findViewById(R.id.overflow);
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(overflow);
            }
        });
        // Toast.makeText(getActivity(), tableView.getDataAdapter().getCount(), Toast.LENGTH_SHORT).show();
        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    void cargarTablaLicencias() {
        List<LicenciaRunt> licenciaRuntList = runtVO.getPersona().getLicenciaRuntList();

        if (licenciaRuntList != null && !licenciaRuntList.isEmpty()) {
            tableView.addDataClickListener(new LicenciaClickListener());
            tableView.setFocusable(false);
            final LicenciaRuntTableAdapter licenciaRuntTableAdapter = new LicenciaRuntTableAdapter(getActivity(), runtVO.getPersona().getLicenciaRuntList());
            tableView.setDataAdapter(licenciaRuntTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Numero", "OT Explide", "Estado", "Fecha");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tableView.setHeaderElevation(10);
            tableView.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tableView.getLayoutParams();
            Log.i("h", layoutParams.height + "");
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            layoutParams.height = licenciaRuntList.size() < 5 ? (licenciaRuntList.size() + 1) * refHegith : 6 * refHegith;
            tableView.setLayoutParams(layoutParams);
        }

    }

    @SuppressWarnings("unchecked")
    private class LicenciaClickListener implements TableDataClickListener<LicenciaRunt> {

        @Override
        public void onDataClicked(final int rowIndex, final LicenciaRunt clickedData) {
            MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                    .title(getResources().getString(R.string.detalle_licencia_lbl) + " " + clickedData.getNumeroLicencia())
                    .titleColorRes(R.color.colorAccent)
                    .customView(R.layout.detalle_licencia_view, false)
                    .positiveText(R.string.btn_cerrar_lbl)
                    .build();
            if (dialog.getCustomView() != null) {

                if (clickedData.getDetalleLicenciaList() != null && !clickedData.getDetalleLicenciaList().isEmpty()) {
                    TableView tableDetalle = (TableView) dialog.getCustomView().findViewById(R.id.tableDetalleLicencia);
                    final DetalleLicenciaRuntTableAdapter detalleLicenciaRuntTableAdapter = new DetalleLicenciaRuntTableAdapter(getActivity(), clickedData.getDetalleLicenciaList());
                    tableDetalle.setDataAdapter(detalleLicenciaRuntTableAdapter);
                    SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Cat", "Expedicion", "Vencimiento", "Venc Examen");
                    simpleTableHeaderAdapter.setTextSize(12);
                    simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));

                    TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(getActivity(), 4, 100);
                    columnModel.setColumnWidth(0, 50);
                    tableDetalle.setColumnModel(columnModel);
                    tableDetalle.setHeaderElevation(10);
                    tableDetalle.setHeaderAdapter(simpleTableHeaderAdapter);
                    ViewGroup.LayoutParams layoutParams = tableDetalle.getLayoutParams();
                    Log.i("h", layoutParams.height + "");
                    int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                    layoutParams.height = clickedData.getDetalleLicenciaList().size() < 5 ? (clickedData.getDetalleLicenciaList().size() + 1) * refHegith : 6 * refHegith;
                    tableDetalle.setLayoutParams(layoutParams);
                }
            }
            dialog.show();
        }
    }


    @SuppressWarnings("unchecked")
    void cargarTablaSolicitudes() {
        List<Solicitud> solicituds = runtVO.getPersona().getSolicitudList();
        if (solicituds != null && !solicituds.isEmpty()) {
            tableSolicitudes.addDataClickListener(new SolicitudClickListener());
            tableSolicitudes.setFocusable(false);
            final SolicitudRuntTableAdapter solicitudRuntTableAdapter = new SolicitudRuntTableAdapter(getActivity(), solicituds);
            tableSolicitudes.setDataAdapter(solicitudRuntTableAdapter);
            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getActivity(), "Numero", "Fecha", "Estado", "Entidad");
            simpleTableHeaderAdapter.setTextSize(12);
            simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getActivity(), R.color.White));
            tableSolicitudes.setHeaderElevation(10);
            tableSolicitudes.setHeaderAdapter(simpleTableHeaderAdapter);
            ViewGroup.LayoutParams layoutParams = tableSolicitudes.getLayoutParams();
            int refHegith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            layoutParams.height = solicituds.size() < 5 ? (solicituds.size() + 1) * refHegith : 6 * refHegith;
            tableSolicitudes.setLayoutParams(layoutParams);
        }

    }

    @SuppressWarnings("unchecked")
    private class SolicitudClickListener implements TableDataClickListener<Solicitud> {

        @Override
        public void onDataClicked(final int rowIndex, final Solicitud clickedData) {
            MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                    .title(getResources().getString(R.string.detalle_solicitud_lbl) + " " + clickedData.getNumeroSolicitud())
                    .titleColorRes(R.color.colorAccent)
                    .customView(R.layout.detalle_solicitud_runt, true)
                    .positiveText(R.string.btn_cerrar_lbl)
                    .build();
            TextView tramitesRealizados = (TextView) dialog.getCustomView().findViewById(R.id.tramitesRealizados);
            tramitesRealizados.setText(clickedData.getTramitesRealizados());
            TextView estado = (TextView) dialog.getCustomView().findViewById(R.id.estado);
            estado.setText(clickedData.getEstado());
            TextView entidad = (TextView) dialog.getCustomView().findViewById(R.id.entidad);
            entidad.setText(clickedData.getEntidad());
            TextView fechaSol = (TextView) dialog.getCustomView().findViewById(R.id.fechaSol);
            fechaSol.setText(formatDate(clickedData.getFechaSolicitud(), "dd/MM/yyyy  mm:ss"));
            TextView vehiculo = (TextView) dialog.getCustomView().findViewById(R.id.vehiculo);
            vehiculo.setText(clickedData.getVehiculosVehiculoId() != null ? clickedData.getVehiculosVehiculoId().getNoPlaca() : "");

            dialog.show();
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

    public void populatePersona(Persona persona, View view) {
        TextView tipoDoc = (TextView) view.findViewById(R.id.personaTipoDocumento);
        tipoDoc.setText(persona.getTipoDocumento());
        TextView numeroDoc = (TextView) view.findViewById(R.id.numeroDocumento);
        numeroDoc.setText(persona.getNumeroDocumento().toString());
        TextView nombres = (TextView) view.findViewById(R.id.nombres);
        nombres.setText(persona.getNombres());
        TextView apellidos = (TextView) view.findViewById(R.id.apellidos);
        apellidos.setText(persona.getApellidos());
        TextView inscripcion = (TextView) view.findViewById(R.id.inscripcion);
        inscripcion.setText(persona.getNroInscripcion().toString());
        TextView fechaInscripcion = (TextView) view.findViewById(R.id.fechaInscripcion);
        fechaInscripcion.setText(persona.getFehchaInscripcion());
        TextView estado = (TextView) view.findViewById(R.id.estado);
        estado.setText(persona.getEstadoConductor());
        TextView estadoPersona = (TextView) view.findViewById(R.id.estadoPersona);
        estadoPersona.setText(persona.getEstadoConductor());
    }

    public void populateVehiculo(Vehiculo vehiculo, View view) {
        TextView placa = (TextView) view.findViewById(R.id.placa);
        placa.setText(vehiculo.getNoPlaca());
        TextView nroLicencia = (TextView) view.findViewById(R.id.nroLicencia);
        nroLicencia.setText(vehiculo.getNoLicenciaTransito());
        TextView estadoVehiculo = (TextView) view.findViewById(R.id.estadoVehiculo);
        estadoVehiculo.setText(vehiculo.getEstado());
        TextView tipoServicio = (TextView) view.findViewById(R.id.tipoServicio);
        tipoServicio.setText(vehiculo.getTipoServicio());
        TextView claseVehiculo = (TextView) view.findViewById(R.id.claseVehiculo);
        claseVehiculo.setText(vehiculo.getClaseVehiculo());
        TextView marca = (TextView) view.findViewById(R.id.marca);
        marca.setText(vehiculo.getMarca());
        TextView linea = (TextView) view.findViewById(R.id.linea);
        linea.setText(vehiculo.getLinea());
        TextView modelo = (TextView) view.findViewById(R.id.modelo);
        modelo.setText(vehiculo.getModelo());
        TextView color = (TextView) view.findViewById(R.id.color);
        color.setText(vehiculo.getColor());
        TextView nroChasis = (TextView) view.findViewById(R.id.nroChasis);
        nroChasis.setText(vehiculo.getNoChasis());
        TextView nroVin = (TextView) view.findViewById(R.id.nroVin);
        nroVin.setText(vehiculo.getNoVin());
        TextView cilindraje = (TextView) view.findViewById(R.id.cilindraje);
        cilindraje.setText(vehiculo.getCilindraje());
        TextView tipoCarroceria = (TextView) view.findViewById(R.id.tipoCarroceria);
        tipoCarroceria.setText(vehiculo.getTipoCarroceria());
        TextView fechaMatricula = (TextView) view.findViewById(R.id.fechaMatricula);
        fechaMatricula.setText(formatDate(vehiculo.getFechaMatricula(), "dd/MM/yyyy"));
        TextView gravemenes = (TextView) view.findViewById(R.id.gravemenes);
        gravemenes.setText(vehiculo.getTieneGravamenes());
        TextView ot = (TextView) view.findViewById(R.id.ot);
        ot.setText(vehiculo.getOrganismoTransito());
        TextView prendas = (TextView) view.findViewById(R.id.prendas);
        prendas.setText(vehiculo.getPrendas());
        TextView clasificacion = (TextView) view.findViewById(R.id.clasificacion);
        clasificacion.setText(vehiculo.getClasificacion());
        TextView motorRegrabado = (TextView) view.findViewById(R.id.motorRegrabado);
        motorRegrabado.setText(vehiculo.getEsRegrabadoMotor());
        TextView chasisRegrabado = (TextView) view.findViewById(R.id.chasisRegrabado);
        chasisRegrabado.setText(vehiculo.getEsRegrabadoChasis());
        TextView vinRegrabado = (TextView) view.findViewById(R.id.vinRegrabado);
        vinRegrabado.setText(vehiculo.getEsRegrabadoVin());
        TextView serieRegrabado = (TextView) view.findViewById(R.id.serieRegrabado);
        serieRegrabado.setText(vehiculo.getEsRegrabadoSerie());
        TextView esClasico = (TextView) view.findViewById(R.id.esClasico);
        esClasico.setText(vehiculo.getClasicoAntiguo());
        TextView tipoCombustible = (TextView) view.findViewById(R.id.tipoCombustible);
        tipoCombustible.setText(vehiculo.getTipoCombustible());
        TextView pasajerosSentados = (TextView) view.findViewById(R.id.pasajerosSentados);
        pasajerosSentados.setText(vehiculo.getPasajeorsSentados());
        TextView tarjetaServicio = (TextView) view.findViewById(R.id.tarjetaServicio);
        tarjetaServicio.setText(vehiculo.getTarjetaServicio());
        TextView repotenciado = (TextView) view.findViewById(R.id.repotenciado);
        repotenciado.setText(vehiculo.getRepotenciado());


    }


    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.vehiculo_runt_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MenuVehiculoClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    private class MenuVehiculoClickListener implements PopupMenu.OnMenuItemClickListener {

        MenuVehiculoClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(getActivity(), "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(getActivity(), "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

}
