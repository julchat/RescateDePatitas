package domain.model.api;

import domain.model.api.entities.UserData;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface APIservice {

    @GET("users")
    Call<List<UserData>> users();

    @GET("admins")
    Call<List<UserData>> admins();
}
