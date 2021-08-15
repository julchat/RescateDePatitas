package domain.notificaciones;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class AdapterTwillio {
    public static final String ACCOUNT_SID = "AC4e03993b0699c3d113ede67873302ac2";
    public static final String AUTH_TOKEN = "57b304a5321c0f45211093493b012b1c";
    public AdapterTwillio(){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    public String addChar(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }

    public void enviarSMS(String numeroDestinatario, String mensaje){
        Message mensajeAMandar = Message.creator(
                new com.twilio.type.PhoneNumber(numeroDestinatario),
                new com.twilio.type.PhoneNumber("+13366630409"),
                mensaje
        ).create();
        System.out.println(mensajeAMandar.getSid());
    }
    public void enviarWhatsapp(String numeroDestinatario, String mensaje){
        char elNueve = '9';
        Message mensajeAMandar = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + addChar(numeroDestinatario, elNueve, 3)),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                mensaje
        ).create();
        System.out.println(mensajeAMandar.getSid());
    }
}
