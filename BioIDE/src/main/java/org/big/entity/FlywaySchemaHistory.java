package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the flyway_schema_history database table.
 * 
 */
@Entity
@Table(name="flyway_schema_history")
@NamedQuery(name="FlywaySchemaHistory.findAll", query="SELECT f FROM FlywaySchemaHistory f")
public class FlywaySchemaHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="installed_rank")
	private int installedRank;

	private int checksum;

	private String description;

	@Column(name="execution_time")
	private int executionTime;

	@Column(name="installed_by")
	private String installedBy;

	@Column(name="installed_on")
	private Timestamp installedOn;

	private String script;

	private byte success;

	private String type;

	private String version;

	public FlywaySchemaHistory() {
	}

	public int getInstalledRank() {
		return this.installedRank;
	}

	public void setInstalledRank(int installedRank) {
		this.installedRank = installedRank;
	}

	public int getChecksum() {
		return this.checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public String getInstalledBy() {
		return this.installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	public Timestamp getInstalledOn() {
		return this.installedOn;
	}

	public void setInstalledOn(Timestamp installedOn) {
		this.installedOn = installedOn;
	}

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public byte getSuccess() {
		return this.success;
	}

	public void setSuccess(byte success) {
		this.success = success;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}