package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the keyitem database table.
 * 
 */
@Entity
@NamedQuery(name="Keyitem.findAll", query="SELECT k FROM Keyitem k")
public class Keyitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int branchid;

	private String featureimgjson;

	private int innerorder;

	private String item;

	private int orderid;

	private String pid;

	private String taxonid;

	//bi-directional many-to-one association to Taxkey
	@ManyToOne
	private Taxkey taxkey;

	public Keyitem() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBranchid() {
		return this.branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public String getFeatureimgjson() {
		return this.featureimgjson;
	}

	public void setFeatureimgjson(String featureimgjson) {
		this.featureimgjson = featureimgjson;
	}

	public int getInnerorder() {
		return this.innerorder;
	}

	public void setInnerorder(int innerorder) {
		this.innerorder = innerorder;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getOrderid() {
		return this.orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTaxonid() {
		return this.taxonid;
	}

	public void setTaxonid(String taxonid) {
		this.taxonid = taxonid;
	}

	public Taxkey getTaxkey() {
		return this.taxkey;
	}

	public void setTaxkey(Taxkey taxkey) {
		this.taxkey = taxkey;
	}

}