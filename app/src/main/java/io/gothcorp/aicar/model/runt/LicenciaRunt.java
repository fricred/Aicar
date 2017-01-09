/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.runt;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jearm_000
 */

public class LicenciaRunt implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer numeroLicencia;
    private String otExpide;
    private String fechaExpedicion;
    private String estadoLicencia;
    private Persona personasPersonaId;
    private List<DetalleLicencia> detalleLicenciaList;

    public LicenciaRunt() {
    }

    public LicenciaRunt(Integer numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Integer getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(Integer numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getOtExpide() {
        return otExpide;
    }

    public void setOtExpide(String otExpide) {
        this.otExpide = otExpide;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getEstadoLicencia() {
        return estadoLicencia;
    }

    public void setEstadoLicencia(String estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    public Persona getPersonasPersonaId() {
        return personasPersonaId;
    }

    public void setPersonasPersonaId(Persona personasPersonaId) {
        this.personasPersonaId = personasPersonaId;
    }

    public List<DetalleLicencia> getDetalleLicenciaList() {
        return detalleLicenciaList;
    }

    public void setDetalleLicenciaList(List<DetalleLicencia> detalleLicenciaList) {
        this.detalleLicenciaList = detalleLicenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroLicencia != null ? numeroLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenciaRunt)) {
            return false;
        }
        LicenciaRunt other = (LicenciaRunt) object;
        if ((this.numeroLicencia == null && other.numeroLicencia != null) || (this.numeroLicencia != null && !this.numeroLicencia.equals(other.numeroLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.LicenciaRunt[ numeroLicencia=" + numeroLicencia + " ]";
    }
    
}
