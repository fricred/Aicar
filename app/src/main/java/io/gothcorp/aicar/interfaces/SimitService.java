package io.gothcorp.aicar.interfaces;

import java.util.List;


import io.gothcorp.aicar.model.simit.Conductor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by dixfricred on 08/01/2017.
 */

public interface SimitService {
    @Headers({
            "Accept: application/json"
    })
    @GET("simit/co.com.simit.local.domain.conductor/{id}")
    Call<Conductor> consultarConductor(@Path("id") Integer numeroCedula);
}
