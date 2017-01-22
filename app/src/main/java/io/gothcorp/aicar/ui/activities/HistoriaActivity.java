package io.gothcorp.aicar.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import io.gothcorp.aicar.R;
import io.gothcorp.aicar.Utils.TinyDB;
import io.gothcorp.aicar.adapters.RVhistoryAdapter;
import io.gothcorp.aicar.model.Historia;
import io.gothcorp.aicar.model.Usuario;

/**
 * Actividad encargada de consultar y mostrar el historial de consultas rapidas que se ha realziado con la aplicacion ussando,
 * detecccion automatica de placas
 */
public class HistoriaActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<Historia> list;
    private Bundle bundle;
    private Intent intent;
    private Gson gsonObject;
    private Usuario usuarioLogeado;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Historial");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        rv = (RecyclerView) findViewById(R.id.reciclerHistory);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        intent = getIntent();
        bundle = savedInstanceState != null ? savedInstanceState : intent.getExtras();
        gsonObject = new Gson();
        usuarioLogeado = bundle != null ? gsonObject.fromJson(bundle.getString("actualUser"), Usuario.class) : null;
        TinyDB tinydb = new TinyDB(this);
        list = (List<Historia>) (List) tinydb.getListObject("Aicar.Usuario." + usuarioLogeado.getUsuario() + ".history", Historia.class);
        RVhistoryAdapter rVhistoryAdapter = new RVhistoryAdapter(list);
        rv.setAdapter(rVhistoryAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
            case R.id.action_accept_editar:
                //attemptEditarPefil();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
