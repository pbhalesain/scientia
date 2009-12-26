package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Entity
public class Role implements java.io.Serializable {
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
	@javax.persistence.OneToOne
	private UserRole userRole;
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne
	private TeamRole teamRole;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 57481639L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "role_id")
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
	public Role() {
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
	public UserRole getUserRole() {
		return this.userRole;
	}

	/**
	 * @generated
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * @generated
	 */
	public TeamRole getTeamRole() {
		return this.teamRole;
	}

	/**
	 * @generated
	 */
	public void setTeamRole(TeamRole teamRole) {
		this.teamRole = teamRole;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Role" + " name=" + name + " description=" + description
				+ " id=" + id + " version=" + version + " dateCreated="
				+ dateCreated + " lastUpdated=" + lastUpdated;
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