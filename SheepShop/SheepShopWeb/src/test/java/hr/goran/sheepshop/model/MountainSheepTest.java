package hr.goran.sheepshop.model;

import org.junit.Test;
import org.springframework.util.Assert;

public class MountainSheepTest {

	@Test
	public void getMilkTest() {
		MountainSheep sheep = getSheep("S-1", 9.5, SexType.FEMALE);
		Assert.isTrue(sheep.getMilk()==21.5);
		
		sheep.initialize(13);
		Assert.isTrue(sheep.getMilk()==21.11);

		sheep.initialize(50);
		Assert.isTrue(sheep.getMilk()==0.0);
		
		sheep = getSheep("S-1", 9.5, SexType.MALE);
		Assert.isTrue(sheep.getMilk()==0.0);
	}

	@Test
	public void isEligibleToShaveTest() {
		MountainSheep sheep = getSheep("S-1", 4, SexType.FEMALE);
		Assert.isTrue(sheep.isEligibleToShave());
		
		sheep.initialize(12);
		Assert.isTrue(!sheep.isEligibleToShave());

		sheep.initialize(13);
		Assert.isTrue(sheep.isEligibleToShave());

		sheep.initialize(25);
		Assert.isTrue(!sheep.isEligibleToShave());

		sheep.initialize(26);
		Assert.isTrue(sheep.isEligibleToShave());
	}

	@Test
	public void isAliveTest() {
		MountainSheep sheep = getSheep("S-1", 4, SexType.FEMALE);
		Assert.isTrue(sheep.isAlive());

		sheep.initialize(26);
		Assert.isTrue(sheep.isAlive());

		sheep.initialize(50);
		Assert.isTrue(sheep.isAlive());

		sheep.initialize(600);
		Assert.isTrue(!sheep.isAlive());
		
		sheep.initialize(601);
		Assert.isTrue(!sheep.isAlive());
	}

	@Test
	public void initializeTest() {
		MountainSheep sheep = getSheep("S-1", 4, SexType.FEMALE);
		
		sheep.initialize(1);
		Assert.isTrue(sheep.getAge() == 4.01);
		Assert.isTrue(sheep.getDayOfLastShave() == 0);
		Assert.isTrue(sheep.getDayOfNextShave() == 13);
		
		sheep.initialize(13);
		Assert.isTrue(sheep.getAge() == 4.13);
		Assert.isTrue(sheep.getDayOfLastShave() == 13);
		Assert.isTrue(sheep.getDayOfNextShave() == 26);
		
		sheep.initialize(100);
		Assert.isTrue(sheep.getAge() == 5.00);

		sheep.initialize(500);
		Assert.isTrue(sheep.getAge() == 9.00);
	}

	@Test
	public void getAgeTest() {
		MountainSheep sheep = getSheep("S-1", 4, SexType.FEMALE);
		Assert.isTrue(sheep.getAge() == 4.0);
		
		sheep.initialize(13);
		Assert.isTrue(sheep.getAge() == 4.13);
		
		sheep.initialize(100);
		Assert.isTrue(sheep.getAge() == 5.00);

		sheep.initialize(500);
		Assert.isTrue(sheep.getAge() == 9.00);
	}

	private MountainSheep getSheep(String name, double ageOnDayZero, SexType sex){
		MountainSheep sheep = new MountainSheep();
		sheep.name = name;
		sheep.ageOnDayZero = ageOnDayZero;
		sheep.sex = sex;
		return sheep;
	}
}
