package domain.business.api;

import domain.business.Administrador;
import domain.business.User;
import domain.business.Usuario;
import domain.business.api.entities.UserData;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIarrp {

    private static APIarrp instance = null;
    private String urlAPI = "";
    private Retrofit retrofit;

    public APIarrp() {
        this.retrofit = new Retrofit.Builder().
                baseUrl(urlAPI).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public static APIarrp getInstance() {
        if(instance == null) {
            instance = new APIarrp();
        }
        return instance;
    }

    public List<Usuario> usuarios() throws IOException {
        APIservice apiService = this.retrofit.create(APIservice.class);

        Call<List<UserData>> pedidoUsuarios = apiService.users();
        Response<List<UserData>> respuestaUsuarios = pedidoUsuarios.execute();

        List<UserData> usuariosData = respuestaUsuarios.body();

        List<Usuario> usuarios = new ArrayList<>();
        for(UserData userData : usuariosData) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.mapearUsuario(userData);
            usuarios.add(nuevoUsuario);
        }

        return usuarios;
    }

    public List<Administrador> administradores() throws IOException {
        APIservice apiService = this.retrofit.create(APIservice.class);

        Call<List<UserData>> pedidoAdministradores = apiService.users();
        Response<List<UserData>> respuestaAdministradores = pedidoAdministradores.execute();

        List<UserData> adminsData = respuestaAdministradores.body();

        List<Administrador> administradores = new ArrayList<>();
        for(UserData userData : adminsData) {
            Administrador nuevoAdmin = new Administrador();
            nuevoAdmin.mapearUsuario(userData);
            administradores.add(nuevoAdmin);
        }

        return administradores;
    }

}
