package domain.password;

import exception.ReadFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ValidadorPasswordComun implements Validador {
    @Override
    public boolean esValido(String usuario, String password){
        Path path = Paths.get("resources/10k-most-common.txt");
        Stream<String> stream;

        try {
            stream = Files.lines(path);
            return stream.noneMatch(palabra -> palabra.equals(password));
        } catch (IOException e){
            throw new ReadFileException();
        }
    }

}
