package io.gothcorp.aicar.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.twitter.sdk.android.tweetui.TweetActionBarView;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import io.gothcorp.aicar.R;
import io.gothcorp.aicar.model.Usuario;

public class ServiceDetailActivity extends AppCompatActivity implements SimitFragment.OnFragmentInteractionListener, SimFragment.OnSimFragmentInteraction {
    Bundle bundle;
    Gson gsonObject;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        intent = getIntent();
        bundle = savedInstanceState != null ? savedInstanceState : intent.getExtras();
        gsonObject = new Gson();
        String titulo = bundle != null ? bundle.getString("detailTitle") : "Detalle";
        setTitle(titulo);
        boolean isRunt = bundle.getBoolean("runtService");
        boolean isSimit = bundle.getBoolean("simitService");
        boolean isSim = bundle.getBoolean("simService");
        if (isRunt) {
            showFragmentRunt(savedInstanceState);
        } else if (isSimit) {
            showFragmenSimit(savedInstanceState);
        }else if(isSim){
            showFragmenSim(savedInstanceState);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFragmentRunt(Bundle savedInstanceState) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_runt_container);
        if (frameLayout != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            ServiceDetailActivityFragment serviceDetailActivityFragment = new ServiceDetailActivityFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            serviceDetailActivityFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_runt_container, serviceDetailActivityFragment).commit();
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    private void showFragmenSimit(Bundle savedInstanceState) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_simit_container);
        if (frameLayout != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            SimitFragment simitActivityFragment = new SimitFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            simitActivityFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_simit_container, simitActivityFragment).commit();
            frameLayout.setVisibility(View.VISIBLE);
        }
    }
    private void showFragmenSim(Bundle savedInstanceState) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_sim_container);
        if (frameLayout != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            SimFragment simFragment = new SimFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            simFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_sim_container, simFragment).commit();
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("simitFragment", uri.toString());
    }

    @Override
    public void onSimFragmentInteraction(Uri uri) {
        Log.i("simitFragment", uri.toString());
    }
}
