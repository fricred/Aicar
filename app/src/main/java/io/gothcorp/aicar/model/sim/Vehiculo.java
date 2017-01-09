/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.sim;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jearm_000
 */

public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer vehiculoId;
    private String placa;
    private String noLicencia;
    private String tipo;
    private String servicio;
    private List<Tramite> tramiteList;
    private Conductor conductoresConductorId;

    public Vehiculo() {
    }

    public Vehiculo(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public List<Tramite> getTramiteList() {
        return tramiteList;
    }

    public void setTramiteList(List<Tramite> tramiteList) {
        this.tramiteList = tramiteList;
    }

    public Conductor getConductoresConductorId() {
        return conductoresConductorId;
    }

    public void setConductoresConductorId(Conductor conductoresConductorId) {
        this.conductoresConductorId = conductoresConductorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehiculoId != null ? vehiculoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.vehiculoId == null && other.vehiculoId != null) || (this.vehiculoId != null && !this.vehiculoId.equals(other.vehiculoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.sim.local.domain.Vehiculo[ vehiculoId=" + vehiculoId + " ]";
    }
    
}
