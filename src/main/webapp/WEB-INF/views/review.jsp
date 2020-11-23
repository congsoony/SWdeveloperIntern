<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
</head>

<body>
	<div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
            <jsp:include page="/WEB-INF/views/include/header.jsp"/>
		</div>
		<div class="ct">
			<div class="wrap_review_list">
				<div class="review_header">
					<div class="top_title gr">
						<a class="btn_back" id="btn_back" title="이전 화면으로 이동"> <i
							class="fn fn-backward1"></i>
						</a>
						<h2>
							<a class="title" href="#">오디컴퍼니 주식회사</a>
						</h2>
					</div>
				</div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<span class="graph_mask"> <em class="graph_value" id="graph_value"
									style="width: 88%;"></em>
								</span> <strong class="text_value"> <span id="average_score">4.4</span> <em
									class="total">5.0</em>
								</strong> <span class="join_count"><em class="green" id="total_count">20건</em> 등록</span>
							</div>
							<ul class="list_short_review" id="list_short_review">
								
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한
								이용자가 남긴 평가입니다.</span>
						</p>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	
	
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

    <script src="js/common/common.js"></script>
    <script src="js/detail/display.js"></script>
    <script src="js/detail/review.js"></script>
    
</body>

</html>