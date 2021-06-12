package excepciones;

public class ReadFileException extends RuntimeException {
    public ReadFileException() {
        super("Error al intentar leer un archivo");
    }
}
