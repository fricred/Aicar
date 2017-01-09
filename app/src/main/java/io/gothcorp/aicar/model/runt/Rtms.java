/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.runt;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jearm_000
 */

public class Rtms implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer rtmId;
    private String tipoRevision;
    private Date fechaExpedicion;
    private Date fechaVigencia;
    private String cdaExpide;
    private String vigente;
    private Vehiculo vehiculosVehiculoId;

    public Rtms() {
    }

    public Rtms(Integer rtmId) {
        this.rtmId = rtmId;
    }

    public Integer getRtmId() {
        return rtmId;
    }

    public void setRtmId(Integer rtmId) {
        this.rtmId = rtmId;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }

    public void setTipoRevision(String tipoRevision) {
        this.tipoRevision = tipoRevision;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public String getCdaExpide() {
        return cdaExpide;
    }

    public void setCdaExpide(String cdaExpide) {
        this.cdaExpide = cdaExpide;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public Vehiculo getVehiculosVehiculoId() {
        return vehiculosVehiculoId;
    }

    public void setVehiculosVehiculoId(Vehiculo vehiculosVehiculoId) {
        this.vehiculosVehiculoId = vehiculosVehiculoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rtmId != null ? rtmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rtms)) {
            return false;
        }
        Rtms other = (Rtms) object;
        if ((this.rtmId == null && other.rtmId != null) || (this.rtmId != null && !this.rtmId.equals(other.rtmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.Rtms[ rtmId=" + rtmId + " ]";
    }
    
}
