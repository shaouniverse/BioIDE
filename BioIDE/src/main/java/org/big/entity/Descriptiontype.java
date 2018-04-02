package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the descriptiontype database table.
 * 
 */
@Entity
@NamedQuery(name="Descriptiontype.findAll", query="SELECT d FROM Descriptiontype d")
public class Descriptiontype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	//bi-directional many-to-one association to Description
	@OneToMany(mappedBy="descriptiontype")
	private List<Description> descriptions;

	public Descriptiontype() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Description> getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public Description addDescription(Description description) {
		getDescriptions().add(description);
		description.setDescriptiontype(this);

		return description;
	}

	public Description removeDescription(Description description) {
		getDescriptions().remove(description);
		description.setDescriptiontype(null);

		return description;
	}

}