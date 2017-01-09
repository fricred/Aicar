package io.gothcorp.aicar.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.io.IOException;
import java.util.List;

import io.gothcorp.aicar.R;
import io.gothcorp.aicar.interfaces.RuntService;
import io.gothcorp.aicar.interfaces.SimService;
import io.gothcorp.aicar.interfaces.SimitService;
import io.gothcorp.aicar.model.Servicio;
import io.gothcorp.aicar.model.runt.Persona;
import io.gothcorp.aicar.model.runt.RuntVO;
import io.gothcorp.aicar.model.sim.SimVO;
import io.gothcorp.aicar.model.simit.Conductor;
import io.gothcorp.aicar.ui.activities.ServiceDetailActivity;
import io.gothcorp.aicar.ui.activities.SettingsActivity;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jearm_000 on 20/11/2016.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {
    private Context mContext;
    private List<Servicio> servicioList;
    private Integer cedula;
    private String placa;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, count;
        ImageView thumbnail, overflow;
        GifImageView gifImageView;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            gifImageView = (GifImageView) view.findViewById(R.id.loadingGIf);
        }
    }


    public ServiceAdapter(Context mContext, List<Servicio> servicioList) {
        this.mContext = mContext;
        this.servicioList = servicioList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Servicio servicio = servicioList.get(position);
        if (servicio.getUrl() != null) {

            if (servicio.isRunt()) {
                //NetworkBasic Runt
                Retrofit retrofitRunt = new Retrofit.Builder()
                        .baseUrl(servicio.getUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final RuntService serviceRunt = retrofitRunt.create(RuntService.class);

                Call<List<RuntVO>> call = serviceRunt.consultaRunt(cedula, placa);
                call.enqueue(new Callback<List<RuntVO>>() {
                    @Override
                    public void onResponse(Call<List<RuntVO>> call, Response<List<RuntVO>> response) {
                        if (response.isSuccessful()) {
                            // tasks available
                            final List<RuntVO> lista = response.body();
                            holder.gifImageView.setImageResource(R.drawable.ic_done_black_24dp);
                            Log.i("lista", lista.toString());
                            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext, ServiceDetailActivity.class);
                                    Gson gsonObject = new Gson();
                                    intent.putExtra("runtVo", !lista.isEmpty() ? gsonObject.toJson(lista.get(0)) : null);
                                    intent.putExtra("runtService", true);
                                    intent.putExtra("detailTitle", servicio.getName());
                                    mContext.startActivity(intent);

                                }
                            });
                        } else {
                            // error response, no access to resource?
                            holder.gifImageView.setImageResource(R.drawable.ic_error_black_24dp);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RuntVO>> call, Throwable t) {
                        // something went completely south (like no internet connection)
                        holder.gifImageView.setImageResource(R.drawable.ic_error_black_24dp);
                        Log.d("Error", t.getMessage() != null ? t.getMessage() : "no Message");
                    }
                });

            } else if (servicio.isSimit()) {
                //NetworkBasic Simit
                Retrofit retrofitSimit = new Retrofit.Builder()
                        .baseUrl(servicio.getUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final SimitService simitService = retrofitSimit.create(SimitService.class);

                Call<Conductor> call = simitService.consultarConductor(cedula);
                call.enqueue(new Callback<Conductor>() {
                    @Override
                    public void onResponse(Call<Conductor> call, Response<Conductor> response) {
                        if (response.isSuccessful()) {
                            // tasks available
                            final Conductor conductor = response.body();
                            holder.gifImageView.setImageResource(R.drawable.ic_done_black_24dp);
                            Log.i("lista SImit", conductor.toString());
                            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext, ServiceDetailActivity.class);
                                    Gson gsonObject = new Gson();
                                    intent.putExtra("conductor", gsonObject.toJson(conductor));
                                    intent.putExtra("simitService", true);
                                    intent.putExtra("detailTitle", servicio.getName());
                                    mContext.startActivity(intent);
                                }
                            });
                        } else {
                            // error response, no access to resource?
                            holder.gifImageView.setImageResource(R.drawable.ic_error_black_24dp);
                        }
                    }

                    @Override
                    public void onFailure(Call<Conductor> call, Throwable t) {
                        // something went completely south (like no internet connection)
                        holder.gifImageView.setImageResource(R.drawable.ic_error_black_24dp);
                        Log.d("Error", t.getMessage());
                    }
                });
            } else if (servicio.isSim()) {
                //NetworkBasic Simit
                Retrofit retrofitSim = new Retrofit.Builder()
                        .baseUrl(servicio.getUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final SimService simService = retrofitSim.create(SimService.class);

                Call<SimVO> call = simService.consultarConductor(cedula, placa);
                call.enqueue(new Callback<SimVO>() {
                    @Override
                    public void onResponse(Call<SimVO> call, Response<SimVO> response) {
                        if (response.isSuccessful()) {
                            // tasks available
                            final SimVO simVO = response.body();
                            holder.gifImageView.setImageResource(R.drawable.ic_done_black_24dp);
                            Log.i("lista Sim", simVO.toString());
                            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext, ServiceDetailActivity.class);
                                    Gson gsonObject = new Gson();
                                    intent.putExtra("conductor", gsonObject.toJson(simVO));
                                    intent.putExtra("simService", true);
                                    intent.putExtra("detailTitle", servicio.getName());
                                    mContext.startActivity(intent);
                                }
                            });
                        } else {
                            // error response, no access to resource?
                            holder.gifImageView.setImageResource(R.drawable.ic_error_black_24dp);
                        }
                    }

                    @Override
                    public void onFailure(Call<SimVO> call, Throwable t) {
                        // something went completely south (like no internet connection)
                        holder.gifImageView.setImageResource(R.drawable.ic_error_black_24dp);
                        Log.d("Error", t.getMessage());
                    }
                });

            }
        }

        holder.title.setText(servicio.getName());
        holder.count.setText(servicio.getEntidad());
        holder.thumbnail.setImageResource(servicio.getIcon());

        holder.thumbnail.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccentDark));
        holder.gifImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccentDark));
        if (position < servicioList.size() - 1)

        {
            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);
                }
            });
        } else

        {
            holder.overflow.setVisibility(View.INVISIBLE);
            holder.gifImageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.service_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }

    }

    @Override
    public int getItemCount() {
        return servicioList.size();
    }


    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
