/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.simit;

import java.io.Serializable;

/**
 *
 * @author jearm_000
 */

public class LicenciaSimit implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer licenciaId;
    private String fechaVencimiento;
    private String categoria;
    private String secretaria;
    private Conductor conductoresConductorId;

    public LicenciaSimit() {
    }

    public LicenciaSimit(Integer licenciaId) {
        this.licenciaId = licenciaId;
    }

    public Integer getLicenciaId() {
        return licenciaId;
    }

    public void setLicenciaId(Integer licenciaId) {
        this.licenciaId = licenciaId;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
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
        hash += (licenciaId != null ? licenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenciaSimit)) {
            return false;
        }
        LicenciaSimit other = (LicenciaSimit) object;
        if ((this.licenciaId == null && other.licenciaId != null) || (this.licenciaId != null && !this.licenciaId.equals(other.licenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.simit.local.domain.LicenciaSimit[ licenciaId=" + licenciaId + " ]";
    }
    
}
