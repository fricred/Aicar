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

public class Blindaje implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer blindajeId;
    private String blindado;
    private String nivelBlindaje;
    private Date fechaBlindaje;
    private Date fechaDesblindaje;
    private Vehiculo vehiculosVehiculoId;

    public Blindaje() {
    }

    public Blindaje(Integer blindajeId) {
        this.blindajeId = blindajeId;
    }

    public Integer getBlindajeId() {
        return blindajeId;
    }

    public void setBlindajeId(Integer blindajeId) {
        this.blindajeId = blindajeId;
    }

    public String getBlindado() {
        return blindado;
    }

    public void setBlindado(String blindado) {
        this.blindado = blindado;
    }

    public String getNivelBlindaje() {
        return nivelBlindaje;
    }

    public void setNivelBlindaje(String nivelBlindaje) {
        this.nivelBlindaje = nivelBlindaje;
    }

    public Date getFechaBlindaje() {
        return fechaBlindaje;
    }

    public void setFechaBlindaje(Date fechaBlindaje) {
        this.fechaBlindaje = fechaBlindaje;
    }

    public Date getFechaDesblindaje() {
        return fechaDesblindaje;
    }

    public void setFechaDesblindaje(Date fechaDesblindaje) {
        this.fechaDesblindaje = fechaDesblindaje;
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
        hash += (blindajeId != null ? blindajeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blindaje)) {
            return false;
        }
        Blindaje other = (Blindaje) object;
        if ((this.blindajeId == null && other.blindajeId != null) || (this.blindajeId != null && !this.blindajeId.equals(other.blindajeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.Blindaje[ blindajeId=" + blindajeId + " ]";
    }
    
}
