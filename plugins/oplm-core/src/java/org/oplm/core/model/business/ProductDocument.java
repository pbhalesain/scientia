package org.oplm.core.model.business;


/**
 * @generated
 */
@javax.persistence.Entity
public class ProductDocument implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -780527826L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "productdocuments_id")
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
	private String type;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
	private Product product;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
	private Document document;

	/**
	 * @generated
	 */
	public ProductDocument() {
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
	public String getType() {
		return this.type;
	}

	/**
	 * @generated
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @generated
	 */
	public Product getProduct() {
		return this.product;
	}

	/**
	 * @generated
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	public String toString() {
		return "ProductDocument" + " id=" + id + " version=" + version
				+ " dateCreated=" + dateCreated + " lastUpdated=" + lastUpdated
				+ " type=" + type;
	}
}