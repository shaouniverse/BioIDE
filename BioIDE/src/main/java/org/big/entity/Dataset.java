package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 *<p><b>Dataset的Entity类</b></p>
 *<p> Dataset的Entity类</p>
 * @author BINZI
 *<p>Created date: 2018/4/8 17:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Entity
@Table(name = "dataset", schema = "biodata")
public class Dataset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	private String creator;

	private String dsabstract;

	private String dsname;

	private String lisenceid;

	private String mark;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String teamid;

	private String userid;

	//bi-directional many-to-one association to Phytree
	@OneToMany(mappedBy="dataset")
	private List<Phytree> phytrees;

	//bi-directional many-to-one association to Taxaset
	@OneToMany(mappedBy="dataset")
	private List<Taxaset> taxasets;

	//bi-directional many-to-one association to Taxtree
	@OneToMany(mappedBy="dataset")
	private List<Taxtree> taxtrees;

	public Dataset() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDsabstract() {
		return this.dsabstract;
	}

	public void setDsabstract(String dsabstract) {
		this.dsabstract = dsabstract;
	}

	public String getDsname() {
		return this.dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getLisenceid() {
		return this.lisenceid;
	}

	public void setLisenceid(String lisenceid) {
		this.lisenceid = lisenceid;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

	public String getTeamid() {
		return this.teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Phytree> getPhytrees() {
		return this.phytrees;
	}

	public void setPhytrees(List<Phytree> phytrees) {
		this.phytrees = phytrees;
	}

	public Phytree addPhytree(Phytree phytree) {
		getPhytrees().add(phytree);
		phytree.setDataset(this);

		return phytree;
	}

	public Phytree removePhytree(Phytree phytree) {
		getPhytrees().remove(phytree);
		phytree.setDataset(null);

		return phytree;
	}

	public List<Taxaset> getTaxasets() {
		return this.taxasets;
	}

	public void setTaxasets(List<Taxaset> taxasets) {
		this.taxasets = taxasets;
	}

	public Taxaset addTaxaset(Taxaset taxaset) {
		getTaxasets().add(taxaset);
		taxaset.setDataset(this);

		return taxaset;
	}

	public Taxaset removeTaxaset(Taxaset taxaset) {
		getTaxasets().remove(taxaset);
		taxaset.setDataset(null);

		return taxaset;
	}

	public List<Taxtree> getTaxtrees() {
		return this.taxtrees;
	}

	public void setTaxtrees(List<Taxtree> taxtrees) {
		this.taxtrees = taxtrees;
	}

	public Taxtree addTaxtree(Taxtree taxtree) {
		getTaxtrees().add(taxtree);
		taxtree.setDataset(this);

		return taxtree;
	}

	public Taxtree removeTaxtree(Taxtree taxtree) {
		getTaxtrees().remove(taxtree);
		taxtree.setDataset(null);

		return taxtree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((dsabstract == null) ? 0 : dsabstract.hashCode());
		result = prime * result + ((dsname == null) ? 0 : dsname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lisenceid == null) ? 0 : lisenceid.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((phytrees == null) ? 0 : phytrees.hashCode());
		result = prime * result + status;
		result = prime * result + ((synchdate == null) ? 0 : synchdate.hashCode());
		result = prime * result + synchstatus;
		result = prime * result + ((taxasets == null) ? 0 : taxasets.hashCode());
		result = prime * result + ((taxtrees == null) ? 0 : taxtrees.hashCode());
		result = prime * result + ((teamid == null) ? 0 : teamid.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		Dataset other = (Dataset) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (dsabstract == null) {
			if (other.dsabstract != null)
				return false;
		} else if (!dsabstract.equals(other.dsabstract))
			return false;
		if (dsname == null) {
			if (other.dsname != null)
				return false;
		} else if (!dsname.equals(other.dsname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lisenceid == null) {
			if (other.lisenceid != null)
				return false;
		} else if (!lisenceid.equals(other.lisenceid))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (phytrees == null) {
			if (other.phytrees != null)
				return false;
		} else if (!phytrees.equals(other.phytrees))
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
		if (taxasets == null) {
			if (other.taxasets != null)
				return false;
		} else if (!taxasets.equals(other.taxasets))
			return false;
		if (taxtrees == null) {
			if (other.taxtrees != null)
				return false;
		} else if (!taxtrees.equals(other.taxtrees))
			return false;
		if (teamid == null) {
			if (other.teamid != null)
				return false;
		} else if (!teamid.equals(other.teamid))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

}