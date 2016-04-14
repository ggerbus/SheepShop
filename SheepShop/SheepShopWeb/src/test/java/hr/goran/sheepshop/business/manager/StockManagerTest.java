package hr.goran.sheepshop.business.manager;

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
import hr.goran.sheepshop.model.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StockManagerTest {

	@Autowired
	private StockManager stockManager;
	@Autowired
	private OrderManager orderManager;
	@Autowired
	private HerdManager herdManager;
	
	@Test
	public void getStockTest() {
		//inicijaliziraj krdo (milk=1104,480L, skins = 3)
		Herd herd = getHerd();
		herdManager.saveHerd(herd);
		int day = 13; //(milk=1188.810L, skins = 4)
		
		Stock stock = stockManager.getStock(day);
		Assert.notNull(stock);
		Assert.isTrue(stock.getMilk() == 1188.810);
		Assert.isTrue(stock.getSkins() == 4);
		
		getOrder(12.38, 1, day);
		getOrder(150.5, 1, day);
		getOrder(15000.5, 1, day); //parcijalno, narucit ce se samo koza
		
		stock = stockManager.getStock(day);
		Assert.notNull(stock);
		Assert.isTrue(stock.getMilk() == 1025.93);
		Assert.isTrue(stock.getSkins() == 1);
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
	
	private void getOrder(double milk, int skins, int day){
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomer("Test");
		customerOrder.getOrder().setMilk(milk);
		customerOrder.getOrder().setSkins(skins);

		orderManager.order(customerOrder, day);
	}

}
