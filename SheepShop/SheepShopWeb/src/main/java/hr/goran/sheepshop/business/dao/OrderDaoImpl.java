package hr.goran.sheepshop.business.dao;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import hr.goran.sheepshop.model.CustomerOrder;

/**
 * @author Goran
 */
@Repository
public class OrderDaoImpl implements OrderDao {

	private Set<CustomerOrder> orders;
	
	@PostConstruct
	private void init(){
		this.orders = new HashSet<CustomerOrder>();
	}

	@Override
	public Set<CustomerOrder> getOrders() {
		//initial state must remain consistent
		Set<CustomerOrder> ordersCopy = new HashSet<CustomerOrder>();
		for (CustomerOrder customerOrder : this.orders) {
			ordersCopy.add(customerOrder.clone());
		}
		return ordersCopy;
	}

	@Override
	public void saveOrder(CustomerOrder customerOrder) {
		this.orders.add(customerOrder);
	}

	@Override
	public void initialize() {
		this.orders.clear();
	}

}
