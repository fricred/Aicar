package io.gothcorp.aicar;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {
    private EditText nombres;
    private EditText apellidos;
    private EditText email;
    private EditText username;
    JSONObject fbObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            fbObject = bundle != null ?new JSONObject(intent.getStringExtra("fbObject")):null;
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
        Button btn=(Button) findViewById(R.id.goLogin);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        email = (EditText) findViewById(R.id.correo);
        username= (EditText) findViewById(R.id.username);
        loadFromFb();
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

    void loadFromFb(){
        try {

            if(fbObject!= null){
                nombres.setText(fbObject.get("first_name").toString());
                apellidos.setText(fbObject.get("last_name").toString());
                email.setText(fbObject.get("email")!= null ? fbObject.get("email").toString():"");
                username.setText(fbObject.get("username").toString());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
