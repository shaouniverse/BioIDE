package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the refs database table.
 * 
 */
@Entity
@Table(name="refs")
@NamedQuery(name="Ref.findAll", query="SELECT r FROM Ref r")
public class Ref implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String author;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String isbn;

	private String journal;

	private String keywords;

	private String languages;

	private String leftstr;

	private int olang;

	private String place;

	private String press;

	private String ptype;

	private String pyear;

	private String refstr;

	@Lob
	private String remark;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String tchar;

	private String title;

	private String tpage;

	private String translator;

	private String version;

	public Ref() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLanguages() {
		return this.languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getLeftstr() {
		return this.leftstr;
	}

	public void setLeftstr(String leftstr) {
		this.leftstr = leftstr;
	}

	public int getOlang() {
		return this.olang;
	}

	public void setOlang(int olang) {
		this.olang = olang;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPress() {
		return this.press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPyear() {
		return this.pyear;
	}

	public void setPyear(String pyear) {
		this.pyear = pyear;
	}

	public String getRefstr() {
		return this.refstr;
	}

	public void setRefstr(String refstr) {
		this.refstr = refstr;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getTchar() {
		return this.tchar;
	}

	public void setTchar(String tchar) {
		this.tchar = tchar;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTpage() {
		return this.tpage;
	}

	public void setTpage(String tpage) {
		this.tpage = tpage;
	}

	public String getTranslator() {
		return this.translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}