package domain.business.organizaciones.apiHogares;

import domain.business.organizaciones.apiHogares.entidades.ConjuntoHogares;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class APIhogares {

    private static APIhogares instance = null;
    private static final String bearer = "Bearer whE1rezGurK5jIZPcLU65frDbH22JbmSMp2OXQXDke7Skj9uV1SX1kZE0d32";
    private static final String urlAPI = "https://api.refugiosdds.com.ar/api/";
    private Retrofit retrofit;

    public APIhogares() {
        this.retrofit = new Retrofit.Builder().
                baseUrl(urlAPI).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public static APIhogares getInstance() {
        if(instance == null) {
            instance = new APIhogares();
        }
        return instance;
    }

    public List<Hogar> conjuntoHogares(int offset) throws IOException {
        APIservice apiService = this.retrofit.create(APIservice.class);

        Call<ConjuntoHogares> pedidoHogares = apiService.hogares(offset, bearer);
        Response<ConjuntoHogares> respuestaHogares = pedidoHogares.execute();

        return respuestaHogares.body().getHogares();
    }

    public int cantidadPaginas() throws IOException {
        return this.cantidadHogares() / 10;
    }

    public int cantidadHogares() throws IOException {
        APIservice apiService = this.retrofit.create(APIservice.class);
        Call<ConjuntoHogares> pedidoHogares = apiService.hogares(1, bearer);
        Response<ConjuntoHogares> respuestaHogares = pedidoHogares.execute();
        return respuestaHogares.body().getTotal();
    }
}