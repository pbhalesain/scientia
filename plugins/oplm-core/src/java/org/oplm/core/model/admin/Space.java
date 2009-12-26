package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Entity
public class Space implements java.io.Serializable {
	/**
	 * @generated
	 */
	private String name;
	/**
	 * @generated
	 */
	private String location;
	/**
	 * @generated
	 */
	private String hostType;

	/**
	 * @generated
	 */
	private String owner;
	/**
	 * @generated
	 */
	private String ownerType;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	private User createdBy;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1782873589L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "space_id")
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
	public Space() {
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
	public String getLocation() {
		return this.location;
	}

	/**
	 * @generated
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @generated
	 */
	public String getHostType() {
		return this.hostType;
	}

	/**
	 * @generated
	 */
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Space" + " name=" + name + " location=" + location
				+ " hostType=" + hostType + " owner=" + owner + " ownerType="
				+ ownerType + " id=" + id + " version=" + version
				+ " dateCreated=" + dateCreated + " lastUpdated=" + lastUpdated;
	}

	/**
	 * @generated
	 */
	public String getOwner() {
		return this.owner;
	}

	/**
	 * @generated
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @generated
	 */
	public String getOwnerType() {
		return this.ownerType;
	}

	/**
	 * @generated
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	/**
	 * @generated
	 */
	public User getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @generated
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
}