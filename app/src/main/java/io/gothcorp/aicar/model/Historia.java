package io.gothcorp.aicar.model;

import java.util.Date;

/**
 * Created by dixfricred on 31/12/2016.
 */

public class Historia {
    String placa;
    Date fecha;
    String phothoPath;
    String accurace;

    public Historia() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPhothoPath() {
        return phothoPath;
    }

    public void setPhothoPath(String phothoPath) {
        this.phothoPath = phothoPath;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAccurace() {
        return accurace;
    }

    public void setAccurace(String accurace) {
        this.accurace = accurace;
    }
}
