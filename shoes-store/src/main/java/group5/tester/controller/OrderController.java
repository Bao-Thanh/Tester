package group5.tester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group5.tester.model.Order;
import group5.tester.model.OrderDetail;
import group5.tester.payload.request.OrderRequest;
import group5.tester.service.OrderService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService ordSer;

	@GetMapping("/viewall")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Order>> getAllOrders(){
		return new ResponseEntity<List<Order>>(ordSer.getAllOrders(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Order> saveOrder(@RequestBody OrderRequest order){
		return new ResponseEntity<Order>(ordSer.createOrder(order), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") long id,
			@RequestBody Order order){
		return new ResponseEntity<Order> (ordSer.updateOrder(order, id), HttpStatus.OK);
	}
	@GetMapping("/orderDetail/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<OrderDetail>> getOrderDetail(@PathVariable("id") long id){
		return new ResponseEntity<List<OrderDetail>> (ordSer.getOrderDetail(id), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Order> getOrder(@PathVariable("id") long id){
		return new ResponseEntity<Order> (ordSer.getOrder(id), HttpStatus.OK);
	}
	
	@PutMapping("/orderDetail/{id}")
	public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") long id,
			@RequestBody OrderDetail orddetail){
		return new ResponseEntity<OrderDetail> (ordSer.updateOrderDetail(orddetail, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") long id){
		ordSer.deleteOrder(id);
		return new ResponseEntity<String> ("Delete Order successfully!.", HttpStatus.OK);
	}
}
