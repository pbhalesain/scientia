package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Entity
public class Project implements java.io.Serializable {
	/**
	 * @generated
	 */
	private java.util.Date startDate;
	/**
	 * @generated
	 */
	private String name;

	/**
	 * @generated
	 */
	private boolean active;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToMany(mappedBy = "projects")
	private java.util.Set<Organization> organizations = new java.util.HashSet<Organization>();

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1352423496L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "project_id")
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
	public Project() {
	}

	/**
	 * @generated
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @generated
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
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
	public String toString() {
		return "Project" + " startDate=" + startDate + " name=" + name
				+ " active=" + active + " id=" + id + " version=" + version
				+ " dateCreated=" + dateCreated + " lastUpdated=" + lastUpdated;
	}

	/**
	 * @generated
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * @generated
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @generated
	 */
	public java.util.Set<Organization> getOrganizations() {
		return this.organizations;
	}

	/**
	 * @generated
	 */
	public void setOrganizations(java.util.Set<Organization> organizations) {
		this.organizations = organizations;
	}

	/**
	 * @generated
	 */
	public void addOrganizations(Organization organizations) {
		getOrganizations().add(organizations);
		organizations.getProjects().add(this);
	}

	/**
	 * @generated
	 */
	public void removeOrganizations(Organization organizations) {
		getOrganizations().remove(organizations);
		organizations.getProjects().remove(this);
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