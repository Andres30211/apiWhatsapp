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
		try {
			Message.creator(
	                new PhoneNumber(addressee), // Número del cliente
	                new PhoneNumber(twilioWhatsappNumber), // Tu número de Twilio
	                text
	        ).create();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        
    }
	
	public String processMainManu(String from, String messageBody) {
		
		switch (messageBody) {
        case "1":
            this.daoRepository.updateStatus(from, "vendiendo");
            return "Seleccionaste *Vender producto*. Por favor, envía los detalles del producto.";
        case "2":
        	this.daoRepository.updateStatus(from, "agregando");
            return "Seleccionaste *Agregar producto*. Por favor, envía los detalles del producto.";
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
		
		Users user = new Users(from, "init");
		user = this.daoRepository.saveUser(user);
        String status = user.getStatus();
        String response = "";
               
        if(status.equals("init")) {
        	response = processMainManu(from, messageBody);
        }else if(status.equals("vendiendo")) {
        	System.out.println(status);
        	if(this.daoProducts.findByName(messageBody) != null) {
        		this.daoRepository.updateStatus(from, "restandoCantidad");
        		this.daoRepository.updateProductUser(from, messageBody);
        		response = this.daoProducts.findByNameFill(messageBody).toString();
        	}else {
        		response = "No pude encontrar productos con esos datos, necesito mas detalles !";
        	}
        }else if(status.equals("restandoCantidad")) {
        	System.out.println(status);
        	String name = user.getCurrentProduct();
        	System.out.println(name);
        	response = this.daoProducts.sell(name, messageBody);
        	this.daoRepository.updateStatus(from, "init");
        }else if(status.equals("agregando")) {
        	System.out.println(status);
        	if(this.daoProducts.findByName(messageBody) != null) {
        		this.daoRepository.updateStatus(from, "agregandoCantidad");
        		this.daoRepository.updateProductUser(from, messageBody);
        		response = this.daoProducts.findByNameAdd(messageBody).toString();
        	}else {
        		response = "No pude encontrar productos con esos datos, necesito mas detalles !";
        	}
        }else if(status.equals("agregandoCantidad")) {
        	System.out.println(status);
        	String name = user.getCurrentProduct();
        	System.out.println(name);
        	response = this.daoProducts.saveCantProduct(name, messageBody);
        	this.daoRepository.updateStatus(from, "init");
    	}else if(status.equals("consultando")) {
        	if(this.daoProducts.findByName(messageBody) != null) {
        		response = this.daoProducts.findByName(messageBody);
        		this.daoRepository.updateStatus(from, "init");
        	}else {
        		response = "No pude encontrar productos con esos datos, necesito mas detalles !";
        	}
        }
        return response;
	}
}
