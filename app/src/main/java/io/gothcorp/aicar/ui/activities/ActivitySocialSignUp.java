package io.gothcorp.aicar.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.gothcorp.aicar.R;
import io.gothcorp.aicar.Utils.TinyDB;
import io.gothcorp.aicar.model.Usuario;
import retrofit2.Call;

public class ActivitySocialSignUp extends AppCompatActivity  implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private TwitterLoginButton loginButtonTwitter;
    List<Usuario> usuarios;
    private Context mContext;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_sign_up);
        mContext = this;
        //cargar usuarios registrados
        TinyDB tinydb = new TinyDB(this);
        usuarios = (List<Usuario>) (List) tinydb.getListObject("Aicar.Usuarios", Usuario.class);

        ActionBar actionBar = getSupportActionBar();
        setTitle(R.string.registro);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); //Set this to true if selecting "home" returns up by a single level in your UI rather than back to the top level or front page.

        }
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        goRegistrarFb(null);
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
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setTextAlignment(SignInButton.TEXT_ALIGNMENT_CENTER);
        signInButton.setScopes(gso.getScopeArray());
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        loginButtonTwitter = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButtonTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                Twitter          twitter = Twitter.getInstance();
                TwitterApiClient api     = twitter.core.getApiClient(session);
                AccountService service = api.getAccountService();
                Call<User> user    = service.verifyCredentials(true, true);

                user.enqueue(new Callback<User>()
                {
                    @Override
                    public void success(Result<User> userResult)
                    {
                        Usuario usuario = new Usuario();
                        usuario.setTwitterI(((Long)userResult.data.id).toString());
                        if (usuarios != null && !usuarios.isEmpty() && usuarios.contains(usuario)) {
                            Usuario userFound = usuarios.get(usuarios.indexOf(usuario));
                            Intent intent = new Intent(mContext, Home.class);
                            Gson gsonObject = new Gson();
                            intent.putExtra("actualUser", gsonObject.toJson(userFound));
                            startActivity(intent);
                            Toast.makeText(mContext, "Usuario Encontrado", Toast.LENGTH_SHORT).show();
                        }else {
                            String name = userResult.data.name;
                            String email = userResult.data.email;
                            JSONObject object = new JSONObject();
                            try {
                                object.put("first_name", name);
                                object.put("username", userResult.data.screenName);
                                object.put("last_name", "");
                                object.put("email", email != null ? email : " ");
                                object.put("twitterId", userResult.data.getId());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            goRegistrarWithFacebook(object);
                        }
                    }

                    @Override
                    public void failure(TwitterException exc)
                    {
                        Log.d("TwitterKit", "Verify Credentials Failure", exc);
                    }
                });
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButtonTwitter.onActivityResult(requestCode, resultCode, data);
    }




    /**
     * Called when the user clicks the Send button
     */
    public void goRegistrarFb(View view) {
        if(AccessToken.getCurrentAccessToken() == null) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        }
        if(AccessToken.getCurrentAccessToken()!= null) {
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            goRegistrarWithFacebook(object);
                            // Application code
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,first_name,last_name");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }


    /** Called when the user clicks the Send button */
    public void goRegistrarWithEmail(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Send button */
    @SuppressWarnings("unchecked")
    public void goRegistrarWithFacebook(JSONObject object) {
        Usuario usuario = new Usuario();
        try {
            usuario.setFacebookId(object.get("id").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (usuarios != null && !usuarios.isEmpty() && usuarios.contains(usuario)) {
            Usuario userFound = usuarios.get(usuarios.indexOf(usuario));
            Intent intent = new Intent(this, Home.class);
            Gson gsonObject = new Gson();
            intent.putExtra("actualUser", gsonObject.toJson(userFound));
            startActivity(intent);
            Toast.makeText(this, "Usuario Encontrado", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, RegistroActivity.class);
            intent.putExtra("fbObject", object.toString());
            startActivity(intent);
        }
    }
  /** Called when the user clicks the Login button */
    public void goLogin(View view) {
        this.finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        //Deslogeo de cualquier cuenta

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            //handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
           // showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                   // hideProgressDialog();
                  //  handleSignInResult(googleSignInResult);
                }
            });
        }
    }


    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.getStatus());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Usuario usuario = new Usuario();
            usuario.setGoogleId(acct.getId());
            if (usuarios != null && !usuarios.isEmpty() && usuarios.contains(usuario)) {
                Usuario userFound = usuarios.get(usuarios.indexOf(usuario));
                Intent intent = new Intent(this, Home.class);
                Gson gsonObject = new Gson();
                intent.putExtra("actualUser", gsonObject.toJson(userFound));
                startActivity(intent);
                Toast.makeText(this, "Usuario Encontrado", Toast.LENGTH_SHORT).show();
            }else {

                JSONObject object = new JSONObject();
                try {
                    object.put("first_name",acct.getGivenName());
                    object.put("last_name",acct.getFamilyName());
                    object.put("email",acct.getEmail());
                    object.put("googleId",acct.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(this, RegistroActivity.class);
                intent.putExtra("fbObject",object.toString());
                startActivity(intent);
            }


        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }



    private void updateUI(boolean signedIn) {
        /*if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signOut();
                signIn();
                break;
        }
    }

    public void onConnected(Bundle bundle) {
        Log.d("Connected", "Connected");
    }





}
