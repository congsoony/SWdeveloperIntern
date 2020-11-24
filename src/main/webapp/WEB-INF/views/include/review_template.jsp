<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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