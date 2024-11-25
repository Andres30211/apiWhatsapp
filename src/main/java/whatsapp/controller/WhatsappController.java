package whatsapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;
import whatsapp.servicesbot.ServicesBot;

import java.util.Map;

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

        String response = this.servicesBot.processOptiosMenu_1(from, messageBody);

        // Enviar la respuesta al usuario
        this.servicesBot.sendMessage(from, response);
    }

}
