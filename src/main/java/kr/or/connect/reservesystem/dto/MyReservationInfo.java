package kr.or.connect.reservesystem.dto;

import java.util.List;

public class MyReservationInfo {
	private int reservationInfoId;
	private String placeName;
	private int displayInfoId;
	private boolean cancelFlag;
	private String reservationDate;
	private String reservationEmail;
	private String reservationTel;
	private String reservationName;
	private int totalPrice;
	private String description;
	private boolean used;
	private String untilDate;
	private int startDay;
	private int untilDay;
	private List<ProductPrice> productPriceList;
	private String displayInfoTel;

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(String untilDate) {
		this.untilDate = untilDate;
	}

	public int getStartDay() {
		return startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public int getUntilDay() {
		return untilDay;
	}

	public void setUntilDay(int untilDay) {
		this.untilDay = untilDay;
	}

	public List<ProductPrice> getProductPriceList() {
		return productPriceList;
	}

	public void setProductPriceList(List<ProductPrice> productPriceList) {
		this.productPriceList = productPriceList;
	}

	public String getDisplayInfoTel() {
		return displayInfoTel;
	}

	public void setDisplayInfoTel(String displayInfoTel) {
		this.displayInfoTel = displayInfoTel;
	}

	@Override
	public String toString() {
		return "MyReservationInfo [reservationInfoId=" + reservationInfoId + ", placeName=" + placeName
				+ ", displayInfoId=" + displayInfoId + ", cancelFlag=" + cancelFlag + ", reservationDate="
				+ reservationDate + ", reservationEmail=" + reservationEmail + ", reservationTel=" + reservationTel
				+ ", reservationName=" + reservationName + ", totalPrice=" + totalPrice + ", description=" + description
				+ ", used=" + used + ", untilDate=" + untilDate + ", startDay=" + startDay + ", untilDay=" + untilDay
				+ ", productPriceList=" + productPriceList + ", displayInfoTel=" + displayInfoTel + "]";
	}

}
