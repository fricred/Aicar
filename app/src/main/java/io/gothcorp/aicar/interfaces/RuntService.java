package io.gothcorp.aicar.interfaces;

import java.util.List;

import io.gothcorp.aicar.model.runt.Persona;
import io.gothcorp.aicar.model.runt.RuntVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Interfsz que define la forma en la cual se consulta el servicio web que ofrece RUNT.
 * Se configuran los encabezados de la solicitud para que se reciba un JSON, el metodo a usar y los parametros a enviar
 *
 * @author Jeisson Huerfano
 */

public interface RuntService {
    @Headers({
            "Accept: application/json"
    })
    @GET("runt/co.com.runt.local.domain.persona/{id}/{placa}")
    Call<List<RuntVO>> consultaRunt(@Path("id") Integer numeroCedula, @Path("placa") String placa);
}
