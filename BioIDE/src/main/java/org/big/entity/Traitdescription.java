package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the traitdescription database table.
 * 
 */
@Entity
@NamedQuery(name="Traitdescription.findAll", query="SELECT t FROM Traitdescription t")
public class Traitdescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String definition;

	private int propertyid;

	private int traitontologyid;

	public Traitdescription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDefinition() {
		return this.definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public int getPropertyid() {
		return this.propertyid;
	}

	public void setPropertyid(int propertyid) {
		this.propertyid = propertyid;
	}

	public int getTraitontologyid() {
		return this.traitontologyid;
	}

	public void setTraitontologyid(int traitontologyid) {
		this.traitontologyid = traitontologyid;
	}

}