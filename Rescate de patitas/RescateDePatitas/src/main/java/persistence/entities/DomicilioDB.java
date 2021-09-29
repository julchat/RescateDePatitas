package persistence.entities;


import domain.business.Ubicacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Domicilio")
public class DomicilioDB extends EntidadPersistente {

    @Column(name = "Provincia")
    private String provincia;

    @Column(name = "Localidad")
    private String localidad;

    @Column(name = "Codigo Postal")
    private int codigoPostal;

    @Column(name = "Calle")
    private String calle;

    @Column(name = "Numero")
    private int numero;

    @Column(name = "Departamento")
    private int departamento;

    @Column(name = "Piso")
    private int piso;

    @Transient
    private Ubicacion ubicacion;
}