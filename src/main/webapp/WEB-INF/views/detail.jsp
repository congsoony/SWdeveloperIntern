<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="css/style.css" rel="stylesheet">
<style>
.container_visual {
	height: 414px;
}
</style>
</head>

<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div class="ct main">
			<div>
				<div class="section_visual">
					<div class="pagination">
						<div class="bg_pagination"></div>
						<div class="figure_pagination">
							<span class="num" id="figure_num" data-num="0">1</span> <span
								class="num off">/ <span id="figure_total">1</span></span>
						</div>
					</div>
					<div class="group_visual">
						<div>
							<div class="container_visual" style="width: 414px;">
								<ul class="visual_img detail_swipe" id="product_img">
								</ul>
							</div>
							<div class="prev">
								<div class="prev_inn">
									<a class="btn_prev" title="이전" id="click_prev"> <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
										<i class="spr_book2 ico_arr6_lt off"></i>
									</a>
								</div>
							</div>
							<div class="nxt">
								<div class="nxt_inn">
									<a class="btn_nxt" title="다음" id="click_nxt"> <i
										class="spr_book2 ico_arr6_rt"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="group_btn_goto" style="display: none;">
						<a class="btn_goto_home" title="홈페이지" href="#" target="siteUrl">
							<i class="fn fn-home1"></i>
						</a> <a class="btn_goto_tel" title="전화" href="#"> <i
							class="fn fn-call1"></i>
						</a> <a class="btn_goto_mail" title="이메일" href="#"> <i
							class="fn fn-mail1"></i>
						</a> <a href="#" class="btn_goto_path" title="길찾기"> <i
							class="fn fn-path-find1"></i>
						</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share"
							title="공유하기"></a>
					</div>
				</div>
				<div class="section_store_details">
					<!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
					<div class="store_details close3" id="content_summary">
						<p class="dsc" id="content_summary_txt">{{productContent}}</p>
					</div>
					<!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
					<a id="watch_more" class="bk_more _open"> <span
						class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i>
					</a> <a id="watch_less" class="bk_more _close" style="display: none;">
						<span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i>
					</a>
				</div>
				<div class="section_event">
					<div class="event_info_box">
						<div class="event_info_tit">
							<h4 class="in_tit">
								<i class="spr_book ico_evt"></i> <span>이벤트 정보</span>
							</h4>
						</div>
						<div class="event_info">
							<div class="in_dsc">
								[네이버예약 특별할인]<br>R석 50%, S석 60% 할인
							</div>
						</div>
					</div>
				</div>
				<div class="section_btn">
					<button type="button" class="bk_btn">
						<i class="fn fn-nbooking-calender2"></i> <span>예매하기</span>
					</button>
				</div>
				<jsp:include page="/WEB-INF/views/include/review_list.jsp"/>
				<div class="section_info_tab">
					<!-- [D] tab 선택 시 anchor에 active 추가 -->
					<ul class="info_tab_lst">
						<li class="item active _detail"><a id="btn_detail_info"
							class="anchor active"> <span>상세정보</span>
						</a></li>
						<li class="item _path"><a class="anchor"
							id="btn_detail_coming"> <span>오시는길</span>
						</a></li>
					</ul>
					<!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
					<div class="detail_area_wrap" id="detail_area_wrap">
						<div class="detail_area">
							<div class="detail_info">
								<h3 class="blind">상세정보</h3>
								<ul class="detail_info_group">
									<li class="detail_info_lst"><strong class="in_tit">[소개]</strong>
										<p class="in_dsc" id="introduce_content"></p></li>
									<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
										<ul class="in_img_group">
											<li class="in_img_lst"><img alt="" class="img_thumb"
												src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
											</li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
					<div class="detail_location hide" id="detail_location"></div>

				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<div id="photoviwer"></div>


	<script type="rv-template" id="coming_script_template">
		<div class="box_store_info no_topline">
							<a class="store_location" title="지도웹으로 연결"> <img
								class="store_map img_thumb" alt="map"
								src="{{saveFileName}}">
								<span class="img_border"></span> <span class="btn_map"><i
									class="spr_book2 ico_mapview"></i></span>
							</a>
							<h3 class="store_name">{{productDescription}}</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">{{placeStreet}}</p>
									<p class="store_addr">
										<span class="addr_old">지번</span> <span class="addr_old_detail">{{placeLot}} </span>
									</p>
									<p class="store_addr addr_detail">{{placeName}}</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item"><span class="item_lt"> <i
												class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
										</span> <span class="item_rt"> <a 
												class="store_tel">{{telephone}}</a></span></li>
									</ul>
								</div>
							</div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
							<div class="bottom_common_path column2">
								<a class="btn_path"> <i class="fn fn-path-find2"></i>
									<span>길찾기</span>
								</a> <a class="btn_navigation before"> <i
									class="fn fn-navigation2"></i> <span>내비게이션</span>
								</a>
							</div>
						</div>
    </script>

	<script type="rv-template" id="img_script_template">
        <li class="item" style="width: 414px;"><img alt=""
            class="img_thumb" src="{{saveFileName}}"> <span class="img_bg"></span>
            <div class="visual_txt">
                <div class="visual_txt_inn">
                    <h2 class="visual_txt_tit">
                        <span>{{productDescription}}</span>
                    </h2>
                    <p class="visual_txt_dsc"></p>
                </div>
            </div>
        </li>
	</script>
	
	<script type="rv-template" id="comment_script_template">
		<li class="list_item">
			<div>
				{{#if commentImages}}
					<div class="review_area">
						<div class="thumb_area">
							{{#each commentImages}}
								<a class="thumb" title="이미지 크게 보기"> <img
									width="90" height="90" class="img_vertical_top"
									src="{{saveFileName}}"
									alt="리뷰이미지">
								</a> <span class="img_count" style="display: block;">
										{{idx @index}}
								</span>
							{{/each}}
						</div>
						<h4 class="resoc_name">{{productDescription}}</h4>
						<p class="review">{{comment}}</p>
					</div>
				{{else}}
					<div class="review_area no_img">
						<h4 class="resoc_name">{{productDescription}}</h4>
						<p class="review">
							{{comment}}
						</p>
					</div>
				{{/if}}
				<div class="info_area">
					<div class="review_info">
						<span class="grade">{{score}}</span> <span class="name">{{reservationEmail}}</span>
						<span class="date">{{date}} 방문</span>
					</div>
				</div>
			</div>
		</li>
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"
		integrity="sha512-fujQJs+fkj7+Az7XKDxMLbVuuaeljjqMQDh3TAI7nYKQMQhEztrmyuex6hlnRuttjXJ9BFvnl4r/t8r8L6gFfA=="
		crossorigin="anonymous"></script>


	<script src="js/detail/detailObj.js"></script>
	<script src="js/common/common.js"></script>
	<script src="js/detail/comment.js"></script>
	<script src="js/detail/display.js"></script>
	<script src="js/detail/btnSet.js"></script>
	<script src="js/detail/detail.js"></script>
</body>


</html>
