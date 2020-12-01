
detailObj.display = {
    introduceContent: document.querySelector("#introduce_content"),
    comingHtml : document.querySelector("#detail_location"),
    detailInfo : document.querySelector("#detail_area_wrap"),
    locationInfo :  document.querySelector("#detail_location"),

    showProductImages() {
        let displayInfoId = getParameterByName("displayInfoId");
        const url = "api/displayinfo?displayInfoId=" + displayInfoId;
        getData(url, this.getDisplayInfo);
    },
    getDisplayInfo(jsonObj) {
        // 상품 내용보여주기
        let displayInfoObj = jsonObj.displayInfo;
        let productContent = document.querySelector("#content_summary_txt");
        let bindTemplate = Handlebars.compile(productContent.innerHTML);

        productContent.innerHTML = bindTemplate(displayInfoObj);
        
        
        // 선택한 상품 이미지 & 제목
        let imagesObj = jsonObj.mainImages;
        imagesObj.forEach((item) => {
            item.productDescription = displayInfoObj.productDescription;
        });
        let template = document.querySelector("#img_script_template").innerText;
        let ultag = document.querySelector("#product_img");
        bindTemplate = Handlebars.compile(template); // bindTemplate은 메서드 즉 함수임
        // handlebars.compile이 반환하는게
        // 함수라서
        ultag.innerHTML = imagesObj.reduce(function (prev, next) {
            return prev + bindTemplate(next);
        }, "");
        
        detailObj.display.addEtcImages(jsonObj.etcImages,displayInfoObj.productDescription,ultag);
        detailObj.comment.showComments(jsonObj);
        detailObj.display.setIntroduce(jsonObj.displayInfo.productContent);
        detailObj.display.setComing(jsonObj.displayInfo,jsonObj.displayInfoImage);
        
    },
    setComing(displayinfo,displayInfoImage){
        let data={
            productDescription:displayinfo.productDescription,
            placeStreet : displayinfo.placeStreet,
            placeLot : displayinfo.placeLot,
            placeName : displayinfo.placeName,
            saveFileName : displayInfoImage.saveFileName,
            telephone : displayinfo.telephone
        };

        let bindTemplate = Handlebars.compile(document.querySelector("#coming_script_template").innerText);
        this.comingHtml.innerHTML = bindTemplate(data);
    },
  
    setIntroduce(productContent) {
        this.introduceContent.innerText = productContent;
    },

    buttonSetListener() {

        document.querySelector("#product_img").style.transition = "all 0.3s";
        document.querySelector("#product_img").style.right = "0px";

        let watchMoreBtn = document.querySelector("#watch_more");
        let watchLessBtn = document.querySelector("#watch_less");
        let rightBtn = document.querySelector("#click_nxt");
        let leftBtn = document.querySelector("#click_prev");
        let introduceBtn = document.querySelector("#btn_detail_info");
        let comingBtn = document.querySelector("#btn_detail_coming");
        let reviewMoreBtn = document.querySelector("#btn_review_more");
        let reserveBtn = document.querySelector("#reserve_btn");

        watchMoreBtn.addEventListener('click', () => {
            let content = document.querySelector("#content_summary");
            content.className = "store_details";
            watchMoreBtn.style.display = "none";
            watchLessBtn.style.display = "block";
        });

        watchLessBtn.addEventListener('click', () => {
            let content = document.querySelector("#content_summary");
            content.className = "store_details close3";
            watchMoreBtn.style.display = "block";
            watchLessBtn.style.display = "none";
        });

        rightBtn.addEventListener('click', () => {
            let imgHtml = document.querySelector("#product_img");
            let curPage = parseInt(document.querySelector("#figure_num").dataset.num);
            this.rightClickAnimate(curPage, 2, imgHtml);
        });

        leftBtn.addEventListener('click', () => {
            let imgHtml = document.querySelector("#product_img");
            let curPage = parseInt(document.querySelector("#figure_num").dataset.num);

            this.leftClickAnimate(curPage, 2, imgHtml);

        });

        introduceBtn.addEventListener('click', () => {
            this.detailInfo.className = "detail_area_wrap";
            this.locationInfo.className = "detail_location hide";
            introduceBtn.className = "anchor active";
            comingBtn.className = "anchor";
        });

        comingBtn.addEventListener('click', () => {
            this.detailInfo.className = "detail_area_wrap hide";
            this.locationInfo.className = "detail_location";
            introduceBtn.className = "anchor";
            comingBtn.className = "anchor active";
        });

        reviewMoreBtn.addEventListener('click', () => {
            let displayInfoId = getParameterByName("displayInfoId");
            location.href = "review?" + "displayInfoId=" + displayInfoId;
        });

        reserveBtn.addEventListener('click', () => {
            //pjt5에서 추가할것
        });
    },

    rightClickAnimate(curPage, length, imgHtml) {
        let figureNum = document.querySelector("#figure_num");
        if (curPage == length - 1) {
            setTimeout(() => {
                imgHtml.style.transition = "all 0.3s";
                imgHtml.style.right = 414 * length + "px";
            }, 100);
            setTimeout(() => {
                imgHtml.style.transition = "all 0s";
                imgHtml.style.right = "0px";
            }, 300);
        }
        else {
            setTimeout(() => {
                imgHtml.style.transition = "all 0.3s";
                imgHtml.style.right = (curPage + 1) * 414 + "px";
            }, 100);
        }
        figureNum.innerText = 1 + (curPage + 1) % length;
        figureNum.dataset.num = (curPage + 1) % length;
    },

    leftClickAnimate(curPage, length, imgHtml) {
        let figureNum = document.querySelector("#figure_num");
        if (curPage == 0) {
            setTimeout(() => {
                imgHtml.style.transition = "all 0s";
                imgHtml.style.right = 414 * length + "px";
            }, 0);
            setTimeout(() => {
                imgHtml.style.transition = "all 0.3s";
                imgHtml.style.right = 414 * (length - 1) + "px";
            }, 100);
        }
        else {
            setTimeout(() => {
                imgHtml.style.transition = "all 0.3s";
                imgHtml.style.right = (curPage - 1) * 414 + "px";
            }, 100);
        }
        figureNum.innerText = 1 + ((curPage - 1 + length) % length);
        figureNum.dataset.num = (curPage - 1 + length) % length;

    },

    addEtcImages(imagesObj, productDescription, ultag) {
        if (imagesObj.length <= 0) {
            // 양쪽버튼 지우기
            document.querySelector("#click_nxt").style.display = "none";
            document.querySelector("#click_prev").style.display = "none";
            return;
        }
        let tempInnerHTML = ultag.innerHTML;
        imagesObj[0].productDescription = productDescription;
        let template = document.querySelector("#img_script_template").innerText;
        let bindTemplate = Handlebars.compile(template);
        ultag.innerHTML += bindTemplate(imagesObj[0]);
        ultag.innerHTML += tempInnerHTML;

        // etc이미지 개수 초기
        document.querySelector("#figure_total").innerText = 2;
    }
    
}
