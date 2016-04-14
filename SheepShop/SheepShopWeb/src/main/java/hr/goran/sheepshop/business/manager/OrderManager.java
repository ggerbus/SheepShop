package hr.goran.sheepshop.business.manager;

import java.util.Set;

import hr.goran.sheepshop.model.CustomerOrder;
import hr.goran.sheepshop.model.Order;

/**
 * @author Goran
 */
public interface OrderManager {
	/**
	 * @return orders
	 */
	public Set<CustomerOrder> getOrders();
	
	/**
	 * @param customerOrder
	 * @param day
	 * @return Return approved order
	 */
	public Order order(CustomerOrder customerOrder, int day);
	
	/**
	 * Delete all orders.
	 */
	public void initialize();
}
