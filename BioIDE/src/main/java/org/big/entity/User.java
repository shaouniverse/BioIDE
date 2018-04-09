package org.big.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *<p><b>User的Entity类</b></p>
 *<p> User的Entity类</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/5 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Entity
@Table(name = "user", schema = "biodata")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	@Basic
	@Column(name = "user_name")
    private String userName;
    private String password;
    private String email;
    private String nickname;
    private String phone;
    private String role;
    
    private Timestamp adddate;
    // 补充字段
    private String avatar;	
    private Timestamp dtime;
    private Integer idnum;
    private Integer level;	
    private String mark;	
    private String mobile;	
    private Integer score;	
    private Byte status; 		// 用户是否激活
    private Integer uploadnum;
    private Date resettime;
    
    private String resetmark;
    
	/** 保留默认无参构造 */
    public User() {
    }

    public User(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
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
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    @Column(name = "avatar")
    public String getAvatar() {
		return avatar;
	}
    
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Basic
    @Column(name = "dtime")
	public Timestamp getDtime() {
		return dtime;
	}

	public void setDtime(Timestamp dtime) {
		this.dtime = dtime;
	}
	
	@Basic
    @Column(name = "idnum")
	public Integer getIdnum() {
		return idnum;
	}

	public void setIdnum(Integer idnum) {
		this.idnum = idnum;
	}
	
	@Basic
    @Column(name = "level")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Basic
    @Column(name = "mark")
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	@Basic
    @Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Basic
    @Column(name = "score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	@Basic
    @Column(name = "status")
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	@Basic
    @Column(name = "uploadnum")
	public Integer getUploadnum() {
		return uploadnum;
	}

	public void setUploadnum(Integer uploadnum) {
		this.uploadnum = uploadnum;
	}
	
	@Basic
    @Column(name = "resetmark")
	public String getResetmark() {
		return resetmark;
	}

	public void setResetmark(String resetmark) {
		this.resetmark = resetmark;
	}
	
	@Basic
    @Column(name = "resettime")
	public Date getResettime() {
		return resettime;
	}

	public void setResettime(Date resettime) {
		this.resettime = resettime;
	}
	
	
/*	@ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER) 
	@JoinTable(name = "user_has_team", joinColumns = {
			@JoinColumn(name = "uid", referencedColumnName = "id")}, inverseJoinColumns = {
					@JoinColumn(name = "tid", referencedColumnName = "id")})*/
	/*public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}*/

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (adddate != null ? !adddate.equals(user.adddate) : user.adddate != null) return false;

        if (status != null ? !status.equals(user.status) : user.status != null) return false;
        if (mobile != null ? !mobile.equals(user.mobile) : user.mobile != null) return false;
        if (score != null ? !score.equals(user.score) : user.score != null) return false;
        if (level != null ? !level.equals(user.level) : user.level != null) return false;
        if (uploadnum != null ? !uploadnum.equals(user.uploadnum) : user.uploadnum != null) return false;
        if (idnum != null ? !idnum.equals(user.idnum) : user.idnum != null) return false;
        if (avatar != null ? !avatar.equals(user.avatar) : user.avatar != null) return false;
        if (dtime != null ? !dtime.equals(user.dtime) : user.dtime != null) return false;
        if (mark != null ? !mark.equals(user.mark) : user.mark != null) return false;
        if (resetmark != null ? !resetmark.equals(user.resetmark) : user.resetmark != null) return false;
        if (resettime != null ? !resettime.equals(user.resettime) : user.resettime != null) return false;
        /*if (teams != null ? !teams.equals(user.teams) : user.teams != null) return false;*/
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (adddate != null ? adddate.hashCode() : 0);
        
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (uploadnum != null ? uploadnum.hashCode() : 0);
        result = 31 * result + (idnum != null ? idnum.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (dtime != null ? dtime.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (resetmark != null ? resetmark.hashCode() : 0);
        result = 31 * result + (resettime != null ? resettime.hashCode() : 0);
        /*result = 31 * result + ((teams != null) ? teams.hashCode() : 0);*/
        return result;
    }

}
