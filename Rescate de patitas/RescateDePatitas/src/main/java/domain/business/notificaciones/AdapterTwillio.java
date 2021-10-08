package domain.business.notificaciones;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
//import org.mortbay.util.IO;

import java.io.IOException;

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

    public void enviarEmail(String correoDestinatario, String asunto,  String contenido){
        Email from = new Email("test@example.com");
        Email to = new Email(correoDestinatario); // use your own email address here
        Content content = new Content("text/html", "and <em>easy</em> to do anywhere with <strong>Java</strong>");
        Mail mail = new Mail(from, asunto, to, content);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try{
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());

        }  catch (IOException e){
            System.out.println("hubo excepcion");
        }
    }
}
