package io.gothcorp.aicar.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import io.gothcorp.aicar.R;
import io.gothcorp.aicar.Utils.TinyDB;
import io.gothcorp.aicar.model.Usuario;
import retrofit2.Call;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "B6hiahhZWoSN8a0K6OgMiJN5e";
    private static final String TWITTER_SECRET = "5RpUjukBeTSHurdVlmKdrbO5TTUISmFytYDOaVExGLI8coU5Qq";
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private GoogleApiClient mGoogleApiClient;
    // UI references.
    private EditText mUsuario;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View mLoginFormHint;
    private View mLoginFormSocial;
    private View mLao;
    private View mLoginFormButtons;
    //Facebook API
    private CallbackManager callbackManager;
    private TwitterAuthClient clientTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        clientTwitter = new TwitterAuthClient();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        // Set up the login form.
        setupUI(findViewById(R.id.parent));
        mUsuario = (EditText) findViewById(R.id.usuario);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    hideSoftKeyboard(LoginActivity.this);
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.login_app);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mLoginFormHint = findViewById(R.id.loginHint);
        mLoginFormSocial = findViewById(R.id.social_btn_container);
        mLao = findViewById(R.id.la_o);
        mLoginFormButtons = findViewById(R.id.buttons_container);
        mProgressView = findViewById(R.id.login_progress);

        //Facebook Login
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        goLoginFacebook(null);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        //Google login
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        findViewById(R.id.iv_google).setOnClickListener(this);
        findViewById(R.id.iv_twitter).setOnClickListener(this);

        //twitter

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsuario.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsuario.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUsuario.setError(getString(R.string.error_field_required));
            focusView = mUsuario;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Usuario usuario = new Usuario();
            usuario.setUsuario(email.toUpperCase());
            usuario.setClave(password);
            initLoginTask(usuario);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
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

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mLoginFormHint.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormHint.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormHint.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mLao.setVisibility(show ? View.GONE : View.VISIBLE);
            mLao.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLao.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mLoginFormButtons.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormButtons.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormButtons.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mLoginFormSocial.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormSocial.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormSocial.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormSocial.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormButtons.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormHint.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_google:
                signIn();
                break;
            case R.id.iv_twitter:
                clientTwitter.authorize(this, new com.twitter.sdk.android.core.Callback<TwitterSession>() {

                    @Override
                    public void success(Result<TwitterSession> result) {
                        // Success
                        // The TwitterSession is also available through:
                        // Twitter.getInstance().core.getSessionManager().getActiveSession()
                        TwitterSession session = result.data;
                        // TODO: Remove toast and use the TwitterSession's userID
                        // with your app's user model
                        Twitter twitter = Twitter.getInstance();
                        TwitterApiClient api = twitter.core.getApiClient(session);
                        AccountService service = api.getAccountService();
                        Call<User> user = service.verifyCredentials(true, true);

                        user.enqueue(new Callback<User>() {
                            @Override
                            public void success(Result<User> userResult) {
                                Usuario usuario = new Usuario();
                                usuario.setTwitterI(((Long) userResult.data.id).toString());
                                initLoginTask(usuario);
                            }

                            @Override
                            public void failure(TwitterException exc) {
                                Log.d("TwitterKit", "Verify Credentials Failure", exc);
                            }
                        });
                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });

                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final Usuario loginUser;
        private final Context mContext;
        private Usuario userFound;

        UserLoginTask(Usuario usuario, Context context) {
            loginUser = usuario;
            mContext = context;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            TinyDB tinydb = new TinyDB(mContext);
            List<Usuario> usuarios = (List<Usuario>) (List) tinydb.getListObject("Aicar.Usuarios", Usuario.class);
            if (usuarios != null && !usuarios.isEmpty() && usuarios.contains(loginUser)) {
                userFound = usuarios.get(usuarios.indexOf(loginUser));
                if (loginUser.getClave() != null && !userFound.getClave().equals(loginUser.getClave())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                Gson gsonObject = new Gson();

                Intent intent = new Intent(mContext, Home.class);
                intent.putExtra("actualUser", gsonObject.toJson(userFound));
                startActivity(intent);
                finish();
            } else {

                showProgress(false);
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
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

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    /**
     * Called when the user clicks the Send button
     */
    public void goRegistrar(View view) {
        Intent intent = new Intent(this, ActivitySocialSignUp.class);
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    public void olvidoPassword(final View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.olvido_pwd_title)
                .positiveText(R.string.aceptar_btn)
                .iconRes(R.drawable.logo_aicar)
                .negativeText(R.string.cancelar_btn)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.pwd_reminder_label, R.string.pwd_reminder_label_empty, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        // Do something
                        String texto = input.toString().toUpperCase();
                        Usuario usuario = new Usuario();
                        Usuario userFound;
                        Context context = view.getContext();
                        if (isEmailValid(texto)) {
                            usuario.setCorreo(texto);
                        } else {
                            usuario.setUsuario(texto);
                        }
                        TinyDB tinydb = new TinyDB(view.getContext());
                        List<Usuario> usuarios = (List<Usuario>) (List) tinydb.getListObject("Aicar.Usuarios", Usuario.class);
                        if (usuarios != null && !usuarios.isEmpty() && usuarios.contains(usuario)) {
                            userFound = usuarios.get(usuarios.indexOf(usuario));
                            Intent intent = new Intent(context, Home.class);
                            Gson gsonObject = new Gson();
                            intent.putExtra("actualUser", gsonObject.toJson(userFound));
                            intent.putExtra("goEditar", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(view.getContext(), "No se encontro un usuario asociado", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .show();
    }

    private void initLoginTask(Usuario usuario) {
        showProgress(true);
        mAuthTask = new UserLoginTask(usuario, this);
        mAuthTask.execute((Void) null);
    }


    /**
     * Called when the user clicks the Send button
     */
    public void goLoginFacebook(View view) {
        showProgress(true);
        if (AccessToken.getCurrentAccessToken() == null) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        }
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Usuario usuario = new Usuario();
                            try {
                                usuario.setFacebookId(object.get("id").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            initLoginTask(usuario);
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,first_name,last_name");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        clientTwitter.onActivityResult(requestCode, resultCode, data);
        showProgress(false);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        //loginButtonTwitter.onActivityResult(requestCode, resultCode, data);
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.getStatus());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Usuario usuario = new Usuario();
            usuario.setGoogleId(acct.getId());
            initLoginTask(usuario);
        } else {
            showProgress(false);
        }
    }

    // [END handleSignInResult]
    // [START signIn]
    private void signIn() {
        showProgress(true);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

}

