package kr.or.connect.reservesystem.dto;

public class ProductPrice {

	private String createDate;
	private double number;
	private String modifyDate;
	private int price;
	private String priceTypeName;
	private int productId;
	private int productPriceId;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	@Override
	public String toString() {
		return "ProductPrice [createDate=" + createDate + ", number=" + number + ", modifyDate=" + modifyDate
				+ ", price=" + price + ", priceTypeName=" + priceTypeName + ", productId=" + productId
				+ ", productPriceId=" + productPriceId + "]";
	}

}
