package persistence.entities;

import domain.business.Persona;
import domain.business.SexoMascota;
import domain.business.TipoAnimal;
import domain.business.caracteristicas.CaracteristicaMascota;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Mascota")
public class MascotaDB extends EntidadPersistente {

    @Column(name = "Nombre")
    private String nombreMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo Animal")
    private TipoAnimal tipoAnimal;

    @Column(name = "Apodo")
    private String apodoMascota;

    @Column(name = "Edad")
    private int edadMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sexo Animal")
    private SexoMascota sexoMascota;

    @Column(name = "Descripcion")
    private String descripcionMascota;

    @OneToMany(mappedBy = "Mascota")
    @Column(name = "Fotos")
    private List<Foto> fotos;

    @OneToMany(mappedBy = "Mascota")
    @Column(name = "Caracteristicas")
    private List<CaracteristicaMascota> caracteristicasMascota;

    @Column(name = "¿Esta perdida?")
    private boolean estaPerdida;

    // TODO: duda en esta, si es estaAdoptada o estaDisponibleParaAdoptar
    @Column(name = "Disponible para adoptar")
    private boolean estaAdoptada;

    @ManyToOne
    @JoinColumn(name = "Encargado", referencedColumnName = "id")
    private PersonaDB encargado;

}
