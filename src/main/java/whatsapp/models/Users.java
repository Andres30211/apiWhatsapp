package whatsapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String phoneNumber;
	
	private String status;
	
	private String currentProduct;

	public Users(String currenString) {
		this.currentProduct = currenString;
	}
	
	public Users(String phoneNumber, String status) {
		this.phoneNumber = phoneNumber;
		this.status = status;
	}
	
	public Users() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(String currentProduct) {
		this.currentProduct = currentProduct;
	}
}
