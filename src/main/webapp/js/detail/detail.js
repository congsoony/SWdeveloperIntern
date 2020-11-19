document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    showProductIamges();
});

function showProductIamges(){
    var displayInfoId = getParameterByName("displayInfoId");
    var url = "api/productimages?displayInfoId="+displayInfoId;
    getData(url, getProductImages);
    url = "api/displayinfo?displayInfoId="+displayInfoId;
    getData(url,getDisplayInfo);
}

function getProductImages(jsonObj){
    
	var imagesData=jsonObj.productImages;
    var template = document.querySelector("#img_script_template").innerText;
    var ultag = document.querySelector("#product_img");
    var bindTemplate = Handlebars.compile(template); //bindTemplate은 메서드 즉 함수임 handlebars.compile이 반환하는게 함수라서
    ultag.innerHTML= imagesData.reduce(function(prev,next){
       return prev+bindTemplate(next);
   },"");
}

function getDisplayInfo(jsonObj){
	
    var imagesData = jsonObj.displayInfo;
    var productContent= document.querySelector("#content_summary_txt");
    var bindTemplate = Handlebars.compile(productContent.innerHTML);
    productContent.innerHTML= bindTemplate(imagesData);
    debugger;
}