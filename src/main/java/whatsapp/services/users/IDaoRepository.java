package whatsapp.services.users;

import whatsapp.models.Users;

public interface IDaoRepository {

	Users saveUser(String phoneNumber);
	
	Users findUserByNumber(String numer);
	
	Users updateStatus(String phoneNumber, String status);
}
