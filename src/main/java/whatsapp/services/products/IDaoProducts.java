package whatsapp.services.products;


public interface IDaoProducts {

	String saveCantProduct(String name, String cantidad);
	
	String sell(String name, String cantidad);
	
	String listProducts();
	
	String findByName(String name);
	
	String findByNameFill(String name);
	
	String findByNameAdd(String name);
}
