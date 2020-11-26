
detailObj.btnSet = {
    detailInfo : document.querySelector("#detail_area_wrap"),
    locationInfo :  document.querySelector("#detail_location"),

    buttonSetListener() {
        
        document.querySelector("#product_img").style.transition = "all 0.3s";
        document.querySelector("#product_img").style.right = "0px";

        var watchMoreBtn = document.querySelector("#watch_more");
        var watchLessBtn = document.querySelector("#watch_less");
        var rightBtn = document.querySelector("#click_nxt");
        var leftBtn = document.querySelector("#click_prev");
        var introduceBtn = document.querySelector("#btn_detail_info");
        var comingBtn = document.querySelector("#btn_detail_coming");
        var reviewMoreBtn=document.querySelector("#btn_review_more");
        var reserveBtn=document.querySelector("#reserve_btn");

        watchMoreBtn.addEventListener('click', () => {
            var content = document.querySelector("#content_summary");
            content.className = "store_details";
            watchMoreBtn.style.display = "none";
            watchLessBtn.style.display = "block";
        });

        watchLessBtn.addEventListener('click', () => {
            var content = document.querySelector("#content_summary");
            content.className = "store_details close3";
            watchMoreBtn.style.display = "block";
            watchLessBtn.style.display = "none";
        });
        
        rightBtn.addEventListener('click', () => {
            var imgHtml = document.querySelector("#product_img");
            var curPage = parseInt(document.querySelector("#figure_num").dataset.num);
            this.rightClickAnimate(curPage, 2, imgHtml);
        });

        leftBtn.addEventListener('click', () => {
            var imgHtml = document.querySelector("#product_img");
            var curPage = parseInt(document.querySelector("#figure_num").dataset.num);
            
            this.leftClickAnimate(curPage, 2, imgHtml);
            
        });

        introduceBtn.addEventListener('click',()=>{
            this.detailInfo.className="detail_area_wrap";
            this.locationInfo.className="detail_location hide";
            introduceBtn.className="anchor active";
            comingBtn.className="anchor";
        });

        comingBtn.addEventListener('click',()=>{
            this.detailInfo.className="detail_area_wrap hide";
            this.locationInfo.className="detail_location";
            introduceBtn.className="anchor";
            comingBtn.className="anchor active";
        });
        
        reviewMoreBtn.addEventListener('click',()=>{
            var displayInfoId = getParameterByName("displayInfoId");
            location.href="review?"+"displayInfoId="+displayInfoId;
        });

        reserveBtn.addEventListener('click()',()=>{
            //pjt5에서 추가할것
        });
    },

    rightClickAnimate(curPage, length, imgHtml) {
        var figureNum=document.querySelector("#figure_num");
        var text=parseInt(figureNum.innerText);
        if(curPage==length-1){
            setTimeout(() => {
            imgHtml.style.transition = "all 0.3s";
            imgHtml.style.right = 414*length+"px";
            }, 100);
            setTimeout(() => {
                imgHtml.style.transition = "all 0s";
                imgHtml.style.right = "0px";
            }, 300);
        }
        else {
            setTimeout(() => {
            imgHtml.style.transition = "all 0.3s";
            imgHtml.style.right = (curPage+1)*414+"px";
            },100);
        }
        figureNum.innerText=1+(curPage+1)%length;
        figureNum.dataset.num=(curPage+1)%length;
    },

    leftClickAnimate(curPage, length, imgHtml) {
        
        var figureNum=document.querySelector("#figure_num");
    
        if(curPage==0){
            setTimeout(() => {
            imgHtml.style.transition = "all 0s";
            imgHtml.style.right = 414*length+"px";
            }, 0);
            setTimeout(() => {
            imgHtml.style.transition = "all 0.3s";
            imgHtml.style.right = 414*(length-1)+"px";
            }, 100);
        }
        else {
            setTimeout(() => {
            imgHtml.style.transition = "all 0.3s";
            imgHtml.style.right = (curPage-1)*414+"px";
            },100);
        }
        figureNum.innerText=1+((curPage-1+length)%length);
        figureNum.dataset.num=(curPage-1+length)%length;
    
    }
    
}