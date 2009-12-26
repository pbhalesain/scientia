package org.oplm.core.model.business;


/**
 * @generated
 */
@javax.persistence.Entity
public class DocumentMaster implements java.io.Serializable {
	/**
	 * @generated
	 */
	private String name;
	/**
	 * @generated
	 */
	private String number;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1730649383L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "documentmaster_id")
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
	@javax.persistence.ManyToOne(optional = false)
	private Document revisions;

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
	@javax.persistence.OneToMany(mappedBy = "documentMaster")
	private java.util.Set<DocumentStructure> usedByDocuments = new java.util.HashSet<DocumentStructure>();

	/**
	 * @generated
	 */
	public DocumentMaster() {
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
	public String getNumber() {
		return this.number;
	}

	/**
	 * @generated
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "DocumentMaster" + " name=" + name + " number=" + number
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
	public Document getRevisions() {
		return this.revisions;
	}

	/**
	 * @generated
	 */
	public void setRevisions(Document revisions) {
		this.revisions = revisions;
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
	public java.util.Set<DocumentStructure> getUsedByDocuments() {
		return this.usedByDocuments;
	}

	/**
	 * @generated
	 */
	public void setUsedByDocuments(
			java.util.Set<DocumentStructure> usedByDocuments) {
		this.usedByDocuments = usedByDocuments;
	}

	/**
	 * @generated
	 */
	public void addUsedByDocuments(DocumentStructure usedByDocuments) {
		getUsedByDocuments().add(usedByDocuments);
		usedByDocuments.setDocumentMaster(this);
	}

	/**
	 * @generated
	 */
	public void removeUsedByDocuments(DocumentStructure usedByDocuments) {
		getUsedByDocuments().remove(usedByDocuments);
		usedByDocuments.setDocumentMaster(null);
	}
}