package hr.goran.sheepshop.business.dao;

import org.springframework.stereotype.Repository;

import hr.goran.sheepshop.model.Herd;
import hr.goran.sheepshop.model.Sheep;

/**
 * @author Goran
 */
@Repository
public class HerdDaoImpl implements HerdDao {

	private Herd herd;
	
	@Override
	public Herd getHerd() {
		if(this.herd == null)
			this.herd = new Herd();
		
		//initial state must remain consistent
		Herd herdCopy = new Herd();
		for (Sheep sheep : this.herd.getHerd()) {
			herdCopy.getHerd().add(sheep.clone());
		}
		
		return herdCopy;
	}

	@Override
	public void saveHerd(Herd herd) {
		this.herd = null;
		this.herd = herd;
	}

}
