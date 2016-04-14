package hr.goran.sheepshop.business.dao;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import hr.goran.sheepshop.Application;
import hr.goran.sheepshop.model.CustomerOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class OrderDaoTest {

	@Autowired
	private OrderDao orderDao;
	
	@Test
	public void initializeTest() {
		orderDao.initialize();
		Set<CustomerOrder> customerOrders = orderDao.getOrders();
		Assert.notNull(customerOrders);
	}
	
	@Test
	public void getOrdersTest() {
		orderDao.initialize();
		Set<CustomerOrder> customerOrders = orderDao.getOrders();
		Assert.notNull(customerOrders);
		Assert.isTrue(customerOrders.size()==0);
	}
	
	@Test
	public void saveOrderTest() {
		orderDao.initialize();
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomer("Test");
		Assert.notNull(customerOrder.getOrder());
		customerOrder.getOrder().setMilk(1.1);
		customerOrder.getOrder().setSkins(2);
		
		orderDao.saveOrder(customerOrder);
		
		Set<CustomerOrder> customerOrders = orderDao.getOrders();
		Assert.notNull(customerOrders);
		Assert.notEmpty(customerOrders);
		Assert.isTrue(customerOrders.size()==1);
	}
	
}
