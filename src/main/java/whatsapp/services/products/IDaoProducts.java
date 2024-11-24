package whatsapp.services.products;

import whatsapp.models.Products;

public interface IDaoProducts {

	Products saveProduct(Products product);
	
	Products sell(String name);
	
	String listProducts();
	
	String findByName(String name);
}
