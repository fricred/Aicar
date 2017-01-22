package io.gothcorp.aicar.interfaces;

import java.util.List;


import io.gothcorp.aicar.model.simit.Conductor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Interfsz que define la forma en la cual se consulta el servicio web que ofrece SIMIT.
 * Se configuran los encabezados de la solicitud para que se reciba un JSON, el metodo a usar y los parametros a enviar
 *
 * @author Jeisson Huerfano
 */
public interface SimitService {
    @Headers({
            "Accept: application/json"
    })
    @GET("simit/co.com.simit.local.domain.conductor/{id}")
    Call<Conductor> consultarConductor(@Path("id") Integer numeroCedula);
}
