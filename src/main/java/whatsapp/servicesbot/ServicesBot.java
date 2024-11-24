package whatsapp.servicesbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import whatsapp.models.Users;
import whatsapp.services.products.IDaoProducts;
import whatsapp.services.users.IDaoRepository;

@Service
public class ServicesBot {
	
	@Value("${twilio.whatsapp.number}")
	private String twilioWhatsappNumber;
	
	@Autowired
    private IDaoRepository daoRepository;
	
	@Autowired
    private IDaoProducts daoProducts;

	public ServicesBot() {
	}
	
	public void sendMessage(String addressee, String text) {
        Message.creator(
                new PhoneNumber(addressee), // Número del cliente
                new PhoneNumber(twilioWhatsappNumber), // Tu número de Twilio
                text
        ).create();
    }
	
	public String processMainManu(String from, String messageBody) {
		
		switch (messageBody) {
        case "1":
            this.daoRepository.updateStatus(from, "vendiendo");
            return "Seleccionaste *Vender producto*. Por favor, envía los detalles del producto.";
        case "2":
        	this.daoRepository.updateStatus(from, "agregando");
            return "Seleccionaste *Consultar Producto*. Por favor, envía el nombre o ID del producto.";
        case "3":
        	this.daoRepository.updateStatus(from, "init");
            return this.daoProducts.listProducts();
        case "4":
        	this.daoRepository.updateStatus(from, "consultando");
            return "Seleccionaste *Consultar producto*. Por favor, envía el nombre del producto.";
        default:
            return "🤖 Bienvenido al bot de Spring Boot!\n\n"
                    + "📳 Presiona el código según la actividad que quieras realizar.\n\n"
                    + "1. Vender producto 🛒.\n"
                    + "2. Agregar cantidad 📖.\n"
                    + "3. Listar productos 📚.\n"
                    + "4. Consultar Producto ✏.";
		}
	}
	
	public String processOptiosMenu_1(String from, String messageBody) {
		
		Users user = this.daoRepository.saveUser(from);
        String status = user.getStatus();
        String response = "";
        
        if(status.equals("init")) {
        	response = processMainManu(from, messageBody);
        	System.out.println(status);
        }else if(status.equals("vendiendo")) {
        	response = this.daoProducts.findByName(messageBody).toString();
//        	this.daoRepository.updateStatus(from, "init");
//        	this.sendMessage(WhatsappController.addressee, response);
        	System.out.println(status);
        }else if(status.equals("consultando")) {
        	response = this.daoProducts.findByName(messageBody);
        }

//        switch (messageBody) {
//            case "init":
//            	response = processMainManu(from, messageBody);
//                break;
//            case "crear":
//            	response = "¡Producto creado exitosamente! Escribe cualquier cosa para regresar al menú principal.";
//            	this.daoRepository.updateStatus(from, "init");
//                break;
//            case "consultar":
//            	response = "Consulta realizada. Escribe cualquier cosa para regresar al menú principal.";
//            	this.daoRepository.updateStatus(from, "init");
//                break;
//            case "listar":
//            	response = "Aquí está la lista de productos. Escribe cualquier cosa para regresar al menú principal.";
//            	this.daoRepository.updateStatus(from, "init");
//                break;
//            case "actualizar":
//            	response = "Producto actualizado. Escribe cualquier cosa para regresar al menú principal.";
//            	this.daoRepository.updateStatus(from, "init");
//                break;
//            default:
//            	response = "No entendí tu mensaje. Escribe cualquier cosa para regresar al menú principal.";
//            	this.daoRepository.updateStatus(from, "init");
//        }
        
        return response;
	}
}
