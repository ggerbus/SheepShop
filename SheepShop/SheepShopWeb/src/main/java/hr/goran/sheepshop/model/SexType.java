package hr.goran.sheepshop.model;

import lombok.Getter;

/**
 * @author Goran
 */
public enum SexType {
	FEMALE("f"),
	MALE("m");
	
	@Getter
	private final String sex;
	
	SexType(String sex){
		this.sex = sex;
	}
	
	
	
	public static SexType getSexType(String sex) throws IllegalArgumentException{
		if("f".equals(sex)){
			return SexType.FEMALE;
		}
		else if("m".equals(sex)){
			return SexType.MALE;
		}
		else{
			throw new IllegalArgumentException("Sex must be in valid format."); 
		}
	}
}
