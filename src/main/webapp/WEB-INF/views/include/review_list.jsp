<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class="section_review_list">
		<div class="review_box">
			<h3 class="title_h3">예매자 한줄평</h3>
			<div class="short_review_area">
				<div class="grade_area">
					<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
					<span class="graph_mask"> <em class="graph_value" id="graph_value" style="width: 84%;"></em>
					</span> <strong class="text_value"> <span id="average_score">4.2</span>
						<em class="total">5.0</em>
					</strong> <span class="join_count"><em class="green" id="total_count">52건</em> 등록</span>
				</div>
				<ul class="list_short_review" id="list_short_review">
				</ul>
			</div>
			<p class="guide">
				<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한
					이용자가 남긴 평가입니다.</span>
			</p>
		</div>
		<a class="btn_review_more" id="btn_review_more"> <span>예매자
				한줄평 더보기</span> <i class="fn fn-forward1"></i>
		</a>
	</div>
	