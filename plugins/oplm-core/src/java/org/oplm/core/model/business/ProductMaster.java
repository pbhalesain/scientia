package org.oplm.core.model.business;



/**
 * @generated
 */
@javax.persistence.Entity
public class ProductMaster implements java.io.Serializable {
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
	@javax.persistence.ManyToOne(optional = false)
	private Product revisions;

	/**
	 * @generated
	 */
	private String unitOfMeasure;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -631580363L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "productmaster_id")
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
	@javax.persistence.OneToMany(mappedBy = "productMaster")
	private java.util.Set<ProductStructure> usedByProducts = new java.util.HashSet<ProductStructure>();

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
	public ProductMaster() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "ProductMaster" + " name=" + name + " number=" + number
				+ " unitOfMeasure=" + unitOfMeasure + " id=" + id + " version="
				+ version + " dateCreated=" + dateCreated + " lastUpdated="
				+ lastUpdated;
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
	public Product getRevisions() {
		return this.revisions;
	}

	/**
	 * @generated
	 */
	public void setRevisions(Product revisions) {
		this.revisions = revisions;
	}

	/**
	 * @generated
	 */
	public String getUnitOfMeasure() {
		return this.unitOfMeasure;
	}

	/**
	 * @generated
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
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
	public java.util.Set<ProductStructure> getUsedByProducts() {
		return this.usedByProducts;
	}

	/**
	 * @generated
	 */
	public void setUsedByProducts(java.util.Set<ProductStructure> usedByProducts) {
		this.usedByProducts = usedByProducts;
	}

	/**
	 * @generated
	 */
	public void addUsedByProducts(ProductStructure usedByProducts) {
		getUsedByProducts().add(usedByProducts);
		usedByProducts.setProductMaster(this);
	}

	/**
	 * @generated
	 */
	public void removeUsedByProducts(ProductStructure usedByProducts) {
		getUsedByProducts().remove(usedByProducts);
		usedByProducts.setProductMaster(null);
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