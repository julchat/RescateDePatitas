package domain.security.password;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ValidadorPasswordComun extends Validador {
    @Override
    public String esValida(String usuario, String password){
        Path path = Paths.get("src/Main/resources/utils/10k-most-common.txt");
        Stream<String> stream;
        try {
            stream = Files.lines(path);
            if(stream.noneMatch(palabra -> palabra.equals(password))) {
                return passwordStatus.getStatusOK();
            }
            else {
                return passwordStatus.getStatusSimple();
            }
        } catch (IOException exception){
            return passwordStatus.getStatusSimple();
        }
    }
}
