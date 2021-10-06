package domain.repositories.testMemoData;


import domain.business.EntidadPersistente;
import domain.business.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<EntidadPersistente> getData(Class type){
        List<EntidadPersistente> entidades = new ArrayList<>();
      /*  if(type.getName().equals(Rol.class.getName())){
            entidades = DataRol.getList();
        }
        else{
            if(type.getName().equals(Usuario.class.getName())){
                entidades = DataUsuario.getList();
            }
        }*/
        return entidades;
    }
}