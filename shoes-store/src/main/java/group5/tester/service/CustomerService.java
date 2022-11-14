package group5.tester.service;

import java.util.List;

import group5.tester.model.Comment;
import group5.tester.model.Customer;
import group5.tester.model.Order;

public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Customer save(Customer customer);
	
	Customer getCustomerById(long id);

	Customer updateCustomer(Customer customer, long id);

	void deleteCustomer(long id);
	
	List<Comment> getCommentByCustomer(long customer_id);
	
	List<Order> getOrderByCustomer(long customer_id);
}
