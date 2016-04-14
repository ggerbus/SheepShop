package hr.goran.sheepshop.business.dao;

import java.util.Set;

import hr.goran.sheepshop.model.CustomerOrder;

/**
 * @author Goran
 */
public interface OrderDao {
	/**
	 * @return all orders
	 */
	public Set<CustomerOrder> getOrders();
	
	/**
	 * Save order.
	 * @param order
	 */
	public void saveOrder(CustomerOrder customerOrder);
	
	/**
	 * Delete all orders.
	 */
	public void initialize();
}
