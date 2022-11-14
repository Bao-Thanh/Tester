package group5.tester.service;

import java.util.List;

import group5.tester.model.Comment;
import group5.tester.model.Product;

public interface ProductService {

	Product getProductById(long id);
	
	List<Comment> getCommentByProduct(long procduct_id);
	
	List<Product> getAllProducts();
	
	Product createProduct(Product product, long id);
	
	Product updateProduct(Product product, long id);
	
	void deleteProduct(long id);

}
