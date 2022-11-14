package group5.tester.service;

import java.util.List;

import group5.tester.model.Comment;

public interface CommentService {
	
	Comment createComment(Comment comment, long customer_id, long product_id);
	
	Comment updateComment(Comment comment, long id);
	
	void deleteComment(long id);
	
	List<Comment> getCommentByCustomer(long customer_id);
	
	List<Comment> getCommentByProduct(long product_id);
}
