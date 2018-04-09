package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.big.common.StringJsonUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.Date;
import java.util.List;

/**
 *<p><b>Geoobject的Entity类</b></p>
 *<p> Geoobject的Entity类</p>
 * @author BINZI
 *<p>Created date: 2018/4/8 17:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Entity
@Table(name = "geoobject", schema = "biodata")
@TypeDef( name= "StringJsonUserType", typeClass = StringJsonUserType.class)
public class Geoobject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double centerx;

	private double centery;

	private String cngeoname;

	private String engeoname;
	@Type(type = "StringJsonUserType")
	private String geodata;

	private String geotype;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String pid;

	@Type(type = "StringJsonUserType")
	private String relation;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String version;

	//bi-directional many-to-one association to Distributiondata
	@OneToMany(mappedBy="geoobject")
	private List<Distributiondata> distributiondata;

	//bi-directional many-to-one association to Geogroup
	@ManyToOne
	private Geogroup geogroup;

	public Geoobject() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getCenterx() {
		return this.centerx;
	}

	public void setCenterx(double centerx) {
		this.centerx = centerx;
	}

	public double getCentery() {
		return this.centery;
	}

	public void setCentery(double centery) {
		this.centery = centery;
	}

	public String getCngeoname() {
		return this.cngeoname;
	}

	public void setCngeoname(String cngeoname) {
		this.cngeoname = cngeoname;
	}

	public String getEngeoname() {
		return this.engeoname;
	}

	public void setEngeoname(String engeoname) {
		this.engeoname = engeoname;
	}

	public String getGeodata() {
		return this.geodata;
	}

	public void setGeodata(String geodata) {
		this.geodata = geodata;
	}

	public String getGeotype() {
		return this.geotype;
	}

	public void setGeotype(String geotype) {
		this.geotype = geotype;
	}

	public String getInputer() {
		return this.inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public Date getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getSynchdate() {
		return this.synchdate;
	}

	public void setSynchdate(Date synchdate) {
		this.synchdate = synchdate;
	}

	public int getSynchstatus() {
		return this.synchstatus;
	}

	public void setSynchstatus(int synchstatus) {
		this.synchstatus = synchstatus;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Distributiondata> getDistributiondata() {
		return this.distributiondata;
	}

	public void setDistributiondata(List<Distributiondata> distributiondata) {
		this.distributiondata = distributiondata;
	}

	public Distributiondata addDistributiondata(Distributiondata distributiondata) {
		getDistributiondata().add(distributiondata);
		distributiondata.setGeoobject(this);

		return distributiondata;
	}

	public Distributiondata removeDistributiondata(Distributiondata distributiondata) {
		getDistributiondata().remove(distributiondata);
		distributiondata.setGeoobject(null);

		return distributiondata;
	}

	public Geogroup getGeogroup() {
		return this.geogroup;
	}

	public void setGeogroup(Geogroup geogroup) {
		this.geogroup = geogroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(centerx);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(centery);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cngeoname == null) ? 0 : cngeoname.hashCode());
		result = prime * result + ((distributiondata == null) ? 0 : distributiondata.hashCode());
		result = prime * result + ((engeoname == null) ? 0 : engeoname.hashCode());
		result = prime * result + ((geodata == null) ? 0 : geodata.hashCode());
		result = prime * result + ((geogroup == null) ? 0 : geogroup.hashCode());
		result = prime * result + ((geotype == null) ? 0 : geotype.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inputer == null) ? 0 : inputer.hashCode());
		result = prime * result + ((inputtime == null) ? 0 : inputtime.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((relation == null) ? 0 : relation.hashCode());
		result = prime * result + status;
		result = prime * result + ((synchdate == null) ? 0 : synchdate.hashCode());
		result = prime * result + synchstatus;
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geoobject other = (Geoobject) obj;
		if (Double.doubleToLongBits(centerx) != Double.doubleToLongBits(other.centerx))
			return false;
		if (Double.doubleToLongBits(centery) != Double.doubleToLongBits(other.centery))
			return false;
		if (cngeoname == null) {
			if (other.cngeoname != null)
				return false;
		} else if (!cngeoname.equals(other.cngeoname))
			return false;
		if (distributiondata == null) {
			if (other.distributiondata != null)
				return false;
		} else if (!distributiondata.equals(other.distributiondata))
			return false;
		if (engeoname == null) {
			if (other.engeoname != null)
				return false;
		} else if (!engeoname.equals(other.engeoname))
			return false;
		if (geodata == null) {
			if (other.geodata != null)
				return false;
		} else if (!geodata.equals(other.geodata))
			return false;
		if (geogroup == null) {
			if (other.geogroup != null)
				return false;
		} else if (!geogroup.equals(other.geogroup))
			return false;
		if (geotype == null) {
			if (other.geotype != null)
				return false;
		} else if (!geotype.equals(other.geotype))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputer == null) {
			if (other.inputer != null)
				return false;
		} else if (!inputer.equals(other.inputer))
			return false;
		if (inputtime == null) {
			if (other.inputtime != null)
				return false;
		} else if (!inputtime.equals(other.inputtime))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (relation == null) {
			if (other.relation != null)
				return false;
		} else if (!relation.equals(other.relation))
			return false;
		if (status != other.status)
			return false;
		if (synchdate == null) {
			if (other.synchdate != null)
				return false;
		} else if (!synchdate.equals(other.synchdate))
			return false;
		if (synchstatus != other.synchstatus)
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}