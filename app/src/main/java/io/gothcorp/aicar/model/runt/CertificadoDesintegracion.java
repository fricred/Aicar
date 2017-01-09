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

public class CertificadoDesintegracion implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer numero;
    private Date fechaExpedicion;
    private String estado;
    private String entidadDesintegradora;
    private Vehiculo vehiculosVehiculoId;

    public CertificadoDesintegracion() {
    }

    public CertificadoDesintegracion(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEntidadDesintegradora() {
        return entidadDesintegradora;
    }

    public void setEntidadDesintegradora(String entidadDesintegradora) {
        this.entidadDesintegradora = entidadDesintegradora;
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
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CertificadoDesintegracion)) {
            return false;
        }
        CertificadoDesintegracion other = (CertificadoDesintegracion) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.CertificadoDesintegracion[ numero=" + numero + " ]";
    }
    
}
