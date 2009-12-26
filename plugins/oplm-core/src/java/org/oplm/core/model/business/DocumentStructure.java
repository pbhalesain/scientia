package org.oplm.core.model.business;


/**
 * @generated
 */
@javax.persistence.Entity
public class DocumentStructure implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1055060580L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "documentstructure_id")
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
	@javax.persistence.ManyToOne
	private Document document;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
	private DocumentMaster documentMaster;

	/**
	 * @generated
	 */
	public DocumentStructure() {
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
	public Document getDocument() {
		return this.document;
	}

	/**
	 * @generated
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * @generated
	 */
	public DocumentMaster getDocumentMaster() {
		return this.documentMaster;
	}

	/**
	 * @generated
	 */
	public void setDocumentMaster(DocumentMaster documentMaster) {
		this.documentMaster = documentMaster;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "DocumentStructure" + " id=" + id + " version=" + version
				+ " dateCreated=" + dateCreated + " lastUpdated=" + lastUpdated;
	}
}