package io.gothcorp.aicar.model;

/**
 * Created by jearm_000 on 20/11/2016.
 */

public class Servicio {
    private String name;
    private String url;
    private int icon;
    private int alerts;

    public Servicio() {
    }


    public Servicio(String name, String url, int icon, int alerts) {
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.alerts = alerts;
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

    public void setAlerts(int alerts) {
        this.alerts = alerts;
    }
}
