package hr.goran.sheepshop.business.manager;

import hr.goran.sheepshop.model.Stock;

/**
 * @author Goran
 */
public interface StockManager {
	/**
	 * @param day is simulated day
	 * @return stock
	 */
	public Stock getStock(int day);
}
