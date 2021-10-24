package json;

import com.google.gson.Gson;

public class JsonController {

    public static String transformar(Object model){
        return new Gson().toJson(model);
    }
}
