package com.jaom.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jaom.model.Product;
import com.jaom.model.ProductDetail;
import com.jaom.util.HibernateUtil;



public class OneToOneTest {

	public static void main(String[] args) {
		Product newProduct = getTransaction();
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			
			System.out.println("############### Session created ###############");
			
			//start transaction
			tx = session.beginTransaction();
		
			//Save the Model object
			session.save(newProduct);

			//Commit transaction
			tx.commit();
			System.out.println("Transaction ID="+newProduct.getProductId());
			
			//Get Saved Trasaction Data
			printTransactionData(newProduct.getProductId(), sessionFactory);
			
		}catch(Exception e){
			System.out.println(" !!!!!!! Exception occured. !!!!!!! "+e.getMessage());
			e.printStackTrace();
		
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("############### Closing SessionFactory ############### ");
				sessionFactory.close();
			}
		}
	}
	
	private static void printTransactionData(Long id, SessionFactory sessionFactory) {
		Session session = null;
		Transaction tx = null;
		try{
			//Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
						
			//start transaction
			tx = session.beginTransaction();
			//Save the Model object
			Product prodcut = (Product) session.get(Product.class, id);
			
			//Commit transaction
			tx.commit();
			System.out.println("Transaction Details=\n"+prodcut);
			
			}catch(Exception e){
				System.out.println("Exception occured. "+e.getMessage());
				e.printStackTrace();
			}
	}
	
	private static Product getTransaction() {
		// creates a new product
        Product product = new Product();
        product.setName("Civic");
        product.setDescription("Comfortable, fuel-saving car");
        product.setPrice(20000);
         
        // creates product detail
        ProductDetail detail = new ProductDetail();
        detail.setPartNumber("ABCDEFGHIJKL");
        detail.setDimension("2,5m x 1,4m x 1,2m");
        detail.setWeight(1000);
        detail.setManufacturer("Honda Automobile");
        detail.setOrigin("Japan");
         
        // sets the bi-directional association
        product.setProductDetail(detail);
        detail.setProduct(product);
        
		return product;
	}
	
	
}
