package org.oplm.core.model.business;


/**
 * @generated
 */
@javax.persistence.Entity
public class ProductStructure implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 774416832L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "productstructure_id")
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
	private float quantity;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
	private ProductMaster productMaster;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
	private Product product;

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
	public ProductStructure() {
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
	public float getQuantity() {
		return this.quantity;
	}

	/**
	 * @generated
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	/**
	 * @generated
	 */
	public ProductMaster getProductMaster() {
		return this.productMaster;
	}

	/**
	 * @generated
	 */
	public void setProductMaster(ProductMaster productMaster) {
		this.productMaster = productMaster;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "ProductStructure" + " quantity=" + quantity + " id=" + id
				+ " version=" + version + " dateCreated=" + dateCreated
				+ " lastUpdated=" + lastUpdated;
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