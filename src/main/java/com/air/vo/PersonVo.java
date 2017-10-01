/**
 * 
 */
package com.air.vo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author BhabaniShankar
 *
 */
public class PersonVo {
	
	int id;
	String code;
	String description;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "code",resolver = CustomObjectIdResolver.class)
	@JsonIdentityReference(alwaysAsId=true)
	CountryVo nationality;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CountryVo getNationality() {
		return nationality;
	}

	public void setNationality(CountryVo nationality) {
		this.nationality = nationality;
	}
	
	

}
