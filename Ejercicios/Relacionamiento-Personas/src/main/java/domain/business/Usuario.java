package domain.business;

import domain.business.api.entities.UserData;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@DiscriminatorColumn(name = "usuario")
public class Usuario extends Actor{
}
