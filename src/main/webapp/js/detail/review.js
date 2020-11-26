document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    detailObj.review.btnSet();
    detailObj.review.showReview();

});

detailObj.review={
    showReview(){
        var displayInfoId = getParameterByName("displayInfoId");
        var url = "api/comment?displayInfoId=" + displayInfoId;
        getData(url, this.getReviewInfo);
    },

    
    getReviewInfo(jsonObj) {
        detailObj.comment.showComments(jsonObj);
    },
    btnSet(){
      var backBtn=document.querySelector("#btn_back");
      backBtn.addEventListener('click',function(){
        location.href="detail?"+"displayInfoId="+getParameterByName("displayInfoId");
      });
    },
    
    
}