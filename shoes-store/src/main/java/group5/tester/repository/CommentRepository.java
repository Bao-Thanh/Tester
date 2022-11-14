package group5.tester.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group5.tester.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query("select c from Comment c where c.customer.id = ?1")
	List<Comment> findCommentByCustomerId(long customer_id);
	
	@Query("select c from Comment c where c.product.id = ?1")
	List<Comment> findCommentByProductId(long product_id);
}
