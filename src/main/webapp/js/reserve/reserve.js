let reserveObj={};
document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
});


document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    reserveObj.reserve.showReserveInfo();
});

reserveObj.reserve={
    ticketHtml :document.getElementById("ticket_body"),

    showReserveInfo(){
        const url = "api/reserve?displayInfoId=" + getParameterByName("displayInfoId");
        getData(url, this.getReserveInfo);
    },
    getReserveInfo(jsonObj) {
        reserveObj.reserve.showMainProduct(jsonObj);

    },

    showMainProduct(jsonObj) {

        let displayInfo = jsonObj.displayInfo;
        let productImages = jsonObj.productImages[0];
        let productPrice = jsonObj.productPrice;

        document.getElementById("product_img").setAttribute("src",productImages.saveFileName);
        document.getElementById("place_term").innerHTML = "장소 : " + displayInfo.placeName + "<br> 기간 : " + displayInfo.openingHours;
        document.getElementById("opening_hours").innerText = displayInfo.openingHours;
        document.getElementById("product_price");

        let template = document.getElementById("count_script_template").innerText;
        let bindTemplate = Handlebars.compile(template);
        let data=[]
        
        productPrice.forEach((element) => {
            item={
                priceTypeName : element.priceTypeName,
                price : Math.round(this.discountedPrice(element.price,element.discountRate)),
                discountRate : element.discountRate
            };
            data.push(item);
        });

        this.ticketHtml.innerHTML = data.reduce(function (prev, next) {
            return prev + bindTemplate(next);
        }, "");
    },

    setBtn(){
      
        ticketHtml.addEventListener('click',(evt)=>{

        });
    },

    discountedPrice(price, discount) {
        const disprice = (100 - discount) / 100;
        return price * disprice;
    }
}