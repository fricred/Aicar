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

public class Conductor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer conductorId;
    private String tipoDocumento;
    private String nombres;
    private String apellidos;
    private String tipoInfractor;
    private List<Tramite> tramiteList;
    private List<Vehiculo> vehiculoList;

    public Conductor() {
    }

    public Conductor(Integer conductorId) {
        this.conductorId = conductorId;
    }

    public Integer getConductorId() {
        return conductorId;
    }

    public void setConductorId(Integer conductorId) {
        this.conductorId = conductorId;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoInfractor() {
        return tipoInfractor;
    }

    public void setTipoInfractor(String tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public List<Tramite> getTramiteList() {
        return tramiteList;
    }

    public void setTramiteList(List<Tramite> tramiteList) {
        this.tramiteList = tramiteList;
    }

    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conductorId != null ? conductorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.conductorId == null && other.conductorId != null) || (this.conductorId != null && !this.conductorId.equals(other.conductorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.sim.local.domain.Conductor[ conductorId=" + conductorId + " ]";
    }
    
}
