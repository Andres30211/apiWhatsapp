package whatsapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;
import whatsapp.models.Products;
import whatsapp.models.Users;
import whatsapp.services.products.IDaoProducts;
import whatsapp.services.users.IDaoRepository;
import whatsapp.servicesbot.ServicesBot;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {
	
	@Value("${twilio.account.sid}")
	private String ACCOUNT_SID;
	
	@Value("${twilio.auth.token}")
	private String AUTH_TOKEN;
    
    @Autowired
    private ServicesBot servicesBot;
    
    public static String addressee;
    
    @PostConstruct
    public void init() throws Exception{
    	if(ACCOUNT_SID != null && AUTH_TOKEN != null) {
    		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    	}else {
    		throw new Exception("No se pudo conectar");
    	}
    	
    }
	
	@PostMapping("/post")
    public void procesarMensajeEntrante(@RequestParam Map<String, String> body) {
		addressee = body.get("From");
        String from = body.get("From"); // Número del remitente
        String messageBody = body.get("Body"); // Cuerpo del mensaje

//        String response = this.servicesBot.processOptiosMenu_1(from, messageBody);

        // Enviar la respuesta al usuario
        if(messageBody.equals(1)) {
        	 Message.creator(
                     new PhoneNumber(from), // Número del cliente
                     new PhoneNumber("whatsapp:+14155238886"), // Tu número de Twilio
                     "jummm"
             ).create();
        }
    }

}
