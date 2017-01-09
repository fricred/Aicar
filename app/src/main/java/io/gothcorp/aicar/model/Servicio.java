package io.gothcorp.aicar.model;

/**
 * Created by jearm_000 on 20/11/2016.
 */

public class Servicio {
    private String name;
    private String url;
    private boolean runt;
    private boolean simit;
    private boolean sim;
    private boolean loaded;
    private int icon;
    private int alerts;
    private String entidad;

    public Servicio() {
    }


    public Servicio(String name, String url, int icon, String entidad) {
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.entidad = entidad;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isRunt() {
        return runt;
    }

    public void setRunt(boolean runt) {
        this.runt = runt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getAlerts() {
        return alerts;
    }

    public String getEntidad() {
        return entidad;
    }

    public boolean isSimit() {
        return simit;
    }

    public boolean isSim() {
        return sim;
    }

    public void setSim(boolean sim) {
        this.sim = sim;
    }

    public void setSimit(boolean simit) {
        this.simit = simit;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public void setAlerts(int alerts) {
        this.alerts = alerts;
    }
}
