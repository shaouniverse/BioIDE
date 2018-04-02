package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the phytree database table.
 * 
 */
@Entity
@NamedQuery(name="Phytree.findAll", query="SELECT p FROM Phytree p")
public class Phytree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	//bi-directional many-to-one association to Dataset
	@ManyToOne
	private Dataset dataset;

	public Phytree() {
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

	public Dataset getDataset() {
		return this.dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

}