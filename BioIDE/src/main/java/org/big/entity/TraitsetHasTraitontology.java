package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the traitset_has_traitontology database table.
 * 
 */
@Entity
@Table(name="traitset_has_traitontology")
@NamedQuery(name="TraitsetHasTraitontology.findAll", query="SELECT t FROM TraitsetHasTraitontology t")
public class TraitsetHasTraitontology implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="p_traitontology_id")
	private String pTraitontologyId;

	//bi-directional many-to-one association to Traitontology
	@ManyToOne
	private Traitontology traitontology;

	//bi-directional many-to-one association to Traitset
	@ManyToOne
	private Traitset traitset;

	public TraitsetHasTraitontology() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPTraitontologyId() {
		return this.pTraitontologyId;
	}

	public void setPTraitontologyId(String pTraitontologyId) {
		this.pTraitontologyId = pTraitontologyId;
	}

	public Traitontology getTraitontology() {
		return this.traitontology;
	}

	public void setTraitontology(Traitontology traitontology) {
		this.traitontology = traitontology;
	}

	public Traitset getTraitset() {
		return this.traitset;
	}

	public void setTraitset(Traitset traitset) {
		this.traitset = traitset;
	}

}