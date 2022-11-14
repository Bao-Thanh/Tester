package group5.tester.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import group5.tester.model.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	@Query("select d from OrderDetail d where d.order.id = ?1")
	List<OrderDetail> getDetailByOrder(long order_id);
	
	@Query("select d from OrderDetail d where d.order.id = ?1")
	List<OrderDetail> findByOrderId (long id);
	
	@Transactional
	@Modifying
	@Query("delete from OrderDetail d where d.order.id = ?1")
    public void removeItem(long order_id);

}
