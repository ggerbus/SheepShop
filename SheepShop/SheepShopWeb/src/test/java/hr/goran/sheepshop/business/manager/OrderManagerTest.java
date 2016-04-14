package hr.goran.sheepshop.business.manager;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import hr.goran.sheepshop.Application;
import hr.goran.sheepshop.model.CustomerOrder;
import hr.goran.sheepshop.model.Herd;
import hr.goran.sheepshop.model.MountainSheep;
import hr.goran.sheepshop.model.Order;
import hr.goran.sheepshop.model.SexType;
import hr.goran.sheepshop.model.Sheep;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class OrderManagerTest {

	@Autowired
	private OrderManager orderManager;
	@Autowired
	private HerdManager herdManager;

	@Test
	public void getOrdersTest() {
		orderManager.initialize();
		Set<CustomerOrder> customerOrders = orderManager.getOrders();
		Assert.notNull(customerOrders);
		Assert.isTrue(customerOrders.size()==0);
	}

	@Test
	public void orderTest() {
		//inicijaliziraj krdo
		Herd herd = getHerd();
		herdManager.saveHerd(herd);
		int day = 13; //(milk=1104,480L, skins = 3)

		//Scenarij 1 -> USPJESNO
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomer("Test");
		customerOrder.getOrder().setMilk(1.1);
		customerOrder.getOrder().setSkins(1);

		Order order = orderManager.order(customerOrder, day); 
		Assert.notNull(order);
		Assert.isTrue(order.getMilk() == 1.1);
		Assert.isTrue(order.getSkins() == 1);

		//Scenarij 2 -> PARCIJALNO (milk)
		orderManager.initialize();
		customerOrder.getOrder().setMilk(1.1);
		customerOrder.getOrder().setSkins(5);
		
		order = orderManager.order(customerOrder, day);
		Assert.notNull(order);
		Assert.isTrue(order.getMilk() == 1.1);
		Assert.isTrue(order.getSkins() == 0);

		//Scenarij 3 -> PARCIJALNO (skin)
		orderManager.initialize();
		customerOrder.getOrder().setMilk(1000000.1);
		customerOrder.getOrder().setSkins(3);
		
		order = orderManager.order(customerOrder, day);
		Assert.notNull(order);
		Assert.isTrue(order.getMilk() == 0);
		Assert.isTrue(order.getSkins() == 3);
		
		//Scenarij 4 -> NEUSPJESNO
		orderManager.initialize();
		customerOrder.getOrder().setMilk(1000000.1);
		customerOrder.getOrder().setSkins(5);
		
		order = orderManager.order(customerOrder, day);
		Assert.notNull(order);
		Assert.isTrue(order.getMilk() == 0);
		Assert.isTrue(order.getSkins() == 0);
	}

	@Test
	public void initializeTest() {
		orderManager.initialize();
		Set<CustomerOrder> customerOrders = orderManager.getOrders();
		Assert.notNull(customerOrders);
	}

	private Herd getHerd(){
		Herd herd = new Herd();

		Sheep sheep = new MountainSheep();
		sheep.setName("Betty_1");
		sheep.setSex(SexType.FEMALE);
		sheep.setAgeOnDayZero(4.0);
		herd.getHerd().add(sheep);

		sheep = new MountainSheep();
		sheep.setName("Betty_2");
		sheep.setSex(SexType.FEMALE);
		sheep.setAgeOnDayZero(8.0);
		herd.getHerd().add(sheep);
		
		sheep = new MountainSheep();
		sheep.setName("Betty_3");
		sheep.setSex(SexType.FEMALE);
		sheep.setAgeOnDayZero(9.5);
		herd.getHerd().add(sheep);
		
		
		return herd;
	}

}
