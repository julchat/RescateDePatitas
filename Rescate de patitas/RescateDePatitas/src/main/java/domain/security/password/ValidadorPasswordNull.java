package domain.security.password;

public class ValidadorPasswordNull implements Validador{

    @Override
    public boolean esValida(String password) {
        if (password.isEmpty()) {
            //throw new NullException();
            return false;
        }
        else {
            return true;
        }
    }
}
