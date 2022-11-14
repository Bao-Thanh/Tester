package group5.tester.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.tester.exception.ResourceNotFoundException;
import group5.tester.model.Order;
import group5.tester.model.OrderDetail;
import group5.tester.model.Product;
import group5.tester.repository.OrderDetailRepository;
import group5.tester.repository.OrderRepository;
import group5.tester.repository.ProductRepository;
import group5.tester.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepository orddetailRepo;
	private ProductRepository productRepo;
	private OrderRepository ordRepo;
	
	@Override
	public List<OrderDetail> getOrddetailByOrderId (long id) {		
		
		return orddetailRepo.getDetailByOrder(id);
	}
	
	@Override
	public OrderDetail createOrderDetail (OrderDetail orddetail) {
		
		Product product = productRepo.findById(orddetail.getProduct().getId()).orElseThrow(() -> 
		new ResourceNotFoundException("Product", "Id", orddetail.getProduct().getId()));
		
		if(product!=null) {							
			orddetailRepo.save(orddetail);
			autoUpdateOrd(orddetail);
			return orddetail;
		}
		return null;
	}
	@Override
	public OrderDetail updateOrderDetail (OrderDetail orddetail, long id) {
		OrderDetail exist = orddetailRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("OrderDetail", "Id", id));
		Product product = productRepo.findById(orddetail.getProduct().getId()).orElseThrow(() -> 
		new ResourceNotFoundException("Product", "Id", orddetail.getProduct().getId()));
		
		//stack
//		List<OrderDetail> listOrddetail = orddetailRepo.getDetailByOrder(orddetail.getOrder().getId());
//		for(OrderDetail d:listOrddetail) {
//			if(d.getProduct().getId()==orddetail.getProduct().getId()) {
//				orddetailRepo.delete(d);
//				d.setAmount(d.getAmount()+orddetail.getAmount());
//				orddetailRepo.save(d);
//				autoUpdateOrd(d);
//				return d;
//			}
//		}
		
		if(product!=null) {
			exist.setProduct(orddetail.getProduct());
		}
		if(ordRepo.findById(orddetail.getOrder().getId())!=null) {
			exist.setOrder(orddetail.getOrder());
		}
		if( (orddetail.getAmount()>0) &&(orddetail.getAmount())<=orddetail.getProduct().getAmount()){
			exist.setAmount(orddetail.getAmount());
		}
		orddetailRepo.save(exist);
		autoUpdateOrd(exist);
		return exist;
	}

	@Override
	public void deleteOrderDetail (long id) {
		orddetailRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("OrderDetail", "Id", id));
		orddetailRepo.deleteById(id);
	}
	
	@Override
	public void autoUpdateOrd (OrderDetail orddetail) {
		Order order = ordRepo.findById(orddetail.getOrder().getId()).orElseThrow(() -> 
		new ResourceNotFoundException("Order", "Id", orddetail.getOrder().getId()));
		
		if(order!= null) {
			List<OrderDetail> newList = orddetailRepo.getDetailByOrder(orddetail.getOrder().getId());
			order.setOrderDetailList(newList);
			double total = 0;
			for (OrderDetail d: newList) {
				total += d.getProductPrice() * d.getAmount();
			}
			order.setTotalPrice(total);
			ordRepo.save(order);			
		}
		
	}
}
