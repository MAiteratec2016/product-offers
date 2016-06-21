package offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails {

	private long productNumber;
	private String name;
	private String description;

	protected ProductDetails() {
	}

	public ProductDetails(long productNumber, String firstName, String lastName) {
		this.productNumber = productNumber;
		this.name = firstName;
		this.description = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer [productNumber=%d, name='%s', description='%s']", productNumber, name,
				description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}
}
