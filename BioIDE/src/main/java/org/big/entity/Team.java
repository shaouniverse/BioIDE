package org.big.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p><b>Team的Entity类</b></p>
 * <p>Team的Entity类</p>
 * @author WangTianshan (王天山)
 * <p>Created date: 2017/9/19 21:35 </p>
 * <p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Entity
@Table(name = "team", schema = "biodata")
public class Team {
	private String id;
	private String name;
	private String leader;
	private Timestamp adddate;
	private String note;
	/*private List<User> users;*/
	/** 保留默认无参构造 */
	public Team() {
	}

	public Team(Team team) {
		this.id = team.getId();
		this.name = team.getName();
		this.leader = team.getLeader();
		this.adddate = team.getAdddate();
		this.note = team.getNote();
	}

	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "leader")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Basic
	@Column(name = "adddate")
	public Timestamp getAdddate() {
		return adddate;
	}

	public void setAdddate(Timestamp adddate) {
		this.adddate = adddate;
	}

	@Basic
	@Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
/*	@ManyToMany(mappedBy = "teams")*/
/*	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Team team = (Team) o;

		if (id != null ? !id.equals(team.id) : team.id != null) return false;
		if (name != null ? !name.equals(team.name) : team.name != null) return false;
		if (leader != null ? !leader.equals(team.leader) : team.leader != null) return false;
		if (adddate != null ? !adddate.equals(team.adddate) : team.adddate != null) return false;
		//if (datasets != null ? !datasets.equals(team.datasets) : team.datasets != null) return false;
		/*if (users != null ? !users.equals(team.users) : team.users != null) return false;*/
		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (leader != null ? leader.hashCode() : 0);
		result = 31 * result + (adddate != null ? adddate.hashCode() : 0);
		//result = 31 * result + (datasets != null ? datasets.hashCode() : 0);
		/*result = 31 * result + ((users != null) ? users.hashCode() : 0);*/
		return result;
	}

}
