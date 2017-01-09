/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.sim;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jearm_000
 */

public class Tramite implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer noRadicado;
    private String identificador;
    private Date fechaSolicitud;
    private String estado;
    private String resultado;
    private Date disponibleEntrega;
    private Vehiculo vehiculoVehiculoId;
    private Conductor conductoresConductorId;

    public Tramite() {
    }

    public Tramite(Integer noRadicado) {
        this.noRadicado = noRadicado;
    }

    public Integer getNoRadicado() {
        return noRadicado;
    }

    public void setNoRadicado(Integer noRadicado) {
        this.noRadicado = noRadicado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getDisponibleEntrega() {
        return disponibleEntrega;
    }

    public void setDisponibleEntrega(Date disponibleEntrega) {
        this.disponibleEntrega = disponibleEntrega;
    }

    public Vehiculo getVehiculoVehiculoId() {
        return vehiculoVehiculoId;
    }

    public void setVehiculoVehiculoId(Vehiculo vehiculoVehiculoId) {
        this.vehiculoVehiculoId = vehiculoVehiculoId;
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
        hash += (noRadicado != null ? noRadicado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tramite)) {
            return false;
        }
        Tramite other = (Tramite) object;
        if ((this.noRadicado == null && other.noRadicado != null) || (this.noRadicado != null && !this.noRadicado.equals(other.noRadicado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.sim.local.domain.Tramite[ noRadicado=" + noRadicado + " ]";
    }
    
}
