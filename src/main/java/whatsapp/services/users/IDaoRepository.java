package whatsapp.services.users;

import whatsapp.models.Users;

public interface IDaoRepository {

	Users saveUser(Users users);
	
	Users findUserByNumber(String numer);
	
	Users updateStatus(String phoneNumber, String status);
	
	String updateProductUser(String phoneNumber, String name);
}
