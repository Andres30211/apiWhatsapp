package whatsapp.services.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import whatsapp.models.Products;

@Service
public class RepositoryProductsImple implements IDaoProducts{
	
	@Autowired
	private ICrudRepositoryProducts crudRepositoryProducts;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public String saveCantProduct(String name, String cantidad) {
		Products product = new Products();
		String response = "";
		try {
	        product = this.entityManager
	                .createQuery("select p from Products p where p.name=:name", Products.class)
	                .setParameter("name", name)
	                .getSingleResult();
	        
	        response = product.toString();
	        Integer cant = Integer.parseInt(cantidad);
	        if (product.getCant() >= cant) {
	            product.setCant(product.getCant() + cant); // Resta la cantidad
	            this.entityManager.merge(product); // Actualiza el producto en la base de datos
	            response = product.toString();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
		return response.concat("\nPresione cualquier letra para salir el menú principal !");
	}

	@Transactional
	@Override
	public String sell(String name, String cantidad) {
		Products product = new Products();
		String response = "";
		try {
	        product = this.entityManager
	                .createQuery("select p from Products p where p.name=:name", Products.class)
	                .setParameter("name", name)
	                .getSingleResult();
	        
	        response = product.toString();
	        Integer cant = Integer.parseInt(cantidad);
	        if (product.getCant() >= cant) {
	            product.setCant(product.getCant() - cant); // Resta la cantidad
	            this.entityManager.merge(product); // Actualiza el producto en la base de datos
	            response = product.toString();
	        } else {
	            response = "No hay cantidad suficiente de " + product.getName();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
		return response.concat("\nPresione cualquier letra para salir el menú principal !");
	}

	@Override
	public String listProducts() {
		List<Products> listProducts = this.entityManager.createQuery("select p from Products p",
				Products.class)
				.getResultList();
		
		return "Seleccionaste *Listar Productos*. Generando la lista...\n\n"
    		    + listProducts
    		    	.stream()
    		        .map(Products::toString) // Usa el método toString() de cada producto
    		        .collect(Collectors.joining("\n\n")) // Une cada producto con dos saltos de línea
    		    + "\n\nPresiona una letra para salir";
	}

	@Override
	public String findByName(String name) {
		Products product = new Products();
		try {
			product =  this.entityManager
					.createQuery("select p from Products p where p.name=:name", Products.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
		return product.toString()+ "\nDigita cualquier letra para ir al menu principal !";
		
	}

	@Override
	public String findByNameFill(String name) {
		Products product = new Products();
		try {
			product =  this.entityManager
					.createQuery("select p from Products p where p.name=:name", Products.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
		return product.toString()+ "\nDigita la cantidad de productos que vas a vender !";
	}

	@Override
	public String findByNameAdd(String name) {
		Products product = new Products();
		try {
			product =  this.entityManager
					.createQuery("select p from Products p where p.name=:name", Products.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
		return product.toString()+ "\nDigita la cantidad de productos que vas agregar !";
	}
	
	

}
