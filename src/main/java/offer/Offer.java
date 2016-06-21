package offer;

import java.util.Currency;

public class Offer {

	private final long productNumber;
	private final String productName;
	private final String productDescription;
	private final double price;
	private final Currency currency;

	public Offer(long productNumber, String productName, String productDescription, double price, Currency currency) {
		super();
		this.productNumber = productNumber;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.currency = currency;
	}

	public long getProductNumber() {
		return productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public double getPrice() {
		return price;
	}

	public Currency getCurrency() {
		return currency;
	}
}
