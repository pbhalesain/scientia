package org.oplm.core.model.admin;



/**
 * @generated
 */
@javax.persistence.Entity
public class User implements java.io.Serializable {
	/**
	 * @generated
	 */
	private String username;
	/**
	 * @generated
	 */
	@javax.persistence.Transient
	private String passwd;
	/**
	 * @generated
	 */
	private String password;

	/**
	 * @generated
	 */
	@javax.persistence.Embedded
	private Address address;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToMany(mappedBy = "user")
	private java.util.Set<Team> team = new java.util.HashSet<Team>();

	/**
	 * @generated
	 */
	@javax.persistence.OneToOne
	private UserRole userRole;

	/**
	 * @generated
	 */
	private String email;
	/**
	 * @generated
	 */
	private String active;
	/**
	 * @generated
	 */
	private String passwdExpired;
	/**
	 * @generated
	 */
	private String locked;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "createdBy")
	private java.util.Set<Space> spaces = new java.util.HashSet<Space>();

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 57574652L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "user_id")
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
	@javax.persistence.OneToMany(mappedBy = "createdBy")
	private java.util.Set<org.oplm.core.model.business.Product> createdProducts = new java.util.HashSet<org.oplm.core.model.business.Product>();
	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "createdBy")
	private java.util.Set<org.oplm.core.model.business.Document> createdDocuments = new java.util.HashSet<org.oplm.core.model.business.Document>();
	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "lastUpdatedBy")
	private java.util.Set<org.oplm.core.model.business.Document> updatedDocuments = new java.util.HashSet<org.oplm.core.model.business.Document>();

	/**
	 * @generated
	 */
	@javax.persistence.OneToOne(mappedBy = "lastUpdatedBy")
	private org.oplm.core.model.business.Product lastUpdatedProducts;

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
	public User() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "User" + " username=" + username + " passwd=" + passwd
				+ " password=" + password + " email=" + email + " active="
				+ active + " passwdExpired=" + passwdExpired + " locked="
				+ locked + " id=" + id + " version=" + version
				+ " dateCreated=" + dateCreated + " lastUpdated=" + lastUpdated;
	}

	/**
	 * @generated
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @generated
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @generated
	 */
	public String getPasswd() {
		return this.passwd;
	}

	/**
	 * @generated
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @generated
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @generated
	 */
	public void setPassword(String password) {
		this.password = password;
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
	public java.util.Set<Team> getTeam() {
		return this.team;
	}

	/**
	 * @generated
	 */
	public void setTeam(java.util.Set<Team> team) {
		this.team = team;
	}

	/**
	 * @generated
	 */
	public void addTeam(Team team) {
		getTeam().add(team);
		team.getUser().add(this);
	}

	/**
	 * @generated
	 */
	public void removeTeam(Team team) {
		getTeam().remove(team);
		team.getUser().remove(this);
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
	public String getEmail() {
		return this.email;
	}

	/**
	 * @generated
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @generated
	 */
	public String getActive() {
		return this.active;
	}

	/**
	 * @generated
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @generated
	 */
	public String getPasswdExpired() {
		return this.passwdExpired;
	}

	/**
	 * @generated
	 */
	public void setPasswdExpired(String passwdExpired) {
		this.passwdExpired = passwdExpired;
	}

	/**
	 * @generated
	 */
	public String getLocked() {
		return this.locked;
	}

	/**
	 * @generated
	 */
	public void setLocked(String locked) {
		this.locked = locked;
	}

	/**
	 * @generated
	 */
	public java.util.Set<Space> getSpaces() {
		return this.spaces;
	}

	/**
	 * @generated
	 */
	public void setSpaces(java.util.Set<Space> spaces) {
		this.spaces = spaces;
	}

	/**
	 * @generated
	 */
	public void addSpaces(Space spaces) {
		getSpaces().add(spaces);
		spaces.setCreatedBy(this);
	}

	/**
	 * @generated
	 */
	public void removeSpaces(Space spaces) {
		getSpaces().remove(spaces);
		spaces.setCreatedBy(null);
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
	public java.util.Set<org.oplm.core.model.business.Product> getCreatedProducts() {
		return this.createdProducts;
	}

	/**
	 * @generated
	 */
	public void setCreatedProducts(
			java.util.Set<org.oplm.core.model.business.Product> createdProducts) {
				this.createdProducts = createdProducts;
			}

	/**
	 * @generated
	 */
	public void addCreatedProducts(
			org.oplm.core.model.business.Product createdProducts) {
				getCreatedProducts().add(createdProducts);
				createdProducts.setCreatedBy(this);
			}

	/**
	 * @generated
	 */
	public void removeCreatedProducts(
			org.oplm.core.model.business.Product createdProducts) {
				getCreatedProducts().remove(createdProducts);
				createdProducts.setCreatedBy(null);
			}

	/**
	 * @generated
	 */
	public java.util.Set<org.oplm.core.model.business.Document> getCreatedDocuments() {
		return this.createdDocuments;
	}

	/**
	 * @generated
	 */
	public void setCreatedDocuments(
			java.util.Set<org.oplm.core.model.business.Document> createdDocuments) {
				this.createdDocuments = createdDocuments;
			}

	/**
	 * @generated
	 */
	public void addCreatedDocuments(
			org.oplm.core.model.business.Document createdDocuments) {
				getCreatedDocuments().add(createdDocuments);
				createdDocuments.setCreatedBy(this);
			}

	/**
	 * @generated
	 */
	public void removeCreatedDocuments(
			org.oplm.core.model.business.Document createdDocuments) {
				getCreatedDocuments().remove(createdDocuments);
				createdDocuments.setCreatedBy(null);
			}

	/**
	 * @generated
	 */
	public java.util.Set<org.oplm.core.model.business.Document> getUpdatedDocuments() {
		return this.updatedDocuments;
	}

	/**
	 * @generated
	 */
	public void setUpdatedDocuments(
			java.util.Set<org.oplm.core.model.business.Document> updatedDocuments) {
				this.updatedDocuments = updatedDocuments;
			}

	/**
	 * @generated
	 */
	public void addUpdatedDocuments(
			org.oplm.core.model.business.Document updatedDocuments) {
				getUpdatedDocuments().add(updatedDocuments);
				updatedDocuments.setLastUpdatedBy(this);
			}

	/**
	 * @generated
	 */
	public void removeUpdatedDocuments(
			org.oplm.core.model.business.Document updatedDocuments) {
				getUpdatedDocuments().remove(updatedDocuments);
				updatedDocuments.setLastUpdatedBy(null);
			}

	/**
	 * @generated
	 */
	public org.oplm.core.model.business.Product getLastUpdatedProducts() {
		return this.lastUpdatedProducts;
	}

	/**
	 * @generated
	 */
	public void setLastUpdatedProducts(
			org.oplm.core.model.business.Product lastUpdatedProducts) {
				this.lastUpdatedProducts = lastUpdatedProducts;
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