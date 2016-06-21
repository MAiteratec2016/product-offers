package offer;

import java.util.Currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPrice {

	private long productNumber;
	private double price;
	private Currency currency;

	protected ProductPrice() {
	}

	public ProductPrice(long productNumber, long price, Currency currency) {
		this.productNumber = productNumber;
		this.price = price;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return String.format("Customer [productNumber=%d, price='%d', currency='%s']", productNumber, price, currency);
	}

	public long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
