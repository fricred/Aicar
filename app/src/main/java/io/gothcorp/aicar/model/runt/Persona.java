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

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer personaId;
    private String tipoDocumento;
    private Integer numeroDocumento;
    private String nombres;
    private String apellidos;
    private Integer nroInscripcion;
    private String fehchaInscripcion;
    private String estadoConductor;
    private String estadoPersona;
    private List<LicenciaRunt> licenciaRuntList;
    private List<Solicitud> solicitudList;

    public Persona() {
    }

    public Persona(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public Integer getNroInscripcion() {
        return nroInscripcion;
    }

    public void setNroInscripcion(Integer nroInscripcion) {
        this.nroInscripcion = nroInscripcion;
    }

    public String getFehchaInscripcion() {
        return fehchaInscripcion;
    }

    public void setFehchaInscripcion(String fehchaInscripcion) {
        this.fehchaInscripcion = fehchaInscripcion;
    }

    public String getEstadoConductor() {
        return estadoConductor;
    }

    public void setEstadoConductor(String estadoConductor) {
        this.estadoConductor = estadoConductor;
    }

    public String getEstadoPersona() {
        return estadoPersona;
    }

    public void setEstadoPersona(String estadoPersona) {
        this.estadoPersona = estadoPersona;
    }

    public List<LicenciaRunt> getLicenciaRuntList() {
        return licenciaRuntList;
    }

    public void setLicenciaRuntList(List<LicenciaRunt> licenciaRuntList) {
        this.licenciaRuntList = licenciaRuntList;
    }

    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.Persona[ personaId=" + personaId + " ]";
    }
    
}
