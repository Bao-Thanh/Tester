package group5.tester.payload.request;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import group5.tester.model.Customer;
import group5.tester.model.OrderDetail;

public class OrderRequest {
	
	private String address;
	private String phone;
	private Timestamp orderDate;
	private Double vat;
	private Double safeOff;
	private String status;
	private Integer state;
	private Double totalPrice;
	private Integer payment_type;
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	//	public class OrderDetailCus {
//		private Integer amount;
//		private Double productPrice;
//		private Long product_id;
//		private Long order_id;
//		
//		public Integer getAmount() {
//		return amount;
//	}
//	public void setAmount(Integer amount) {
//		this.amount = amount;
//	}
//	public Double getProductPrice() {
//		return productPrice;
//	}
//	public void setProductPrice(Double productPrice) {
//		this.productPrice = productPrice;
//	}
//	public Long getProduct_id() {
//		return product_id;
//	}
//	public void setProduct_id(Long product_id) {
//		this.product_id = product_id;
//	}
//	public Long getOrder_id() {
//		return order_id;
//	}
//	public void setOrder_id(Long order_id) {
//		this.order_id = order_id;
//	}
//	}
	private List<OrderDetail> orderDetail;
//	private Integer amount;
//	private Double productPrice;
//	private Long product_id;
//	private Long order_id;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getSafeOff() {
		return safeOff;
	}
	public void setSafeOff(Double safeOff) {
		this.safeOff = safeOff;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(Integer payment_type) {
		this.payment_type = payment_type;
	}
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	
//	public Integer getAmount() {
//		return amount;
//	}
//	public void setAmount(Integer amount) {
//		this.amount = amount;
//	}
//	public Double getProductPrice() {
//		return productPrice;
//	}
//	public void setProductPrice(Double productPrice) {
//		this.productPrice = productPrice;
//	}
//	public Long getProduct_id() {
//		return product_id;
//	}
//	public void setProduct_id(Long product_id) {
//		this.product_id = product_id;
//	}
//	public Long getOrder_id() {
//		return order_id;
//	}
//	public void setOrder_id(Long order_id) {
//		this.order_id = order_id;
//	}
	
	
}
