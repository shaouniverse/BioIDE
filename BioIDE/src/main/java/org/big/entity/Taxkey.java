package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the taxkey database table.
 * 
 */
@Entity
@NamedQuery(name="Taxkey.findAll", query="SELECT t FROM Taxkey t")
public class Taxkey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private String abstraction;

	private String keytitle;

	//bi-directional many-to-one association to Keyitem
	@OneToMany(mappedBy="taxkey")
	private List<Keyitem> keyitems;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Taxkey() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbstraction() {
		return this.abstraction;
	}

	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	public String getKeytitle() {
		return this.keytitle;
	}

	public void setKeytitle(String keytitle) {
		this.keytitle = keytitle;
	}

	public List<Keyitem> getKeyitems() {
		return this.keyitems;
	}

	public void setKeyitems(List<Keyitem> keyitems) {
		this.keyitems = keyitems;
	}

	public Keyitem addKeyitem(Keyitem keyitem) {
		getKeyitems().add(keyitem);
		keyitem.setTaxkey(this);

		return keyitem;
	}

	public Keyitem removeKeyitem(Keyitem keyitem) {
		getKeyitems().remove(keyitem);
		keyitem.setTaxkey(null);

		return keyitem;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}