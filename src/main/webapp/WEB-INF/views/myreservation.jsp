<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<!--[D] 선택 후 .on 추가 link_summary_board -->
							<a class="link_summary_board on" id="all_tab"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure" id="reservation_all_count">6</span> </a>
						</li>
						<li class="item">
							<a class="link_summary_board" id="expect_tab"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure" id="reservation_expect_count">2</span> </a>
						</li>
						<li class="item">
							<a class="link_summary_board" id="used_tab"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure" id="reservation_used_count">2</span> </a>
						</li>
						<li class="item">
							<a class="link_summary_board" id="cancel_tab"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure" id="reservation_cancel_count">2</span> </a>
						</li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">
						<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
						<li class="card" style="display: none;">
							<div class=link_booking_details>
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
										<i class="spr_book2 ico_clock"></i>
										<span class="tit">예약 신청중</span>
									</div>
									<div class="right"></div>
								</div>
							</div>

						</li>
						<li class="card confirmed" id="card_reservation_confirmed">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i>
										<span class="tit">예약 확정</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
							<li class="card used" id="card_reservation_used">
								<div class="link_booking_details">
									<div class="card_header">
										<div class="left"></div>
										<div class="middle">
											<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
											<i class="spr_book2 ico_check2"></i>
											<span class="tit">이용 완료</span>
										</div>
										<div class="right"></div>
									</div>
								</div>
							</li>
							<li class="card used cancel" id="card_reservation_cancel">
								<div class="link_booking_details">
									<div class="card_header">
										<div class="left"></div>
										<div class="middle">
											<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
											<i class="spr_book2 ico_cancel"></i>
											<span class="tit">취소된 예약</span>
										</div>
										<div class="right"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!--// 내 예약 리스트 -->
					<!-- 예약 리스트 없음 -->
					<div class="err" id="list_empty" style="display: none"> <i class="spr_book ico_info_nolist"></i>
						<h1 class="tit">예약 리스트가 없습니다</h1>
					</div>
					<!--// 예약 리스트 없음 -->
				</div>
			</div>
			<hr>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>

		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper" style="display:none;">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
				<h1 class="pop_tit">
					<span>서비스명/상품명</span>
					<small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray">
						<a href="#" class="btn_bottom"><span>아니오</span></a>
					</div>
					<div class="btn_green">
						<a href="#" class="btn_bottom"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a href="#" class="popup_btn_close" title="close">
					<i class="spr_book2 ico_cls"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
		
		<script type="rv-template" id="confirmed_script_template">
			<article class="card_item">
				<a class="link_booking_details">
					<div class="card_body">
						<div class="left"></div>
						<div class="middle">
							<div class="card_detail">
								<em class="booking_number">No.{{reservationInfoId}}</em>
								<h4 class="tit">{{description}}</h4>
								<ul class="detail">
									<li class="item">
										<span class="item_tit">일정</span>
										<em class="item_dsc">
											{{reservationDate}}.({{startDay}}){{untilDate}}.({{untilDay}})
										</em>
									</li>
									<li class="item">
										<span class="item_tit">내역</span>
										<em class="item_dsc">
											{{priceInfo}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">장소</span>
										<em class="item_dsc">
											{{placeName}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">업체</span>
										<em class="item_dsc">
											{{displayInfoTel}}
										</em>
									</li>
								</ul>
								<div class="price_summary">
									<span class="price_tit">결제 예정금액</span>
									<em class="price_amount">
										<span>{{totalPrice}}</span>
										<span class="unit">원</span>
									</em>
								</div>
								<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
								<div class="booking_cancel">
									<button class="btn" data-reservationInfoId={{reservationInfoId}} onclick="myReserveObj.clickCancel(event);"><span>취소</span></button>
								</div>

							</div>
						</div>
						<div class="right"></div>
					</div>
					<div class="card_footer">
						<div class="left"></div>
						<div class="middle"></div>
						<div class="right"></div>
					</div>
				</a>
				<a class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
			</article>
		</script>
		<script type="rv-template" id="used_script_template">
			<article class="card_item">
				<a class="link_booking_details">
					<div class="card_body">
						<div class="left"></div>
						<div class="middle">
							<div class="card_detail">
								<em class="booking_number">No.{{reservationInfoId}}</em>
								<h4 class="tit">{{description}}</h4>
								<ul class="detail">
									<li class="item">
										<span class="item_tit">일정</span>
										<em class="item_dsc">
											{{reservationDate}}.({{startDay}}){{untilDate}}.({{untilDay}})
										</em>
									</li>
									<li class="item">
										<span class="item_tit">내역</span>
										<em class="item_dsc">
											{{priceInfo}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">장소</span>
										<em class="item_dsc">
											{{placeName}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">업체</span>
										<em class="item_dsc">
											{{displayInfoTel}}
										</em>
									</li>
								</ul>
								<div class="price_summary">
									<span class="price_tit">결제 예정금액</span>
									<em class="price_amount">
										<span>{{totalPrice}}</span>
										<span class="unit">원</span>
									</em>
								</div>
								<div class="booking_cancel">
									<a href="./reviewWrite.html"><button class="btn" data-displayInfoId={{displayInfoId}}><span>예매자 리뷰 남기기</span></button></a>
								</div>
							</div>
						</div>
						<div class="right"></div>
					</div>
					<div class="card_footer">
						<div class="left"></div>
						<div class="middle"></div>
						<div class="right"></div>
					</div>
				</a>
			</article>
		</script>
		<script type="rv-template" id="cancel_script_template">
			<article class="card_item">
				<a class="link_booking_details">
					<div class="card_body">
						<div class="left"></div>
						<div class="middle">
							<div class="card_detail">
								<em class="booking_number">No.{{reservationInfoId}}</em>
								<h4 class="tit">{{description}}</h4>
								<ul class="detail">
									<li class="item">
										<span class="item_tit">일정</span>
										<em class="item_dsc">
											{{reservationDate}}.({{startDay}}){{untilDate}}.({{untilDay}})
										</em>
									</li>
									<li class="item">
										<span class="item_tit">내역</span>
										<em class="item_dsc">
											{{priceInfo}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">장소</span>
										<em class="item_dsc">
											{{placeName}}
										</em>
									</li>
									<li class="item">
										<span class="item_tit">업체</span>
										<em class="item_dsc">
											{{displayInfoTel}}
										</em>
									</li>
								</ul>
								<div class="price_summary">
									<span class="price_tit">결제 예정금액</span>
									<em class="price_amount">
										<span>{{totalPrice}}</span>
										<span class="unit">원</span>
									</em>
								</div>
							</div>
						</div>
						<div class="right"></div>
					</div>
					<div class="card_footer">
						<div class="left"></div>
						<div class="middle"></div>
						<div class="right"></div>
					</div>
				</a>
			</article>
		</script>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"
			integrity="sha512-fujQJs+fkj7+Az7XKDxMLbVuuaeljjqMQDh3TAI7nYKQMQhEztrmyuex6hlnRuttjXJ9BFvnl4r/t8r8L6gFfA=="
			crossorigin="anonymous"></script>
		
		<script src="js/common/common.js"></script>
		<script src="js/myreservation/myreservation.js"></script>

	</body>

	</html>