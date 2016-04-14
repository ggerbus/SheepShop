package hr.goran.sheepshop.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Goran
 */
@JsonFilter("orderFilter")
public class Order {
	@Getter @Setter
	private double milk;
	@Getter @Setter
	private int skins;
	
	public Order clone(){
		Order orderCopy = new Order();
		orderCopy.milk = this.milk;
		orderCopy.skins = this.skins;
		return orderCopy;
	}
}
