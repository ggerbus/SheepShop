package hr.goran.sheepshop.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

/**
 * @author Goran
 */
@XmlTransient
@XmlSeeAlso({MountainSheep.class})
public abstract class Sheep{
	@Getter
	protected String name;
	
	@Getter @JsonIgnore
	protected double ageOnDayZero;

	@Getter @JsonIgnore
	protected SexType sex;

	@Getter @JsonIgnore
	protected int dayOfLastShave;
	
	@Getter @JsonIgnore
	protected int dayOfNextShave;

	@Getter @JsonIgnore
	protected int day;

	/* ######################## ABSTRACT METHODS ######################### */
	
	public abstract double getMilk();

	public abstract boolean isEligibleToShave();

	public abstract boolean isAlive();
	
	protected abstract void initializeDaysOfShave();
	
	public abstract Sheep clone();
	
	/* ######################## IMPLEMENTED METHODS ######################### */
	
	public void initialize(int day){
		if(day < 0)
			throw new IllegalArgumentException("Day can't be less then 0 (zero).");
		this.day = day;
		this.dayOfLastShave = 0;
		this.dayOfNextShave = 0;
		initializeDaysOfShave();
	}
	
	@JsonProperty("age")
	public double getAge(){
		return this.ageOnDayZero + this.day/100.0d;
	}

	@JsonProperty("age-last-shaved")
	public double getAgeLastShaved(){
		return this.ageOnDayZero + this.dayOfLastShave;
	}
	
	protected int getAgeInDays(int day){
		if(day < 0)
			throw new IllegalArgumentException("Day can't be less then 0 (zero).");
		return (int)(this.ageOnDayZero*100) + day;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name = "age")
	public void setAgeOnDayZero(double ageOnDayZero) {
		this.ageOnDayZero = ageOnDayZero;
	}

	@XmlAttribute(name = "sex")
	@XmlJavaTypeAdapter(JaxbSexTypeAdapter.class)
	public void setSex(SexType sex) {
		this.sex = sex;
	}

}
