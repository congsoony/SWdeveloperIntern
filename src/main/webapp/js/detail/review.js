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
        document.querySelector("#title").innerText=jsonObj.displayInfo.productDescription;
        detailObj.comment.showComments(jsonObj);
    },
    btnSet(){
        var backBtn=document.querySelector("#btn_back");
        backBtn.addEventListener('click',function(){
        history.back();
      });
    },
    
    
}