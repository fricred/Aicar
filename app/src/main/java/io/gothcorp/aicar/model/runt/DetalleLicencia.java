/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.runt;

import java.io.Serializable;

/**
 *
 * @author jearm_000
 */

public class DetalleLicencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer detalleLicenciaId;
    private String categoria;
    private String fechaExpedicion;
    private String fechaVencimiento;
    private String fechaVencimientoExamen;
    private LicenciaRunt licenciasNumeroLicencia;

    public DetalleLicencia() {
    }

    public DetalleLicencia(Integer detalleLicenciaId) {
        this.detalleLicenciaId = detalleLicenciaId;
    }

    public Integer getDetalleLicenciaId() {
        return detalleLicenciaId;
    }

    public void setDetalleLicenciaId(Integer detalleLicenciaId) {
        this.detalleLicenciaId = detalleLicenciaId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaVencimientoExamen() {
        return fechaVencimientoExamen;
    }

    public void setFechaVencimientoExamen(String fechaVencimientoExamen) {
        this.fechaVencimientoExamen = fechaVencimientoExamen;
    }

    public LicenciaRunt getLicenciasNumeroLicencia() {
        return licenciasNumeroLicencia;
    }

    public void setLicenciasNumeroLicencia(LicenciaRunt licenciasNumeroLicencia) {
        this.licenciasNumeroLicencia = licenciasNumeroLicencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleLicenciaId != null ? detalleLicenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleLicencia)) {
            return false;
        }
        DetalleLicencia other = (DetalleLicencia) object;
        if ((this.detalleLicenciaId == null && other.detalleLicenciaId != null) || (this.detalleLicenciaId != null && !this.detalleLicenciaId.equals(other.detalleLicenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.DetalleLicencia[ detalleLicenciaId=" + detalleLicenciaId + " ]";
    }
    
}
