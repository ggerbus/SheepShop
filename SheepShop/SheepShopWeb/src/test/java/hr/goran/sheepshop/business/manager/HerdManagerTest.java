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
import hr.goran.sheepshop.model.SexType;
import hr.goran.sheepshop.model.Sheep;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HerdManagerTest {

	@Autowired
	private HerdManager herdManager;
	@Autowired
	private OrderManager orderManager;
	
	@Test
	public void getHerdTest() {
		herdManager.saveHerd(getHerd());
		
		Herd herd = herdManager.getHerd(0);
		Assert.notNull(herd.getHerd());
		Assert.isTrue(herd.getHerd().size() == 10);
		for (Sheep sheep : herd.getHerd()) {
			Assert.isTrue(sheep.getAge() == 1.0);
		}
		
		herd = herdManager.getHerd(13);
		Assert.notNull(herd.getHerd());
		Assert.isTrue(herd.getHerd().size() == 10);
		for (Sheep sheep : herd.getHerd()) {
			Assert.isTrue(sheep.getAge() == 1.13);
		}
	}

	@Test
	public void saveHerdTest() {
		Herd herd = getHerd();

		herdManager.saveHerd(herd);
		
		Set<CustomerOrder> customerOrders = orderManager.getOrders();
		Assert.notNull(customerOrders);
		Assert.isTrue(customerOrders.size()==0);
	}

	private Herd getHerd(){
		Herd herd = new Herd();

		for (int i = 0; i < 10; i++) {
			Sheep sheep = new MountainSheep();
			sheep.setName("S-"+i);
			sheep.setSex(SexType.FEMALE);
			sheep.setAgeOnDayZero(1.0);
			herd.getHerd().add(sheep);
		}
		return herd;
	}
}
