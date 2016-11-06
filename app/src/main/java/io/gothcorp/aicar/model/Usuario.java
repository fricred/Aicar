package io.gothcorp.aicar.model;

/**
 * Created by jearm_000 on 05/11/2016.
 */

public class Usuario {
    private String nombres;
    private String apellidos;
    private String correo;
    private String nroCedula;
    private String placa;
    private String usuario;
    private String clave;
    private String facebookId;
    private String twitterI;
    private String googleId;


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNroCedula() {
        return nroCedula;
    }

    public void setNroCedula(String nroCedula) {
        this.nroCedula = nroCedula;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterI() {
        return twitterI;
    }

    public void setTwitterI(String twitterI) {
        this.twitterI = twitterI;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario1 = (Usuario) o;

        return usuario.equals(usuario1.usuario);

    }

    @Override
    public int hashCode() {
        return usuario.hashCode();
    }
}
