package whatsapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

import java.awt.event.FocusEvent.Cause;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {
	
	public static final String ACCOUNT_SID = "AC0bb2ec721155853bf33eb4a2a188e634";
    public static final String AUTH_TOKEN = "bc47944aa459fdb61346a77f651a47c6";
	
    @PostConstruct
    public void init() throws Exception{
    	if(ACCOUNT_SID != null && AUTH_TOKEN != null) {
    		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    	}else {
    		throw new Exception("No se pudo conectar");
    	}
    	
    }
    
	@GetMapping("/mensaje")
	public String mensaje() {
		
		Message message = Message
				.creator(
						new PhoneNumber("whatsapp:+573022458804"),
						new PhoneNumber("whatsapp:+14155238886"),
						"Ahora podemos hacer cualquier cosa...")
				.create();
		
		return message.getBody();
	}
	
	@PostMapping("/peticion")
    public String procesarMensajeEntrante(@RequestParam Map<String, String> body) {
        String from = body.get("From"); // Número del remitente
        String messageBody = body.get("Body"); // Cuerpo del mensaje

        // Responder al remitente
        Message response = Message
                .creator(
                        new PhoneNumber(from), // Responder al remitente
                        new PhoneNumber("whatsapp:+14155238886"), // Twilio
                        "Recibí tu mensaje: " + messageBody)
                .create();

        return "Mensaje recibido y respondido";
    }
	
	
}
