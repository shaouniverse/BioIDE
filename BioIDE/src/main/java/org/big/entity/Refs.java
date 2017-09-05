package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Refs {
    private String id;
    private String refstr;
    private String author;
    private String pyear;
    private String leftstr;
    private String title;
    private String ptype;
    private String journal;
    private String languages;
    private Integer olang;
    private String keywords;
    private String translator;
    private String press;
    private String place;
    private String tpage;
    private String tchar;
    private String version;
    private String isbn;
    private Integer status;
    private String inputer;
    private Timestamp inputtime;
    private Integer synchstatus;
    private Timestamp synchdate;
    private String remark;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "refstr")
    public String getRefstr() {
        return refstr;
    }

    public void setRefstr(String refstr) {
        this.refstr = refstr;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "pyear")
    public String getPyear() {
        return pyear;
    }

    public void setPyear(String pyear) {
        this.pyear = pyear;
    }

    @Basic
    @Column(name = "leftstr")
    public String getLeftstr() {
        return leftstr;
    }

    public void setLeftstr(String leftstr) {
        this.leftstr = leftstr;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "ptype")
    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    @Basic
    @Column(name = "journal")
    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    @Basic
    @Column(name = "languages")
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    @Basic
    @Column(name = "olang")
    public Integer getOlang() {
        return olang;
    }

    public void setOlang(Integer olang) {
        this.olang = olang;
    }

    @Basic
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "translator")
    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    @Basic
    @Column(name = "press")
    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "tpage")
    public String getTpage() {
        return tpage;
    }

    public void setTpage(String tpage) {
        this.tpage = tpage;
    }

    @Basic
    @Column(name = "tchar")
    public String getTchar() {
        return tchar;
    }

    public void setTchar(String tchar) {
        this.tchar = tchar;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "inputer")
    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Basic
    @Column(name = "inputtime")
    public Timestamp getInputtime() {
        return inputtime;
    }

    public void setInputtime(Timestamp inputtime) {
        this.inputtime = inputtime;
    }

    @Basic
    @Column(name = "synchstatus")
    public Integer getSynchstatus() {
        return synchstatus;
    }

    public void setSynchstatus(Integer synchstatus) {
        this.synchstatus = synchstatus;
    }

    @Basic
    @Column(name = "synchdate")
    public Timestamp getSynchdate() {
        return synchdate;
    }

    public void setSynchdate(Timestamp synchdate) {
        this.synchdate = synchdate;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Refs refs = (Refs) o;

        if (id != null ? !id.equals(refs.id) : refs.id != null) return false;
        if (refstr != null ? !refstr.equals(refs.refstr) : refs.refstr != null) return false;
        if (author != null ? !author.equals(refs.author) : refs.author != null) return false;
        if (pyear != null ? !pyear.equals(refs.pyear) : refs.pyear != null) return false;
        if (leftstr != null ? !leftstr.equals(refs.leftstr) : refs.leftstr != null) return false;
        if (title != null ? !title.equals(refs.title) : refs.title != null) return false;
        if (ptype != null ? !ptype.equals(refs.ptype) : refs.ptype != null) return false;
        if (journal != null ? !journal.equals(refs.journal) : refs.journal != null) return false;
        if (languages != null ? !languages.equals(refs.languages) : refs.languages != null) return false;
        if (olang != null ? !olang.equals(refs.olang) : refs.olang != null) return false;
        if (keywords != null ? !keywords.equals(refs.keywords) : refs.keywords != null) return false;
        if (translator != null ? !translator.equals(refs.translator) : refs.translator != null) return false;
        if (press != null ? !press.equals(refs.press) : refs.press != null) return false;
        if (place != null ? !place.equals(refs.place) : refs.place != null) return false;
        if (tpage != null ? !tpage.equals(refs.tpage) : refs.tpage != null) return false;
        if (tchar != null ? !tchar.equals(refs.tchar) : refs.tchar != null) return false;
        if (version != null ? !version.equals(refs.version) : refs.version != null) return false;
        if (isbn != null ? !isbn.equals(refs.isbn) : refs.isbn != null) return false;
        if (status != null ? !status.equals(refs.status) : refs.status != null) return false;
        if (inputer != null ? !inputer.equals(refs.inputer) : refs.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(refs.inputtime) : refs.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(refs.synchstatus) : refs.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(refs.synchdate) : refs.synchdate != null) return false;
        if (remark != null ? !remark.equals(refs.remark) : refs.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (refstr != null ? refstr.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (pyear != null ? pyear.hashCode() : 0);
        result = 31 * result + (leftstr != null ? leftstr.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (ptype != null ? ptype.hashCode() : 0);
        result = 31 * result + (journal != null ? journal.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (olang != null ? olang.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (translator != null ? translator.hashCode() : 0);
        result = 31 * result + (press != null ? press.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (tpage != null ? tpage.hashCode() : 0);
        result = 31 * result + (tchar != null ? tchar.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
