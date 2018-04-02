package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the geogroup database table.
 * 
 */
@Entity
@NamedQuery(name="Geogroup.findAll", query="SELECT g FROM Geogroup g")
public class Geogroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	@Lob
	private String remark;

	//bi-directional many-to-one association to Geoobject
	@OneToMany(mappedBy="geogroup")
	private List<Geoobject> geoobjects;

	public Geogroup() {
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Geoobject> getGeoobjects() {
		return this.geoobjects;
	}

	public void setGeoobjects(List<Geoobject> geoobjects) {
		this.geoobjects = geoobjects;
	}

	public Geoobject addGeoobject(Geoobject geoobject) {
		getGeoobjects().add(geoobject);
		geoobject.setGeogroup(this);

		return geoobject;
	}

	public Geoobject removeGeoobject(Geoobject geoobject) {
		getGeoobjects().remove(geoobject);
		geoobject.setGeogroup(null);

		return geoobject;
	}

}