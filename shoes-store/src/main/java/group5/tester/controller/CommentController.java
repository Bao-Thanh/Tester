package group5.tester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group5.tester.model.Comment;
import group5.tester.service.CommentService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService comSer;

	@PostMapping("/save/{cus_id}/{pro_id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment, 
			@PathVariable("cus_id") long cusId,
			@PathVariable("pro_id") long proId){
		return new ResponseEntity<Comment>(comSer.createComment(comment, cusId, proId), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<String> deleteComment(@PathVariable("id") long comId){
		comSer.deleteComment(comId);
		return new ResponseEntity<String> ("Delete Comment successfully!.", HttpStatus.OK);
	}
}
