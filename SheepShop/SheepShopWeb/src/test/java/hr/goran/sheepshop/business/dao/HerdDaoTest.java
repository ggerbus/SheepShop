package hr.goran.sheepshop.business.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import hr.goran.sheepshop.Application;
import hr.goran.sheepshop.model.Herd;
import hr.goran.sheepshop.model.MountainSheep;
import hr.goran.sheepshop.model.SexType;
import hr.goran.sheepshop.model.Sheep;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HerdDaoTest {

	@Autowired HerdDao herdDao;
	
	@Test
	public void getHerdTest() {
		herdDao.saveHerd(null);
		Herd herd = herdDao.getHerd();
		Assert.notNull(herd);
		Assert.notNull(herd.getHerd());
		Assert.isTrue(herd.getHerd().isEmpty());
	}
	
	@Test
	public void saveHerd(){
		Herd herd = getHerd(10);
		herdDao.saveHerd(herd);
		herd = herdDao.getHerd();
		Assert.notNull(herd);
		Assert.notNull(herd.getHerd());
		Assert.notEmpty(herd.getHerd());
		Assert.isTrue(herd.getHerd().size()==10);
	}
	
	

	private Herd getHerd(int numberOfSheeps){
		Herd herd = new Herd();
		
		for (int i = 0; i < numberOfSheeps; i++) {
			Sheep sheep = new MountainSheep();
			sheep.setName("S-"+i);
			sheep.setSex(SexType.FEMALE);
			sheep.setAgeOnDayZero(i%10);
			herd.getHerd().add(sheep);
		}
		return herd;
	}
}
