package hr.goran.sheepshop.business.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.goran.sheepshop.business.dao.HerdDao;
import hr.goran.sheepshop.model.Herd;
import hr.goran.sheepshop.model.Sheep;

/**
 * @author Goran
 */
@Service
public class HerdManagerImpl implements HerdManager {

	@Autowired
	private HerdDao herdDao;

	@Autowired
	private OrderManager orderManager;
	
	@Override
	public Herd getHerd(int day) {
		Herd herd = this.herdDao.getHerd();
		for (Sheep sheep : herd.getHerd()) {
			sheep.initialize(day);
		}
		return herd;
	}

	@Override
	public void saveHerd(Herd herd) {
		this.herdDao.saveHerd(herd);
		this.orderManager.initialize();
	}
}
