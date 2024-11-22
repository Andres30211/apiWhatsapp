package whatsapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {
	
	public static final String ACCOUNT_SID = "AC0bb2ec721155853bf33eb4a2a188e634";
    public static final String AUTH_TOKEN = "bc47944aa459fdb61346a77f651a47c6";
	
	@GetMapping("/mensaje")
	public String mensaje() {
		
		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
		Message message = Message
				.creator(
						new PhoneNumber("whatsapp:+573022458804"),
						new PhoneNumber("whatsapp:+14155238886"),
						"Ahora podemos hacer cualquier cosa...")
				.create();
		
		return message.getBody();
	}
	
	
}
