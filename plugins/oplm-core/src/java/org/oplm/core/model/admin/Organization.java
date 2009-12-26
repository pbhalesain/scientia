package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Entity
public class Organization implements java.io.Serializable {
	/**
	 * @generated
	 */
	private String name;
	/**
	 * @generated
	 */
	private String description;

	/**
	 * @generated
	 */
	@javax.persistence.Embedded
	private Address address;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToMany
	private java.util.Set<Project> projects = new java.util.HashSet<Project>();

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1777124708L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "organization_id")
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
	public Organization() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Organization" + " name=" + name + " description=" + description
				+ " id=" + id + " version=" + version + " dateCreated="
				+ dateCreated + " lastUpdated=" + lastUpdated;
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
	public String getDescription() {
		return this.description;
	}

	/**
	 * @generated
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @generated
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * @generated
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @generated
	 */
	public java.util.Set<Project> getProjects() {
		return this.projects;
	}

	/**
	 * @generated
	 */
	public void setProjects(java.util.Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @generated
	 */
	public void addProjects(Project projects) {
		getProjects().add(projects);
		projects.getOrganizations().add(this);
	}

	/**
	 * @generated
	 */
	public void removeProjects(Project projects) {
		getProjects().remove(projects);
		projects.getOrganizations().remove(this);
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