package hr.goran.sheepshop.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Goran
 */
public class CustomerOrder {
	@Getter @Setter
	private String customer;
	@Getter @Setter
	private Order order;
	
	public CustomerOrder(){
		this.order = new Order();
	}
	
	public CustomerOrder clone(){
		CustomerOrder customerOrderCopy = new CustomerOrder();
		customerOrderCopy.customer = this.customer;
		customerOrderCopy.order = this.order.clone();
		return customerOrderCopy;
	}
}
