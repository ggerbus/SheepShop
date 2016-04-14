package hr.goran.sheepshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

/**
 * @author Goran
 */
@XmlRootElement( name = "herd" )
public class Herd {
	@Getter
	private Set<Sheep> herd;
	
	public Herd(){
		this.herd = new HashSet<Sheep>();
	}
	@XmlElement( name = "mountainsheep", type=MountainSheep.class )
	public void setHerd(Set<Sheep> herd) {
		this.herd = herd;
	}
}
