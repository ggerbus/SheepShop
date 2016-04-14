package hr.goran.sheepshop.business.manager;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.goran.sheepshop.model.CustomerOrder;
import hr.goran.sheepshop.model.Herd;
import hr.goran.sheepshop.model.Sheep;
import hr.goran.sheepshop.model.Stock;

/**
 * @author Goran
 */
@Service
public class StockManagerImpl implements StockManager {

	@Autowired
	private OrderManager orderManager;
	@Autowired
	private HerdManager herdManager;

	@Override
	public Stock getStock(int day) {
		int skins = 0;
		double milk = 0.0;
		
		Herd herd = this.herdManager.getHerd(day);
		Set<CustomerOrder> orders = this.orderManager.getOrders();
		
		//assumption is that day is much bigger number than number of sheeps
		for (int i = 0; i <= day; i++) {
			for (Sheep sheep : herd.getHerd()) {
				sheep.initialize(i);
				if(sheep.isAlive()){
					if(sheep.isEligibleToShave())
						skins++;
					milk += sheep.getMilk();
				}
			}
		}
		for (CustomerOrder customerOrder : orders) {
			milk -= customerOrder.getOrder().getMilk();
			skins -= customerOrder.getOrder().getSkins();
		}
		
		milk = Math.round(milk * 1000)/1000.0d; //zaokruzujem na 3 decimale
		
		Stock stock = new Stock();
		stock.setMilk(milk);
		stock.setSkins(skins);
		
		return stock;
	}
//
//	public static void main(String[] args) {
//		double x = 256.23;
//		double y = 21.41;
//		double z = x+y;
//		System.out.println(z);
//	}
}
