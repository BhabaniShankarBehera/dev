/**
 * 
 */
package com.air.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author BhabaniShankar
 *
 */
public class AddressVo {
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "code")
	@JsonIdentityReference(alwaysAsId=true)
	CountryVo nationality;
	
	List<PersonVo> personVo;

	public CountryVo getNationality() {
		return nationality;
	}

	public void setNationality(CountryVo nationality) {
		this.nationality = nationality;
	}

	public List<PersonVo> getPersonVo() {
		return personVo;
	}

	public void setPersonVo(List<PersonVo> personVo) {
		this.personVo = personVo;
	}
	
	

}
