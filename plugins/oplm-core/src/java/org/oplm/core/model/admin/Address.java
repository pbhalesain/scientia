package org.oplm.core.model.admin;


/**
 * @generated
 */
@javax.persistence.Embeddable
public class Address implements java.io.Serializable {
	/**
	 * @generated
	 */
	private String address1;
	/**
	 * @generated
	 */
	private String address2;
	/**
	 * @generated
	 */
	private String city;
	/**
	 * @generated
	 */
	private String state;
	/**
	 * @generated
	 */
	private String country;
	/**
	 * @generated
	 */
	private String zipcode;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 514042147L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "address_id")
	@javax.persistence.GeneratedValue
	private Long id;
	
	/**
	 * @generated
	 */
	public Address() {
	}

	/**
	 * @generated
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * @generated
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @generated
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * @generated
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @generated
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @generated
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @generated
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * @generated
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @generated
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @generated
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @generated
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 * @generated
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Address" + " address1=" + address1 + " address2=" + address2
				+ " city=" + city + " state=" + state + " country=" + country
				+ " zipcode=" + zipcode + " id=" + id ;				
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
}