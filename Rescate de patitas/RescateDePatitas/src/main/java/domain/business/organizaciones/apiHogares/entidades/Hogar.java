package domain.business.organizaciones.apiHogares.entidades;

import java.util.List;

public class Hogar {
    public String id;
    public String nombre;
    public Ubication ubicacion;
    public String telefono;
    public Admision admisiones;
    public int capacidad;
    public int lugares_disponibles;
    public boolean patio;
    public List<String> caracteristicas;

    public String getId() { return id; }

    public String getNombre() { return nombre; }

    public Ubication getUbicacion() { return ubicacion; }

    public String getTelefono() { return telefono; }

    public Admision getAdmisiones() { return admisiones; }

    public int getCapacidad() { return capacidad; }

    public int getLugares_disponibles() { return lugares_disponibles; }

    public boolean isPatio() { return patio; }

    public List<String> getCaracteristicas() { return caracteristicas; }
}
