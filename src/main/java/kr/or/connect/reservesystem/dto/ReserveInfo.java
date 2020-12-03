package kr.or.connect.reservesystem.dto;

import java.util.List;

public class ReserveInfo {
	private int displayInfoId;
	private int productId;
	private List<ReservationInfoPrice> reservationInfoPrices;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private int reservationInfoId;

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public List<ReservationInfoPrice> getReservationInfoPrices() {
		return reservationInfoPrices;
	}

	public void setReservationInfoPrices(List<ReservationInfoPrice> reservationInfoPrices) {
		this.reservationInfoPrices = reservationInfoPrices;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	@Override
	public String toString() {
		return "ReserveInfo [displayInfoId=" + displayInfoId + ", productId=" + productId + ", reservationInfoPrices="
				+ reservationInfoPrices + ", reservationName=" + reservationName + ", reservationTel=" + reservationTel
				+ ", reservationEmail=" + reservationEmail + ", reservationInfoId=" + reservationInfoId + "]";
	}
	
	
}
