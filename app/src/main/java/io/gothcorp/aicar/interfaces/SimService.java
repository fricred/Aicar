package io.gothcorp.aicar.interfaces;

import io.gothcorp.aicar.model.sim.Conductor;
import io.gothcorp.aicar.model.sim.SimVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Interfsz que define la forma en la cual se consulta el servicio web que ofrece SIM.
 * Se configuran los encabezados de la solicitud para que se reciba un JSON, el metodo a usar y los parametros a enviar
 *
 * @author Jeisson Huerfano
 */

public interface SimService {

    @Headers({
            "Accept: application/json"
    })
    @GET("sim/co.com.sim.local.domain.conductor/{id}/{placa}/")
    Call<SimVO> consultarConductor(@Path("id") Integer numeroCedula, @Path("placa") String placa);
}
