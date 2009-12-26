package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Entity
public class TeamRole implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne(mappedBy = "teamRole")
	private Role role;
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne(mappedBy = "teamRole")
	private Team team;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToMany
	private java.util.Set<Project> project = new java.util.HashSet<Project>();

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1694475996L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "teamrole_id")
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
	public TeamRole() {
	}

	/**
	 * @generated
	 */
	public Role getRole() {
		return this.role;
	}

	/**
	 * @generated
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @generated
	 */
	public Team getTeam() {
		return this.team;
	}

	/**
	 * @generated
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @generated
	 */
	public java.util.Set<Project> getProject() {
		return this.project;
	}

	/**
	 * @generated
	 */
	public void setProject(java.util.Set<Project> project) {
		this.project = project;
	}

	/**
	 * @generated
	 */
	public void addProject(Project project) {
		getProject().add(project);
	}

	/**
	 * @generated
	 */
	public void removeProject(Project project) {
		getProject().remove(project);
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "TeamRole" + " id=" + id + " version=" + version
				+ " dateCreated=" + dateCreated + " lastUpdated=" + lastUpdated;
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