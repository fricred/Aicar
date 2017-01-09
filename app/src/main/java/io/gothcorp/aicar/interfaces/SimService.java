package io.gothcorp.aicar.interfaces;

import io.gothcorp.aicar.model.sim.Conductor;
import io.gothcorp.aicar.model.sim.SimVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by dixfricred on 08/01/2017.
 */

public interface SimService {

    @Headers({
            "Accept: application/json"
    })
    @GET("sim/co.com.sim.local.domain.conductor/{id}/{placa}/")
    Call<SimVO> consultarConductor(@Path("id") Integer numeroCedula, @Path("placa") String placa);
}
