package whatsapp.services.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import whatsapp.models.Products;

@Service
public class RepositoryProductsImple implements IDaoProducts{
	
	@Autowired
	private ICrudRepositoryProducts crudRepositoryProducts;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Products saveProduct(Products product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sell(String name, Integer cantidad) {
		return null;
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
					.createQuery("select p from Products p where p.name like :name", Products.class)
					.setParameter("name", "%" + name + "%")
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
					.createQuery("select p from Products p where p.name like :name", Products.class)
					.setParameter("name", "%" + name + "%")
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
		return product.toString()+ "\nDigita la cantidad de productos que vas a vender !";
	}
	
	

}
