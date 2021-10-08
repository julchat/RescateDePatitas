package mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.io.InputStream;

public class JsonMapper {
    private ObjectMapper objectMapper;

    public JsonMapper() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public <T> T fromJson(String json, Class<T> typeReference) {
        try {
            return this.objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading a json", e);
        }
    }

    public InputStream loadJSonFromFile(String fileName) {
        return JsonMapper.class.getClassLoader().getResourceAsStream(fileName);
    }
}
