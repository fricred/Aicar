/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gothcorp.aicar.model.runt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jearm_000
 */

public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer vehiculoId;
    private String noLicenciaTransito;
    private String estado;
    private String tipoServicio;
    private String claseVehiculo;
    private String marca;
    private String linea;
    private String modelo;
    private String color;
    private String noMotor;
    private String noChasis;
    private String noVin;
    private String cilindraje;
    private String tipoCarroceria;
    private Date fechaMatricula;
    private String tieneGravamenes;
    private String organismoTransito;
    private String prendas;
    private String clasificacion;
    private String esRegrabadoMotor;
    private String esRegrabadoChasis;
    private String esRegrabadoVin;
    private String esRegrabadoSerie;
    private String clasicoAntiguo;
    private String tipoCombustible;
    private String pasajeorsSentados;
    private String tarjetaServicio;
    private String noPlaca;
    private String repotenciado;
    private List<TarjetaOperacion> tarjetaOperacionList;
    private List<CertificacionDijin> certificacionDijinList;
    private List<Blindaje> blindajeList;
    private List<CertificadoDesintegracion> certificadoDesintegracionList;
    private List<Rtms> rtmsList;
    private List<DatoTecnico> datoTecnicoList;
    private List<Poliza> polizaList;
    private List<CompromisoPoliza> compromisoPolizaList;
    private List<Solicitud> solicitudList;

    public Vehiculo() {
    }

    public Vehiculo(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getNoLicenciaTransito() {
        return noLicenciaTransito;
    }

    public void setNoLicenciaTransito(String noLicenciaTransito) {
        this.noLicenciaTransito = noLicenciaTransito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getClaseVehiculo() {
        return claseVehiculo;
    }

    public void setClaseVehiculo(String claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNoMotor() {
        return noMotor;
    }

    public void setNoMotor(String noMotor) {
        this.noMotor = noMotor;
    }

    public String getNoChasis() {
        return noChasis;
    }

    public void setNoChasis(String noChasis) {
        this.noChasis = noChasis;
    }

    public String getNoVin() {
        return noVin;
    }

    public void setNoVin(String noVin) {
        this.noVin = noVin;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getTieneGravamenes() {
        return tieneGravamenes;
    }

    public void setTieneGravamenes(String tieneGravamenes) {
        this.tieneGravamenes = tieneGravamenes;
    }

    public String getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(String organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getPrendas() {
        return prendas;
    }

    public void setPrendas(String prendas) {
        this.prendas = prendas;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getEsRegrabadoMotor() {
        return esRegrabadoMotor;
    }

    public void setEsRegrabadoMotor(String esRegrabadoMotor) {
        this.esRegrabadoMotor = esRegrabadoMotor;
    }

    public String getEsRegrabadoChasis() {
        return esRegrabadoChasis;
    }

    public void setEsRegrabadoChasis(String esRegrabadoChasis) {
        this.esRegrabadoChasis = esRegrabadoChasis;
    }

    public String getEsRegrabadoVin() {
        return esRegrabadoVin;
    }

    public void setEsRegrabadoVin(String esRegrabadoVin) {
        this.esRegrabadoVin = esRegrabadoVin;
    }

    public String getEsRegrabadoSerie() {
        return esRegrabadoSerie;
    }

    public void setEsRegrabadoSerie(String esRegrabadoSerie) {
        this.esRegrabadoSerie = esRegrabadoSerie;
    }

    public String getClasicoAntiguo() {
        return clasicoAntiguo;
    }

    public void setClasicoAntiguo(String clasicoAntiguo) {
        this.clasicoAntiguo = clasicoAntiguo;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getPasajeorsSentados() {
        return pasajeorsSentados;
    }

    public void setPasajeorsSentados(String pasajeorsSentados) {
        this.pasajeorsSentados = pasajeorsSentados;
    }

    public String getTarjetaServicio() {
        return tarjetaServicio;
    }

    public void setTarjetaServicio(String tarjetaServicio) {
        this.tarjetaServicio = tarjetaServicio;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    public String getRepotenciado() {
        return repotenciado;
    }

    public void setRepotenciado(String repotenciado) {
        this.repotenciado = repotenciado;
    }

    public List<TarjetaOperacion> getTarjetaOperacionList() {
        return tarjetaOperacionList;
    }

    public void setTarjetaOperacionList(List<TarjetaOperacion> tarjetaOperacionList) {
        this.tarjetaOperacionList = tarjetaOperacionList;
    }

    public List<CertificacionDijin> getCertificacionDijinList() {
        return certificacionDijinList;
    }

    public void setCertificacionDijinList(List<CertificacionDijin> certificacionDijinList) {
        this.certificacionDijinList = certificacionDijinList;
    }

    public List<Blindaje> getBlindajeList() {
        return blindajeList;
    }

    public void setBlindajeList(List<Blindaje> blindajeList) {
        this.blindajeList = blindajeList;
    }

    public List<CertificadoDesintegracion> getCertificadoDesintegracionList() {
        return certificadoDesintegracionList;
    }

    public void setCertificadoDesintegracionList(List<CertificadoDesintegracion> certificadoDesintegracionList) {
        this.certificadoDesintegracionList = certificadoDesintegracionList;
    }

    public List<Rtms> getRtmsList() {
        return rtmsList;
    }

    public void setRtmsList(List<Rtms> rtmsList) {
        this.rtmsList = rtmsList;
    }

    public List<DatoTecnico> getDatoTecnicoList() {
        return datoTecnicoList;
    }

    public void setDatoTecnicoList(List<DatoTecnico> datoTecnicoList) {
        this.datoTecnicoList = datoTecnicoList;
    }

    public List<Poliza> getPolizaList() {
        return polizaList;
    }

    public void setPolizaList(List<Poliza> polizaList) {
        this.polizaList = polizaList;
    }

    public List<CompromisoPoliza> getCompromisoPolizaList() {
        return compromisoPolizaList;
    }

    public void setCompromisoPolizaList(List<CompromisoPoliza> compromisoPolizaList) {
        this.compromisoPolizaList = compromisoPolizaList;
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
        hash += (vehiculoId != null ? vehiculoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.vehiculoId == null && other.vehiculoId != null) || (this.vehiculoId != null && !this.vehiculoId.equals(other.vehiculoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.runt.local.domain.Vehiculo[ vehiculoId=" + vehiculoId + " ]";
    }
    
}
