package group5.tester.service;

import java.util.List;

import group5.tester.model.Order;
import group5.tester.model.OrderDetail;
import group5.tester.payload.request.OrderRequest;

public interface OrderService {
	
	
	Order createOrder(OrderRequest order);
	
	Order updateOrder(Order order, long id);

	void deleteOrder(long d);
	
	Double totalPrice(long id);

	List<Order> getAllOrders();

	Order getOrder(long id);
	
	OrderDetail updateOrderDetail(OrderDetail orderdetail, long id);

	List<OrderDetail> getOrderDetail(long id);
}
