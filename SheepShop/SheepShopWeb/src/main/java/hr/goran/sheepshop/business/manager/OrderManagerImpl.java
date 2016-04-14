package hr.goran.sheepshop.business.manager;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.goran.sheepshop.business.dao.OrderDao;
import hr.goran.sheepshop.model.CustomerOrder;
import hr.goran.sheepshop.model.Order;
import hr.goran.sheepshop.model.Stock;

/**
 * @author Goran
 */
@Service
public class OrderManagerImpl implements OrderManager {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private StockManager stockManager;
	
	@Override
	public Set<CustomerOrder> getOrders() {
		return this.orderDao.getOrders();
	}

	@Override
	public Order order(CustomerOrder customerOrder, int day) {
		Stock stock = stockManager.getStock(day);
		
		if(stock.getMilk() < customerOrder.getOrder().getMilk()){
			customerOrder.getOrder().setMilk(0.0);
		}
		else{
			customerOrder.getOrder().setMilk(customerOrder.getOrder().getMilk());
		}
		
		if(stock.getSkins() < customerOrder.getOrder().getSkins()){
			customerOrder.getOrder().setSkins(0);
		}
		else{
			customerOrder.getOrder().setSkins(customerOrder.getOrder().getSkins());
		}
		
		orderDao.saveOrder(customerOrder);
	
		return customerOrder.getOrder();
	}

	@Override
	public void initialize() {
		this.orderDao.initialize();
	}

}
