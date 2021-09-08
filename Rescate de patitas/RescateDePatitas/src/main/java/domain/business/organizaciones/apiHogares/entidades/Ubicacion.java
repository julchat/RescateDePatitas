package domain.business.organizaciones.apiHogares.entidades;

import com.google.gson.annotations.SerializedName;


public class Ubicacion {
    String direccion;
    float lat;
    @SerializedName("long")
    float longitud;

    public String getDireccion() { return direccion; }

    public float getLat() { return lat; }

    public float getLongitud() { return longitud; }
}
