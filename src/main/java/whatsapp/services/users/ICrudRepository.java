package whatsapp.services.users;

import org.springframework.data.repository.CrudRepository;

import whatsapp.models.Users;

public interface ICrudRepository extends CrudRepository<Users, Integer> {

}
