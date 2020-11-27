let reserveObj={};
document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
});


document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    reserveObj.reserve.buttonSetListener();
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

    discountedPrice(price, discount) {
        const disprice = (100 - discount) / 100;
        return price * disprice;
    },

    buttonSetListener(){
        this.ticketHtml.addEventListener('click',(evt)=>{
            let target=evt.target;
            if(target.nodeName!="A"){
                return;
            }
            
            let qty=target.closest(".qty");
            let nodeMinusPluse=qty.querySelectorAll("A");
            let totalPrice=qty.querySelector(".total_price");
            let priceNode=qty.querySelector(".price");
            let priceColorNode=totalPrice.parentNode;
            let input=qty.querySelector("input");
            let num=parseInt(input.value);
            let price=parseInt(priceNode.dataset.price);
            let totalCount=document.getElementById("totalCount");
            if(target.title=="더하기"){
            	num++;
            	totalCount.innerText=parseInt(totalCount.innerText)+1;
            } else {
            	if(num>0){
            		num--;
            		totalCount.innerText=parseInt(totalCount.innerText)-1;
            	}
            }
            if(num>0){
            	input.className="count_control_input";
            	priceColorNode.className="individual_price on_color";
            	nodeMinusPluse[0].className="btn_plus_minus spr_book2 ico_minus3";
            }
            else{
            	input.className="count_control_input disabled";
            	priceColorNode.className="individual_price";
            	nodeMinusPluse[0].className="btn_plus_minus spr_book2 ico_minus3 disabled";
            }
            totalPrice.innerText=num*price;
            input.value=num;
        });
    }
}