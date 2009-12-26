package org.oplm.core.model.business;


/**
 * @generated
 */
@javax.persistence.Entity
public class Document implements java.io.Serializable {
	/**
	 * @generated
	 */
	private String number;
	/**
	 * @generated
	 */
	private String name;
	/**
	 * @generated
	 */
	@javax.persistence.Lob
	private String description;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -598730345L;

	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "document_id")
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
	private boolean isMajorVersion;
	/**
	 * @generated
	 */
	private int majorVersion;
	/**
	 * @generated
	 */
	private int minorVersion;
	/**
	 * @generated
	 */
	private boolean frozen;
	/**
	 * @generated
	 */
	private boolean checkedOut;
	/**
	 * @generated
	 */
	private boolean CheckedOutSuperseded;
	/**
	 * @generated
	 */
	private boolean superseded;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "revisions")
	private java.util.Set<DocumentMaster> documentMaster = new java.util.HashSet<DocumentMaster>();

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	private org.oplm.core.model.admin.User createdBy;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	private org.oplm.core.model.admin.User lastUpdatedBy;

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
	@javax.persistence.OneToMany(mappedBy = "document")
	private java.util.Set<DocumentStructure> usesDocuments = new java.util.HashSet<DocumentStructure>();

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "document")
	private java.util.Set<ProductDocument> products = new java.util.HashSet<ProductDocument>();

	/**
	 * @generated
	 */
	public Document() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Document" + " number=" + number + " name=" + name
				+ " description=" + description + " isMajorVersion="
				+ isMajorVersion + " majorVersion=" + majorVersion
				+ " minorVersion=" + minorVersion + " frozen=" + frozen
				+ " checkedOut=" + checkedOut + " CheckedOutSuperseded="
				+ CheckedOutSuperseded + " superseded=" + superseded + " id="
				+ id + " version=" + version + " dateCreated=" + dateCreated
				+ " lastUpdated=" + lastUpdated;
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
	public boolean isIsMajorVersion() {
		return this.isMajorVersion;
	}

	/**
	 * @generated
	 */
	public void setIsMajorVersion(boolean isMajorVersion) {
		this.isMajorVersion = isMajorVersion;
	}

	/**
	 * @generated
	 */
	public int getMajorVersion() {
		return this.majorVersion;
	}

	/**
	 * @generated
	 */
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	/**
	 * @generated
	 */
	public int getMinorVersion() {
		return this.minorVersion;
	}

	/**
	 * @generated
	 */
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	/**
	 * @generated
	 */
	public boolean isFrozen() {
		return this.frozen;
	}

	/**
	 * @generated
	 */
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	/**
	 * @generated
	 */
	public boolean isCheckedOut() {
		return this.checkedOut;
	}

	/**
	 * @generated
	 */
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	/**
	 * @generated
	 */
	public void setCheckedOutSuperseded(boolean CheckedOutSuperseded) {
		this.CheckedOutSuperseded = CheckedOutSuperseded;
	}

	/**
	 * @generated
	 */
	public boolean isSuperseded() {
		return this.superseded;
	}

	/**
	 * @generated
	 */
	public void setSuperseded(boolean superseded) {
		this.superseded = superseded;
	}

	/**
	 * @generated
	 */
	public java.util.Set<DocumentMaster> getDocumentMaster() {
		return this.documentMaster;
	}

	/**
	 * @generated
	 */
	public void setDocumentMaster(java.util.Set<DocumentMaster> documentMaster) {
		this.documentMaster = documentMaster;
	}

	/**
	 * @generated
	 */
	public void addDocumentMaster(DocumentMaster documentMaster) {
		getDocumentMaster().add(documentMaster);
		documentMaster.setRevisions(this);
	}

	/**
	 * @generated
	 */
	public void removeDocumentMaster(DocumentMaster documentMaster) {
		getDocumentMaster().remove(documentMaster);
		documentMaster.setRevisions(null);
	}

	/**
	 * @generated
	 */
	public org.oplm.core.model.admin.User getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @generated
	 */
	public void setCreatedBy(org.oplm.core.model.admin.User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @generated
	 */
	public org.oplm.core.model.admin.User getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	/**
	 * @generated
	 */
	public void setLastUpdatedBy(org.oplm.core.model.admin.User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * @generated
	 */
	public boolean isCheckedOutSuperseded() {
		return this.CheckedOutSuperseded;
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
	public java.util.Set<DocumentStructure> getUsesDocuments() {
		return this.usesDocuments;
	}

	/**
	 * @generated
	 */
	public void setUsesDocuments(java.util.Set<DocumentStructure> usesDocuments) {
		this.usesDocuments = usesDocuments;
	}

	/**
	 * @generated
	 */
	public void addUsesDocuments(DocumentStructure usesDocuments) {
		getUsesDocuments().add(usesDocuments);
		usesDocuments.setDocument(this);
	}

	/**
	 * @generated
	 */
	public void removeUsesDocuments(DocumentStructure usesDocuments) {
		getUsesDocuments().remove(usesDocuments);
		usesDocuments.setDocument(null);
	}

	/**
	 * @generated
	 */
	public java.util.Set<ProductDocument> getProducts() {
		return this.products;
	}

	/**
	 * @generated
	 */
	public void setProducts(java.util.Set<ProductDocument> products) {
		this.products = products;
	}

	/**
	 * @generated
	 */
	public void addProducts(ProductDocument products) {
		getProducts().add(products);
		products.setDocument(this);
	}

	/**
	 * @generated
	 */
	public void removeProducts(ProductDocument products) {
		getProducts().remove(products);
		products.setDocument(null);
	}
}