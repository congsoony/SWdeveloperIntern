
detailObj.display = {
    introduceContent: document.querySelector("#introduce_content"),
    comingHtml : document.querySelector("#detail_location"),

    showProductIamges() {
        var displayInfoId = getParameterByName("displayInfoId");
        var url = "api/displayinfo?displayInfoId=" + displayInfoId;
        getData(url, this.getDisplayInfo);
    },
    getDisplayInfo(jsonObj) {
        // 상품 내용보여주기
        var displayInfoObj = jsonObj.displayInfo;
        var productContent = document.querySelector("#content_summary_txt");
        var bindTemplate = Handlebars.compile(productContent.innerHTML);

        productContent.innerHTML = bindTemplate(displayInfoObj);
        
        
        // 선택한 상품 이미지 & 제목
        var imagesObj = jsonObj.mainImages;
        imagesObj.forEach((item) => {
            item.productDescription = displayInfoObj.productDescription;
        });
        var template = document.querySelector("#img_script_template").innerText;
        var ultag = document.querySelector("#product_img");
        bindTemplate = Handlebars.compile(template); // bindTemplate은 메서드 즉 함수임
        // handlebars.compile이 반환하는게
        // 함수라서
        ultag.innerHTML = imagesObj.reduce(function (prev, next) {
            return prev + bindTemplate(next);
        }, "");
        
        detailObj.addObj.addEtcImages(jsonObj.etcImages,displayInfoObj.productDescription,ultag);
        detailObj.comment.showComments(jsonObj);
        detailObj.display.setIntroduce(jsonObj.displayInfo.productContent);
        detailObj.display.setComing(jsonObj.displayInfo,jsonObj.displayInfoImage);
        
    },
    setComing(displayinfo,displayInfoImage){
        var data={
            productDescription:displayinfo.productDescription,
            placeStreet : displayinfo.placeStreet,
            placeLot : displayinfo.placeLot,
            placeName : displayinfo.placeName,
            saveFileName : displayInfoImage.saveFileName,
            telephone : displayinfo.telephone
        };

        var bindTemplate = Handlebars.compile(document.querySelector("#coming_script_template").innerText);
        this.comingHtml.innerHTML = bindTemplate(data);
    },
  
    setIntroduce(productContent){
    	this.introduceContent.innerText = productContent;
    }
}

detailObj.addObj = {
    addEtcImages(imagesObj, productDescription, ultag) {
        if (imagesObj.length <= 0) {
            // 양쪽버튼 지우기
            document.querySelector("#click_nxt").style.display = "none";
            document.querySelector("#click_prev").style.display = "none";
            return;
        }
        var tempInnerHTML = ultag.innerHTML;
        imagesObj[0].productDescription = productDescription;
        var template = document.querySelector("#img_script_template").innerText;
        var bindTemplate = Handlebars.compile(template);
        ultag.innerHTML += bindTemplate(imagesObj[0]);
        ultag.innerHTML += tempInnerHTML;

        // etc이미지 개수 초기
        document.querySelector("#figure_total").innerText = 2;
    }
}