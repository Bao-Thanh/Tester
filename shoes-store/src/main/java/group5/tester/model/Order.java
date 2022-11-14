package group5.tester.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "address")
	private String address;
	@Column(name = "phone")
	private String phone;
	@Column(name = "orderDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp orderDate;
	@Column(name = "vat")
	private Double vat;
	@Column(name = "safeOff")
	private Double safeOff;
	@Column(name = "status")
	private String status;
	@Column(name = "state")
	private Integer state;
	@Column(name = "totalPrice")
	private Double totalPrice;
	@Column(name = "payment_type")
	private Integer payment_type;
	@ManyToOne
    @JoinColumn(name="account_id")
	@JsonIgnore
	private Customer customer;
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetailList;
	
	public Order() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public Order(String address, String phone, Double vat, Double safeOff, String status,
			Integer state, Double totalPrice, Integer payment_type, Customer customer) {
		super();
		this.address = address;
		this.phone = phone;
		this.vat = vat;
		this.safeOff = safeOff;
		this.status = status;
		this.state = state;
		this.totalPrice = totalPrice;
		this.payment_type = payment_type;
		this.customer=customer;
	}
	
	

	public Order(Long id, String address, String phone, Integer state, Double totalPrice, Integer payment_type) {
		super();
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.state = state;
		this.totalPrice = totalPrice;
		this.payment_type = payment_type;
	}

	public Order(Long id) {
		super();
		this.id = id;
	}
	
	
}
