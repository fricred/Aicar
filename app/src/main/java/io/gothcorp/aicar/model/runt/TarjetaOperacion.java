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

public class TarjetaOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer tarjetaId;
    private String empresaAfiliadora;
    private String modalidadTransporte;
    private String modalidadServicio;
    private String radioAccion;
    private Date fechaExpedicion;
    private Date fechaVencimiento;
    private String estado;
    private Vehiculo vehiculosVehiculoId;

    public TarjetaOperacion() {
    }

    public TarjetaOperacion(Integer tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    public Integer getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(Integer tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    public String getEmpresaAfiliadora() {
        return empresaAfiliadora;
    }

    public void setEmpresaAfiliadora(String empresaAfiliadora) {
        this.empresaAfiliadora = empresaAfiliadora;
    }

    public String getModalidadTransporte() {
        return modalidadTransporte;
    }

    public void setModalidadTransporte(String modalidadTransporte) {
        this.modalidadTransporte = modalidadTransporte;
    }

    public String getModalidadServicio() {
        return modalidadServicio;
    }

    public void setModalidadServicio(String modalidadServicio) {
        this.modalidadServicio = modalidadServicio;
    }

    public String getRadioAccion() {
        return radioAccion;
    }

    public void setRadioAccion(String radioAccion) {
        this.radioAccion = radioAccion;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        hash += (tarjetaId != null ? tarjetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaOperacion)) {
            return false;
        }
        TarjetaOperacion other = (TarjetaOperacion) object;
        if ((this.tarjetaId == null && other.tarjetaId != null) || (this.tarjetaId != null && !this.tarjetaId.equals(other.tarjetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.TarjetaOperacion[ tarjetaId=" + tarjetaId + " ]";
    }
    
}
