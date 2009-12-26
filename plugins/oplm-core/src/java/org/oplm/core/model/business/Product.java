package org.oplm.core.model.business;


/**
 * @generated
 */
@javax.persistence.Entity
public class Product implements java.io.Serializable {
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
	private boolean checkedOutSuperseded;
	/**
	 * @generated
	 */
	private boolean superseded;
	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "revisions")
	private java.util.Set<ProductMaster> productMaster = new java.util.HashSet<ProductMaster>();
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne
	private Product checkedOutAs;
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne(mappedBy = "checkedOutAs")
	private Product checkedOutFrom;
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne
	private Product versionedAs;
	/**
	 * @generated
	 */
	@javax.persistence.OneToOne(mappedBy = "versionedAs")
	private Product versionedFrom;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	private org.oplm.core.model.admin.User createdBy;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 890340595L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "product_id")
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
	@javax.persistence.OneToOne
	private org.oplm.core.model.admin.User lastUpdatedBy;
	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "product")
	private java.util.Set<ProductStructure> usesProducts = new java.util.HashSet<ProductStructure>();
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
	@javax.persistence.OneToMany(mappedBy = "product")
	private java.util.Set<ProductDocument> documents = new java.util.HashSet<ProductDocument>();
	/**
	 * @generated
	 */
	public Product() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Product" + " number=" + number + " name=" + name
				+ " description=" + description + " isMajorVersion="
				+ isMajorVersion + " majorVersion=" + majorVersion
				+ " minorVersion=" + minorVersion + " frozen=" + frozen
				+ " checkedOut=" + checkedOut + " checkedOutSuperseded="
				+ checkedOutSuperseded + " superseded=" + superseded + " id="
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
	public boolean isCheckedOutSuperseded() {
		return this.checkedOutSuperseded;
	}

	/**
	 * @generated
	 */
	public void setCheckedOutSuperseded(boolean checkedOutSuperseded) {
		this.checkedOutSuperseded = checkedOutSuperseded;
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
	public java.util.Set<ProductMaster> getProductMaster() {
		return this.productMaster;
	}

	/**
	 * @generated
	 */
	public void setProductMaster(java.util.Set<ProductMaster> productMaster) {
		this.productMaster = productMaster;
	}

	/**
	 * @generated
	 */
	public Product getCheckedOutAs() {
		return this.checkedOutAs;
	}

	/**
	 * @generated
	 */
	public void setCheckedOutAs(Product checkedOutAs) {
		this.checkedOutAs = checkedOutAs;
	}

	/**
	 * @generated
	 */
	public Product getCheckedOutFrom() {
		return this.checkedOutFrom;
	}

	/**
	 * @generated
	 */
	public void setCheckedOutFrom(Product checkedOutFrom) {
		this.checkedOutFrom = checkedOutFrom;
	}

	/**
	 * @generated
	 */
	public Product getVersionedAs() {
		return this.versionedAs;
	}

	/**
	 * @generated
	 */
	public void setVersionedAs(Product versionedAs) {
		this.versionedAs = versionedAs;
	}

	/**
	 * @generated
	 */
	public Product getVersionedFrom() {
		return this.versionedFrom;
	}

	/**
	 * @generated
	 */
	public void setVersionedFrom(Product versionedFrom) {
		this.versionedFrom = versionedFrom;
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
	public void addProductMaster(ProductMaster productMaster) {
		getProductMaster().add(productMaster);
		productMaster.setRevisions(this);
	}

	/**
	 * @generated
	 */
	public void removeProductMaster(ProductMaster productMaster) {
		getProductMaster().remove(productMaster);
		productMaster.setRevisions(null);
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
	public java.util.Set<ProductStructure> getUsesProducts() {
		return this.usesProducts;
	}

	/**
	 * @generated
	 */
	public void setUsesProducts(java.util.Set<ProductStructure> usesProducts) {
		this.usesProducts = usesProducts;
	}

	/**
	 * @generated
	 */
	public void addUsesProducts(ProductStructure usesProducts) {
		getUsesProducts().add(usesProducts);
		usesProducts.setProduct(this);
	}

	/**
	 * @generated
	 */
	public void removeUsesProducts(ProductStructure usesProducts) {
		getUsesProducts().remove(usesProducts);
		usesProducts.setProduct(null);
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
	public java.util.Set<ProductDocument> getDocuments() {
		return this.documents;
	}

	/**
	 * @generated
	 */
	public void setDocuments(java.util.Set<ProductDocument> documents) {
		this.documents = documents;
	}

	/**
	 * @generated
	 */
	public void addDocuments(ProductDocument documents) {
		getDocuments().add(documents);
		documents.setProduct(this);
	}

	/**
	 * @generated
	 */
	public void removeDocuments(ProductDocument documents) {
		getDocuments().remove(documents);
		documents.setProduct(null);
	}
}