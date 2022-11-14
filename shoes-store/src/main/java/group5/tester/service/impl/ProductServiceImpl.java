package group5.tester.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.tester.exception.ResourceNotFoundException;
import group5.tester.model.Category;
import group5.tester.model.Comment;
import group5.tester.model.Product;
import group5.tester.repository.CategoryRepository;
import group5.tester.repository.CommentRepository;
import group5.tester.repository.ProductRepository;
import group5.tester.repository.SupplierRepository;
import group5.tester.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepo;
	@Autowired
	CommentRepository commentRepo;
	@Autowired
	SupplierRepository supplierRepo;
	@Autowired
	CategoryRepository categoryRepo;

	@Override
	public Product getProductById(long id) {
		return productRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Product", "Id", id));
	}

	@Override
	public List<Comment> getCommentByProduct(long procduct_id) {
		return commentRepo.findCommentByProductId(procduct_id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Product createProduct(Product product, long id) {
		Category existingCategory = categoryRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Category", "Id", id));
		product.setCategory(existingCategory);
		return productRepo.save(product);
	}

	@Override
	public Product updateProduct(Product product, long id) {
		Product existingProduct = productRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Product", "Id", id));

		if (!product.getName().equals("")) {
			existingProduct.setName(product.getName());
		}
		if (product.getPrice() > 0) {
			existingProduct.setPrice(product.getPrice());
		}
		existingProduct.setSaleOff(product.getSaleOff());
		if (!product.getImage().equals("")) {
			existingProduct.setImage(product.getImage());
		}
		existingProduct.setAmount(product.getAmount());
		existingProduct.setDescription(product.getDescription());
		//existingProduct.setCommentList(product.getCommentList());
		productRepo.save(existingProduct);
		return existingProduct;
	}

	@Override
	public void deleteProduct(long id) {
		productRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Product", "Id", id));
		productRepo.deleteById(id);
	}
}
