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

public class Infraccion implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer infraccionId;
    private String articulo;
    private String codigo;
    private String descripcion;
    private String salarios;
    private Comparendo comparendoNumero;

    public Infraccion() {
    }

    public Infraccion(Integer infraccionId) {
        this.infraccionId = infraccionId;
    }

    public Integer getInfraccionId() {
        return infraccionId;
    }

    public void setInfraccionId(Integer infraccionId) {
        this.infraccionId = infraccionId;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSalarios() {
        return salarios;
    }

    public void setSalarios(String salarios) {
        this.salarios = salarios;
    }

    public Comparendo getComparendoNumero() {
        return comparendoNumero;
    }

    public void setComparendoNumero(Comparendo comparendoNumero) {
        this.comparendoNumero = comparendoNumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infraccionId != null ? infraccionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infraccion)) {
            return false;
        }
        Infraccion other = (Infraccion) object;
        if ((this.infraccionId == null && other.infraccionId != null) || (this.infraccionId != null && !this.infraccionId.equals(other.infraccionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.simit.local.domain.Infraccion[ infraccionId=" + infraccionId + " ]";
    }
    
}
