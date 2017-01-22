package io.gothcorp.aicar.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.gothcorp.aicar.R;
import io.gothcorp.aicar.Utils.TinyDB;
import io.gothcorp.aicar.model.Usuario;

/**
 * Esta clase controla el registro de usuarios dentro de la aplicaciòn,
 * realiza las validaciones necesarias y registra el usuario en la base de datos local
 */
public class RegistroActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "AiCar.usuarios";

    private EditText nombres;
    private EditText apellidos;
    private EditText email;
    private EditText cedula;
    private EditText placa;
    private EditText clave;
    private EditText username;
    private EditText confirmarClave;
    private View mRegistroView;
    private View mProgressView;
    private Boolean existeUsuario;
    JSONObject fbObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            fbObject = bundle != null ? new JSONObject(intent.getStringExtra("fbObject")) : null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ActionBar actionBar = getSupportActionBar();
        setTitle(R.string.registro);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); //Set this to true if selecting "home" returns up by a single level in your UI rather than back to the top level or front page.

        }
        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        email = (EditText) findViewById(R.id.correo);
        cedula = (EditText) findViewById(R.id.cedula);
        placa = (EditText) findViewById(R.id.placa);
        clave = (EditText) findViewById(R.id.clave);
        confirmarClave = (EditText) findViewById(R.id.clave_confirmacion);
        confirmarClave.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    attemptRegistro();
                    handled = true;
                }
                return handled;
            }
        });
        username = (EditText) findViewById(R.id.username);
        mRegistroView = findViewById(R.id.registro_form);
        mProgressView = findViewById(R.id.register_progress);
        loadFromFb();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            case R.id.action_accept_register:
                attemptRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void loadFromFb() {
        try {

            if (fbObject != null) {
                nombres.setText(fbObject.get("first_name").toString());
                apellidos.setText(fbObject.get("last_name").toString());
                email.setText(fbObject.get("email") != null ? fbObject.get("email").toString() : "");
                username.setText(fbObject.get("username").toString());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void attemptRegistro() {


        // Reset errors.
        nombres.setError(null);
        apellidos.setError(null);
        email.setError(null);
        cedula.setError(null);
        placa.setError(null);
        username.setError(null);
        clave.setError(null);
        confirmarClave.setError(null);


        // Store values at the time of the login attempt.
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres.getText().toString());
        usuario.setApellidos(apellidos.getText().toString());
        usuario.setCorreo(email.getText().toString());
        usuario.setNroCedula(cedula.getText().toString());
        usuario.setPlaca(placa.getText().toString());
        usuario.setUsuario(username.getText().toString().toUpperCase());
        usuario.setClave(clave.getText().toString());
        if(fbObject!= null){
            try {
                usuario.setFacebookId(fbObject.getString("id"));

            } catch (JSONException e) {
                Log.i("JSONException", e.getMessage());
            }
            try {
                usuario.setGoogleId(fbObject.getString("googleId"));
            } catch (JSONException e) {
                Log.i("JSONException", e.getMessage());
            }
            try {
                usuario.setTwitterI(fbObject.getString("twitterId"));
            } catch (JSONException e) {
                Log.i("JSONException", e.getMessage());
            }
        }

        boolean cancel = false;
        View focusView = null;

        // verificar campos requeridos
        if (TextUtils.isEmpty(usuario.getNombres())) {
            nombres.setError(getString(R.string.error_field_required));
            focusView = nombres;
            cancel = true;
        }

        if (TextUtils.isEmpty(usuario.getCorreo())) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(usuario.getCorreo())) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }

        if (TextUtils.isEmpty(usuario.getNroCedula())) {
            cedula.setError(getString(R.string.error_field_required));
            focusView = cedula;
            cancel = true;
        } else if (TextUtils.isDigitsOnly(usuario.getNroCedula() + ".0")) {
            cedula.setError(getString(R.string.error_campo_numerico));
            focusView = cedula;
            cancel = true;
        }
        if (TextUtils.isEmpty(usuario.getPlaca())) {
            placa.setError(getString(R.string.error_field_required));
            focusView = placa;
            cancel = true;
        } else if (!isPlacaValid(usuario.getPlaca())) {
            placa.setError(getString(R.string.error_field_placa_invalid));
            focusView = placa;
            cancel = true;
        }
        if (TextUtils.isEmpty(usuario.getUsuario())) {
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        }
        if (TextUtils.isEmpty(usuario.getClave())) {
            clave.setError(getString(R.string.error_field_required));
            focusView = clave;
            cancel = true;
        } else if (!isPasswordValid(usuario.getClave())) {
            clave.setError(getString(R.string.error_invalid_password));
            focusView = clave;
            cancel = true;
        } else if (!usuario.getClave().equals(confirmarClave.getText().toString())) {
            confirmarClave.setError(getString(R.string.error_field_no_match));
            focusView = confirmarClave;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            hideSoftKeyboard(RegistroActivity.this);
            showProgress(true);
            if (registrar(usuario)) {
                Toast.makeText(this, "Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show();
                Gson gsonObject = new Gson();
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("actualUser", gsonObject.toJson(usuario));
                startActivity(intent);
            } else if (existeUsuario) {
                Toast.makeText(this, "El nombre de usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ocurrio un error al registrar el usuario", Toast.LENGTH_SHORT).show();
            }
            showProgress(false);

        }
    }


    private boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();

    }

    private boolean isPlacaValid(String placa) {
        String ePattern = "^[A-Z]{3}\\d{3}|[A-Z]{3}\\d{2}[A-Z]{1}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(placa);
        return m.matches();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegistroView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegistroView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegistroView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegistroView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {

        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }

    }

    @SuppressWarnings("unchecked")
    public Boolean registrar(Usuario usuario) {
        existeUsuario = false;
        TinyDB tinydb = new TinyDB(this);
        List<Usuario> usuarios = (List<Usuario>) (List) tinydb.getListObject("Aicar.Usuarios", Usuario.class);
        if (usuarios != null && !usuarios.isEmpty()) {
            for(Usuario usuario1 : usuarios){
                if(usuario1.getUsuario().equals(usuario.getUsuario())){
                    existeUsuario = true;
                    break;
                }
            }
            if (existeUsuario) {
                return false;
            } else {
                usuarios.add(usuario);
            }
        } else {
            usuarios = new ArrayList<>();
            usuarios.add(usuario);
        }
        // save the task list to preference
        tinydb.putListObject("Aicar.Usuarios", (ArrayList<Object>) (List) usuarios);
        return true;
    }
}
