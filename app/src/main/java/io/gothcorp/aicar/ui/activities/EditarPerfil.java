package io.gothcorp.aicar.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.gothcorp.aicar.R;
import io.gothcorp.aicar.Utils.StringUtils;
import io.gothcorp.aicar.Utils.TinyDB;
import io.gothcorp.aicar.model.Usuario;

public class EditarPerfil extends AppCompatActivity {
    private EditText nombres;
    private EditText apellidos;
    private EditText email;
    private EditText placa;
    private EditText cedula;
    private EditText claveAnterior;
    private EditText nuevaClave;
    private EditText confirmacion;
    private Usuario usuarioLogeado;
    private Gson gsonObject;
    private Intent intent;
    private Bundle bundle;
    private RelativeLayout formEditar;
    private View mProgressView;
    private Boolean fromRecovery;
    private ImageView fotoCarro;
    private final int GALLERY_ACTIVITY_CODE = 200;
    private final int RESULT_CROP = 400;
    private TinyDB tinydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Editar Perfil");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Obtener usuario Logeado
        intent = getIntent();
        bundle = savedInstanceState != null ? savedInstanceState : intent.getExtras();
        gsonObject = new Gson();
        usuarioLogeado = bundle != null ? gsonObject.fromJson(bundle.getString("actualUser"), Usuario.class) : null;
        fromRecovery = bundle != null && bundle.getBoolean("fromRecovery");
        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        email = (EditText) findViewById(R.id.correo);
        placa = (EditText) findViewById(R.id.placa);
        cedula = (EditText) findViewById(R.id.cedula);
        claveAnterior = (EditText) findViewById(R.id.claveAnterior);
        nuevaClave = (EditText) findViewById(R.id.nuevaClave);
        confirmacion = (EditText) findViewById(R.id.clave_confirmacion);
        formEditar = (RelativeLayout) findViewById(R.id.form_editar);
        mProgressView = findViewById(R.id.editar_progress);
        fotoCarro = (ImageView) findViewById(R.id.fotoCarro);
        fotoCarro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Start Activity To Select Image From Gallery
                Intent gallery_Intent = new Intent(getApplicationContext(), GalleryUtil.class);
                startActivityForResult(gallery_Intent, GALLERY_ACTIVITY_CODE);
            }
        });
        confirmacion.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    attemptEditarPefil();
                    handled = true;
                }
                return handled;
            }
        });
        tinydb = new TinyDB(this);
        setUi();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
            case R.id.action_accept_editar:
                attemptEditarPefil();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUi() {
        if (usuarioLogeado != null) {
            nombres.setText(usuarioLogeado.getNombres());
            apellidos.setText(usuarioLogeado.getApellidos());
            email.setText(usuarioLogeado.getCorreo());
            cedula.setText(usuarioLogeado.getNroCedula());
            placa.setText(usuarioLogeado.getPlaca());
            if (fromRecovery) {
                claveAnterior.setText(usuarioLogeado.getClave());
            }
            Log.i("datadir", getApplicationInfo().dataDir);
            Bitmap selectedBitmap = tinydb.getImage(getApplicationInfo().dataDir + File.separator + usuarioLogeado.getUsuario());
            // Set The Bitmap Data To ImageView
            if (selectedBitmap != null) {
                fotoCarro.setImageBitmap(selectedBitmap);
                fotoCarro.setScaleType(ImageView.ScaleType.FIT_XY);
            }

        }
    }


    private void attemptEditarPefil() {


        // Reset errors.
        nombres.setError(null);
        apellidos.setError(null);
        email.setError(null);
        cedula.setError(null);
        placa.setError(null);
        claveAnterior.setError(null);
        nuevaClave.setError(null);
        confirmacion.setError(null);


        // Store values at the time of the login attempt.
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres.getText().toString());
        usuario.setApellidos(apellidos.getText().toString());
        usuario.setCorreo(email.getText().toString());
        usuario.setNroCedula(cedula.getText().toString());
        usuario.setPlaca(placa.getText().toString());


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
        } else if (!StringUtils.isEmailValid(usuario.getCorreo())) {
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
        } else if (!StringUtils.isPlacaValid(usuario.getPlaca())) {
            placa.setError(getString(R.string.error_field_placa_invalid));
            focusView = placa;
            cancel = true;
        }
        if (!TextUtils.isEmpty(claveAnterior.getText().toString())) {
            if (!claveAnterior.getText().toString().equals(usuarioLogeado.getClave())) {
                claveAnterior.setError(getString(R.string.error_clave_anterior));
                focusView = claveAnterior;
                cancel = true;
            } else if (TextUtils.isEmpty(nuevaClave.getText().toString())) {
                nuevaClave.setError(getString(R.string.error_field_required));
                focusView = nuevaClave;
                cancel = true;
            } else if (!StringUtils.isPasswordValid(nuevaClave.getText().toString())) {
                nuevaClave.setError(getString(R.string.error_invalid_password));
                focusView = nuevaClave;
                cancel = true;
            } else if (!nuevaClave.getText().toString().equals(confirmacion.getText().toString())) {
                confirmacion.setError(getString(R.string.error_field_no_match));
                focusView = confirmacion;
                cancel = true;
            }
        } else {
            if (!TextUtils.isEmpty(nuevaClave.getText().toString())) {
                if (!claveAnterior.getText().toString().equals(usuarioLogeado.getClave())) {
                    claveAnterior.setError(getString(R.string.error_clave_anterior));
                    focusView = claveAnterior;
                    cancel = true;
                } else if (!nuevaClave.getText().toString().equals(confirmacion.getText().toString())) {
                    confirmacion.setError(getString(R.string.error_field_no_match));
                    focusView = confirmacion;
                    cancel = true;
                }

            }
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            hideSoftKeyboard(EditarPerfil.this);
            showProgress(true);
            usuarioLogeado.setNombres(usuario.getNombres());
            usuarioLogeado.setApellidos(usuario.getApellidos());
            usuarioLogeado.setPlaca(usuario.getPlaca());
            usuarioLogeado.setNroCedula(usuario.getNroCedula());
            usuarioLogeado.setCorreo(usuario.getCorreo());
            if (!TextUtils.isEmpty(nuevaClave.getText().toString())) {
                usuarioLogeado.setClave(nuevaClave.getText().toString());
            }
            if (editar()) {
                Toast.makeText(this, "Usuario Modificado Correctamente", Toast.LENGTH_SHORT).show();
                claveAnterior.setText("");
                nuevaClave.setText("");
                confirmacion.setText("");
            } else {
                Toast.makeText(this, "Ocurrio un error al editar el usuario", Toast.LENGTH_SHORT).show();
            }
            showProgress(false);

        }
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

            formEditar.setVisibility(show ? View.GONE : View.VISIBLE);
            formEditar.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    formEditar.setVisibility(show ? View.GONE : View.VISIBLE);
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
            formEditar.setVisibility(show ? View.GONE : View.VISIBLE);
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
    public Boolean editar() {

        List<Usuario> usuarios = (List<Usuario>) (List) tinydb.getListObject("Aicar.Usuarios", Usuario.class);
        if (usuarios != null && !usuarios.isEmpty()) {
            int index = usuarios.indexOf(usuarioLogeado);
            usuarios.remove(index);
            usuarios.add(usuarioLogeado);
        }
        // save the task list to preference
        tinydb.putListObject("Aicar.Usuarios", (ArrayList<Object>) (List) usuarios);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        MenuItem aceptarItem = menu.findItem(R.id.action_accept_editar);
        Drawable newIcon = aceptarItem.getIcon();
        newIcon.mutate().setColorFilter(Color.argb(255, 200, 200, 200), PorterDuff.Mode.SRC_IN);
        aceptarItem.setIcon(newIcon);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String picturePath = data.getStringExtra("picturePath");
                //perform Crop on the Image Selected from Gallery
                performCrop(picturePath);
            }
        }

        if (requestCode == RESULT_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap selectedBitmap = extras.getParcelable("data");
                // Set The Bitmap Data To ImageView
                fotoCarro.setImageBitmap(selectedBitmap);
                tinydb.putImageWithFullPath(getApplicationInfo().dataDir + File.separator + usuarioLogeado.getUsuario(), selectedBitmap);
                fotoCarro.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }

    private void performCrop(String picUri) {
        try {
            //Start Crop Activity

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            File f = new File(picUri);
            Uri contentUri = Uri.fromFile(f);

            cropIntent.setDataAndType(contentUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 280);
            cropIntent.putExtra("outputY", 280);

            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, RESULT_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
