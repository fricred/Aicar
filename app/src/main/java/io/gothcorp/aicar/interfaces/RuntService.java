package io.gothcorp.aicar.interfaces;

import java.util.List;

import io.gothcorp.aicar.model.runt.Persona;
import io.gothcorp.aicar.model.runt.RuntVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by dixfricred on 03/01/2017.
 */

public interface RuntService {
    @Headers({
            "Accept: application/json"
    })
    @GET("runt/co.com.runt.local.domain.persona/{id}/{placa}")
    Call<List<RuntVO>> consultaRunt(@Path("id") Integer numeroCedula, @Path("placa") String placa);
}
