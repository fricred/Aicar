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

public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer numeroSolicitud;
    private Date fechaSolicitud;
    private String identificador;
    private String estado;
    private String tramitesRealizados;
    private String entidad;
    private Vehiculo vehiculosVehiculoId;
    private Persona personasPersonaId;

    public Solicitud() {
    }

    public Solicitud(Integer numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Integer getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Integer numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTramitesRealizados() {
        return tramitesRealizados;
    }

    public void setTramitesRealizados(String tramitesRealizados) {
        this.tramitesRealizados = tramitesRealizados;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Vehiculo getVehiculosVehiculoId() {
        return vehiculosVehiculoId;
    }

    public void setVehiculosVehiculoId(Vehiculo vehiculosVehiculoId) {
        this.vehiculosVehiculoId = vehiculosVehiculoId;
    }

    public Persona getPersonasPersonaId() {
        return personasPersonaId;
    }

    public void setPersonasPersonaId(Persona personasPersonaId) {
        this.personasPersonaId = personasPersonaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroSolicitud != null ? numeroSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.numeroSolicitud == null && other.numeroSolicitud != null) || (this.numeroSolicitud != null && !this.numeroSolicitud.equals(other.numeroSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.Solicitud[ numeroSolicitud=" + numeroSolicitud + " ]";
    }
    
}
