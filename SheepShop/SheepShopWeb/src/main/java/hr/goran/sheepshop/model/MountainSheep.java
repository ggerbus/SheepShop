package hr.goran.sheepshop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Goran
 */
@XmlRootElement( name = "mountainsheep" )
public class MountainSheep extends Sheep{
	
	private final double WOOL_MIN_DAY_LIMIT = 1.0; //100 days
	private final int LIFE_LIMIT = 1000; //10 years

	public double getMilk() {
		double retVal = 0.0;
		if(this.sex == SexType.FEMALE && isAlive()){
			retVal = 50.0d - this.getAgeInDays(this.day) * 0.03d;
		}
		return retVal;
	}

	public boolean isEligibleToShave(){
		return (this.getAgeInDays(this.day) >= WOOL_MIN_DAY_LIMIT && this.day == this.dayOfLastShave ? true : false);
	}

	public boolean isAlive() {
		if (this.getAgeInDays(this.day) < LIFE_LIMIT)
			return true;
		return false;
	}
	
	protected void initializeDaysOfShave(){
//		this.dayOfNextShave = this.dayOfLastShave + (int) Math.ceil(8 + this.getAgeInDays(this.day)* 0.01);
		this.dayOfNextShave = ((int) (8 + this.getAgeInDays(this.day)* 0.01)) + this.dayOfLastShave + 1;
		if(this.dayOfNextShave <= this.day){
			this.dayOfLastShave = this.dayOfNextShave;
			initializeDaysOfShave();
		}
		return;
	}

	public Sheep clone() {
		Sheep sheep = new MountainSheep();
		sheep.ageOnDayZero = this.ageOnDayZero;
		sheep.day = this.day;
		sheep.dayOfLastShave = this.dayOfLastShave;
		sheep.dayOfNextShave = this.dayOfNextShave;
		sheep.name = this.name;
		sheep.sex = this.sex;
		return sheep;
	}

//	public static void main(String[] args) {
//		Sheep sheep = new MountainSheep("ofca", 4.0, SexType.getSexType("f"));
//		
//		sheep.shave(0);
//		System.out.println(sheep.eligibleToShaveOnDay());
//		System.out.println(sheep.isEligibleToShave(11));
//		System.out.println(sheep.isEligibleToShave(12));
//		System.out.println(sheep.isEligibleToShave(13));
//	}
}
