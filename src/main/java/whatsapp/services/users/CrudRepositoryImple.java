package whatsapp.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import whatsapp.models.Users;

@Service
public class CrudRepositoryImple implements IDaoRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ICrudRepository crudRepository;
	
	@Override
	public Users saveUser(Users users) {
		Users user = new Users();
		try {
			 user = this.findUserByNumber(users.getPhoneNumber());
			if(user == null) {
				user = new Users(users.getPhoneNumber(), users.getStatus());
				this.crudRepository.save(user);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return user;
	}
	
	@Override
	public Users updateStatus(String phoneNumber, String status) {
		Users user = new Users();
		try {
			 user = this.findUserByNumber(phoneNumber);
			if(user != null) {
				user.setStatus(status);
				this.crudRepository.save(user);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return user;
	}
	
	@Override
	public Users findUserByNumber(String phoneNumber) {
		try {
			return this.entityManager.createQuery("select u from Users u where u.phoneNumber=:phoneNumber",
					Users.class)
					.setParameter("phoneNumber",phoneNumber)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public String updateProductUser(String phoneNumber, String name) {
		Users user = new Users();
		try {
			 user = this.findUserByNumber(phoneNumber);
			if(user != null) {
				user.setCurrentProduct(name);
				user = this.crudRepository.save(user);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user.getCurrentProduct();
	}

}
