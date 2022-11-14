package group5.tester.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group5.tester.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("select o from Order o where o.customer.id = ?1")
	List<Order> getOrderByCustomer(long cus_id);
	
//	@Query("select new Order(o.id) from Order o")
//	List<Order> findAll();
}
