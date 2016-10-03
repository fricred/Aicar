package io.gothcorp.aicar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ActivitySocialSignUp extends AppCompatActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_social_sign_up);
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
                        Log.println(Log.INFO, "FB", AccessToken.getCurrentAccessToken().getExpires().toString());
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
    public void goRegistrarWithFacebook(JSONObject object) {
        Intent intent = new Intent(this, RegistroActivity.class);
        intent.putExtra("fbObject",object.toString());
        startActivity(intent);
    }
   }
