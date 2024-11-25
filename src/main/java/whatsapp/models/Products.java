package whatsapp.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	private String description;
	
	private Integer cant;
	
	private Integer price;
	
	public Products() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.'); // Usar punto como separador de miles
        symbols.setDecimalSeparator(','); // Usar coma como separador decimal (opcional)
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        String formattedPrice = formatter.format(this.getPrice());
        
		return "📦 *Nombre:* " + this.getName() + "\n\n"
				+ "📋 *Descripción:* " + this.description + "\n\n"
				+ "📊 *Cantidad:* " + this.getCant() + "\n\n"
				+ "💸 *Precio:* " + formattedPrice
				+"\n==============================";
	}

	
	
	
}
