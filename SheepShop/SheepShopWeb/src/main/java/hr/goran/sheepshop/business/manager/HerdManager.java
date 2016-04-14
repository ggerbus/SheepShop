package hr.goran.sheepshop.business.manager;

import hr.goran.sheepshop.model.Herd;

/**
 * @author Goran
 */
public interface HerdManager {
	/**
	 * @param day is simulated day
	 * @return herd of sheeps
	 */
	public Herd getHerd(int day);
	
	/**
	 * Save herd.
	 * @param herd
	 */
	public void saveHerd(Herd herd);
}
