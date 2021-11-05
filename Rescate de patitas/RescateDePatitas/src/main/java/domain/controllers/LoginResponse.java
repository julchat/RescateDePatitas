package domain.controllers;

import json.JsonController;

public class LoginResponse {

    private String idSesion;

    public LoginResponse(String idSesion) { this.idSesion = idSesion; }

    public String getIdSesion() { return idSesion; }

    public void setIdSesion(String idSesion) { this.idSesion = idSesion; }

    public String transformar(){
        return JsonController.transformar(this);
    }
}