package domain.business.organizaciones.apiHogares;

import domain.business.organizaciones.apiHogares.entidades.ConjuntoHogares;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIservice {

    @GET("hogares")
    Call<ConjuntoHogares> hogares(@Query("offset") int offset, @Header("Authorization") String bearer);
}