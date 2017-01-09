package io.gothcorp.aicar.model.sim;

/**
 * Created by dixfricred on 08/01/2017.
 */

public class SimVO {
    private Conductor conductor;
    private Vehiculo vehiculo;

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
