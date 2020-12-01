document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    detailObj.review.btnSet();
    detailObj.review.showReview();

});

detailObj.review={
    showReview(){
        let displayInfoId = getParameterByName("displayInfoId");
        const url = "api/comment?displayInfoId=" + displayInfoId;
        getData(url, this.getReviewInfo);
    },

    
    getReviewInfo(jsonObj) {
        document.querySelector("#title").innerText=jsonObj.displayInfo.productDescription;
        detailObj.comment.showComments(jsonObj);
    },
    btnSet(){
        let backBtn=document.querySelector("#btn_back");
        backBtn.addEventListener('click',function(){
        history.back();
      });
    },
    
    
}