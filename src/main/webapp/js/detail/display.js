
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
        detailObj.display.review(jsonObj.comments,jsonObj.averageScore,jsonObj.totalCount,jsonObj.displayInfo);
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
    review(comments,averageScore,totalCount,product){
        document.querySelector("#average_score").innerText=averageScore;
        document.querySelector("#graph_value").style.width=(averageScore/5)*100+"%";
        document.querySelector("#total_count").innerText=totalCount+"건";
        var html =document.querySelector("#list_short_review");
        var data=[];
        
        //동적데이터 obj만들기
        comments.forEach((item)=>{
            obj={
                comment:item.comment,
                commentImages:item.commentImages,
                date:item.reservationDate,
                score:item.score.toFixed(1),
                productDescription:product.productDescription,
                reservationEmail:makeEmailSecurity(item.reservationEmail)
            }
            if(item.commentImages.length>0){
                obj.saveFileName=[];
                item.commentImages.forEach((image)=>{
                    obj.saveFileName.push(image.saveFileName);
                });
            }
            data.push(obj);
        });

        //template 처리
        Handlebars.registerHelper('idx', function (index) {
            return parseInt(index)+1;
        });
        var template = document.querySelector("#comment_script_template").innerText;
        var bindTemplate = Handlebars.compile(template);
        var resultHTML=data.reduce((prev,next)=>{
            return prev+bindTemplate(next);
        },"");
        html.innerHTML=resultHTML;
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