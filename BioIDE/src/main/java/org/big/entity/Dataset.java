package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

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

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="teamid")
	private Team team;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Dataset() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

/*	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
*/
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

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}