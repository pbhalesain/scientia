package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Entity
public class Policy implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -651971037L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "policy_id")
	@javax.persistence.GeneratedValue
	private Long id;
	/**
	 * @generated
	 */
	@javax.persistence.Version
	@javax.persistence.Column(name = "OPT_LOCK")
	private Long version;
	/**
	 * @generated
	 */
	private java.util.Date dateCreated;
	/**
	 * @generated
	 */
	private java.util.Date lastUpdated;
	/**
	 * @generated
	 */
	private String type;
	/**
	 * @generated
	 */
	private String name;
	/**
	 * @generated
	 */
	@javax.persistence.Lob
	private String behaviour;

	/**
	 * @generated
	 */
	public Policy() {
	}

	/**
	 * @generated
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public Long getVersion() {
		return this.version;
	}

	/**
	 * @generated
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @generated
	 */
	public java.util.Date getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * @generated
	 */
	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @generated
	 */
	public java.util.Date getLastUpdated() {
		return this.lastUpdated;
	}

	/**
	 * @generated
	 */
	public void setLastUpdated(java.util.Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @generated
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @generated
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @generated
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @generated
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @generated
	 */
	public String getBehaviour() {
		return this.behaviour;
	}

	/**
	 * @generated
	 */
	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Policy" + " id=" + id + " version=" + version + " dateCreated="
				+ dateCreated + " lastUpdated=" + lastUpdated + " type=" + type
				+ " name=" + name + " behaviour=" + behaviour;
	}
}