/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.simit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jearm_000
 */

public class Comparendo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer numero;
    private Date fecha;
    private Date hora;
    private String direccion;
    private String comparendoElectronico;
    private Date fechaNotificacion;
    private String secretaria;
    private String agente;
    private List<Infraccion> infraccionList;
    private Conductor conductoresConductorId;

    public Comparendo() {
    }

    public Comparendo(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComparendoElectronico() {
        return comparendoElectronico;
    }

    public void setComparendoElectronico(String comparendoElectronico) {
        this.comparendoElectronico = comparendoElectronico;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public List<Infraccion> getInfraccionList() {
        return infraccionList;
    }

    public void setInfraccionList(List<Infraccion> infraccionList) {
        this.infraccionList = infraccionList;
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
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comparendo)) {
            return false;
        }
        Comparendo other = (Comparendo) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.simit.local.domain.Comparendo[ numero=" + numero + " ]";
    }
    
}
