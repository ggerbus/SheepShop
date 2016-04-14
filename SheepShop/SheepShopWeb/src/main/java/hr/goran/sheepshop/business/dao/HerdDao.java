package hr.goran.sheepshop.business.dao;

import hr.goran.sheepshop.model.Herd;

/**
 * @author Goran
 */
public interface HerdDao {
	/**
	 * @param day is simulated day
	 * @return herd of sheeps
	 */
	public Herd getHerd();
	
	/**
	 * Save herd.
	 * @param herd
	 */
	public void saveHerd(Herd herd);
}
