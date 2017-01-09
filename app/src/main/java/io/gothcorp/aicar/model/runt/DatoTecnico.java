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

public class DatoTecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer datoTecnicoId;
    private String capacidadCarga;
    private String pesoBruto;
    private String capacidadPasajeros;
    private String capacidadPasajerosSentados;
    private String noEjes;
    private Vehiculo vehiculosVehiculoId;

    public DatoTecnico() {
    }

    public DatoTecnico(Integer datoTecnicoId) {
        this.datoTecnicoId = datoTecnicoId;
    }

    public Integer getDatoTecnicoId() {
        return datoTecnicoId;
    }

    public void setDatoTecnicoId(Integer datoTecnicoId) {
        this.datoTecnicoId = datoTecnicoId;
    }

    public String getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(String capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(String pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(String capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getCapacidadPasajerosSentados() {
        return capacidadPasajerosSentados;
    }

    public void setCapacidadPasajerosSentados(String capacidadPasajerosSentados) {
        this.capacidadPasajerosSentados = capacidadPasajerosSentados;
    }

    public String getNoEjes() {
        return noEjes;
    }

    public void setNoEjes(String noEjes) {
        this.noEjes = noEjes;
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
        hash += (datoTecnicoId != null ? datoTecnicoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoTecnico)) {
            return false;
        }
        DatoTecnico other = (DatoTecnico) object;
        if ((this.datoTecnicoId == null && other.datoTecnicoId != null) || (this.datoTecnicoId != null && !this.datoTecnicoId.equals(other.datoTecnicoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.DatoTecnico[ datoTecnicoId=" + datoTecnicoId + " ]";
    }
    
}
