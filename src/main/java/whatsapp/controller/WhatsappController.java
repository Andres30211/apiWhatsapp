package whatsapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {

	@Value("${twilio.account.sid}")
	private String accountSid;
	
	@Value("${twilio.auth.token}")
	private String authToken;
	
	@Value("${twilio.whatsapp.from}")
	private String whatsappFrom;
	
	public WhatsappController() {
		Twilio.init(this.accountSid,this.authToken);
	}
	
	@PostMapping("/mensaje")
	public String mensaje(@RequestParam Map<String, String> requestBody) {
		
		String from = requestBody.get("From");
		String body = requestBody.get("Body");
		
		if("1".equals(body.trim())) {
			this.sendMessage(from, "hola!, este es un saludo automatico desde spring boot y twilio...");
		}else {
			this.sendMessage(from, "Lo siento no entendi el mensaje, envia 1 para poder responder...");
		}
		
		return "Mensaje procesado...";
	}
	
	private void sendMessage(String to, String message) {
		
		Message.creator(new PhoneNumber(to), new PhoneNumber(message), message);
	}
	
}
