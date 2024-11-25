package whatsapp.services.products;

import whatsapp.models.Products;

public interface IDaoProducts {

	Products saveProduct(Products product);
	
	String sell(String name, Integer cantidad);
	
	String listProducts();
	
	String findByName(String name);
	
	String findByNameFill(String name);
}
