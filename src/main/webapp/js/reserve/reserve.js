document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    reserveObj.reserve=new reserve();
    reserveObj.reserveForm=new reserveForm();
    reserveObj.reserve.showReserveInfo();
    reserveObj.reserveForm.setFormClickListener();
    reserveObj.reserve.buttonSetListener();
});

function reserve(){
    this.ticketHtml = document.getElementById("ticket_body");
    this.checkBox = document.getElementById("chk3");
    this.reserveBtn=document.getElementById("bk_btn");
    this.dataObj=null;
}
reserve.prototype.showReserveInfo=function(){
    const url = "api/reserve?displayInfoId=" + getParameterByName("displayInfoId");
    getData(url, this.getReserveInfo);
}
reserve.prototype.getReserveInfo=function(jsonObj) {
    reserveObj.reserve.showMainProduct(jsonObj);
}

reserve.prototype.showMainProduct=function(jsonObj) {

    let displayInfo = jsonObj.displayInfo;
    let productImages = jsonObj.productImages[0];
    let productPrice = jsonObj.productPrice;
    let date = new Date();
    document.getElementById("product_img").setAttribute("src",productImages.saveFileName);
    document.getElementById("place_term").innerHTML = "장소 : " + displayInfo.placeName + "<br> 기간 : " + displayInfo.openingHours;
    document.getElementById("opening_hours").innerText = displayInfo.openingHours;
    document.getElementById("current_date").innerText=date.getCurrentDate()+",총 ";

    let template = document.getElementById("count_script_template").innerText;
    let bindTemplate = Handlebars.compile(template);
    let data = []
    productPrice.forEach((element) => {
        item = {
            priceTypeName : element.priceTypeName,
            price : Math.round(this.discountedPrice(element.price,element.discountRate)),
            discountRate : element.discountRate,
            productPriceId: element.productPriceId
        };
        data.push(item);
    });

    this.ticketHtml.innerHTML = data.reduce(function (prev, next) {
        return prev + bindTemplate(next);
    }, "");
    this.setPlusMinusButtons();
    this.dataObj=jsonObj;
}

reserve.prototype.discountedPrice = function(price, discount) {
    const disprice = (100 - discount) / 100;
    return price * disprice;
}
reserve.prototype.setPlusMinusButtons = function(){
    let plusMinusBtns = document.querySelectorAll(".clearfix");
    plusMinusBtns.forEach((item) => {
        item.addEventListener('click',(evt) => {
            let target = evt.target;
            if(target.nodeName != "A"){
                return;
            }

            let qty = target.closest(".qty");
            let totalPrice = qty.querySelector(".total_price");
            let priceNode = qty.querySelector(".price");
            let priceColorNode = totalPrice.parentNode;
            let input = qty.querySelector("input");
            let ticketNum = parseInt(input.value);
            let price = parseInt(priceNode.dataset.price);
            let totalCount = document.getElementById("totalCount");
            let isPlus = target.title == "더하기";
            const ticketMax = 10;

            if(isPlus){
                if (ticketNum < ticketMax) {//최대갯수 추가
                    ticketNum++;
                    totalCount.innerText = parseInt(totalCount.innerText) + 1;
                }
            } else {
                if(ticketNum > 0){
                    ticketNum--;
                    totalCount.innerText = parseInt(totalCount.innerText)-1;
                    target.nextElementSibling.nextElementSibling.classList.remove("disabled");
                }
            }
            
            if(ticketNum > 0){
                input.classList.remove("disabled");
                priceColorNode.classList.add("on_color");
                target.classList.remove("disabled");
            }
            else{
                input.classList.add("disabled");
                priceColorNode.classList.remove("on_color");
                target.classList.add("disabled");
            }

            if(isPlus && ticketNum >= ticketMax){
                target.classList.add("disabled");
            }


            totalPrice.innerText = ticketNum*price;
            input.value = ticketNum;
        });
    });

}

reserve.prototype.buttonSetListener = function(){
   
    this.checkBox.addEventListener('click',() => {
        let colorBtn = document.getElementById("bk_btn_parent");
        if(this.checkBox.checked){
            colorBtn.classList.remove("disable");
        } else{
            colorBtn.classList.add("disable");
        }
    });

    this.reserveBtn.addEventListener('click',() => {
        //checkbox선택
        if(this.checkBox.checked == false){
            return;
        }
        let nameInput = document.getElementById("name");
        let telInput = document.getElementById("tel");
        let emailInput = document.getElementById("email");
        let totalCount = document.getElementById("totalCount");
        let flag = true;
        
        //이름 안적음
        if(nameInput.dataset.flag != "1"){
            let msg = nameInput.parentNode.querySelector(".warning_msg");
            msg.style.visibility="visible";
            flag = false;   
        }
        //전화번호 형식 안맞거나
        if(telInput.dataset.flag != "1"){
            let msg = telInput.parentNode.querySelector(".warning_msg");
            msg.style.visibility = "visible";
            flag = false;
        }
        if(emailInput.dataset.flag != "1"){
            let msg = emailInput.parentNode.querySelector(".warning_msg");
            msg.style.visibility = "visible";
            flag = false;
        }
        //상품 한개도 선택안하거나
        if(totalCount.innerText == "0"){
            alert("1개이상 예매를 해주셔야합니다.");
            flag = false;
        }
        if(flag == false){
            return;
        }

        let qtys = document.querySelectorAll(".qty");
        let prices = [];

        qtys.forEach((item) => {
            let cnt=parseInt(item.querySelector("input").value);
            if(cnt > 0){
                let p = {
                    productPriceId:item.dataset.productpriceid,
                    reservationInfoId:this.dataObj.displayInfo.productId,
                    count:cnt
                }
                prices.push(p);
            }
        });

        let data = {
            productId : this.dataObj.displayInfo.productId,
            displayInfoId : getParameterByName("displayInfoId"),
            reservationInfoPrices : prices,
            reservationName : nameInput.value,
            reservationTel : telInput.value,
            reservationEmail : emailInput.value
        }
        postData("api/reserve/",data);
    });
}


function reserveForm(){

};
reserveForm.prototype.setFormClickListener=function(){
    let msg = document.querySelectorAll(".warning_msg");
    msg.forEach((item) => {
        item.addEventListener('click',(evt) => {
            evt.currentTarget.style.visibility = "hidden";
        })
    });
    this.agreementForm();
}

reserveForm.prototype.nameForm = function(evt){
    let target = evt.currentTarget;
    const value= target.value;
    const result = value.match(/^[가-힣]+$|^[a-zA-Z]+$/); //한글이면 한글만 영어면 영어만 가능 다른문자 불가
    let msg = target.parentNode.querySelector(".warning_msg");
    if(result){
        msg.style.visibility = "hidden";
        target.dataset.flag = 1;//형식에 맞음
    } else {
        msg.style.visibility = "visible";
        target.dataset.flag = 0;//형식이 안맞음
    }
}
reserveForm.prototype.telForm = function(evt){
    let target = evt.currentTarget;
    const value = target.value;
    const result = value.match(/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/); //00~999-000~9999-0000~9999 핸드폰,일반전화
    let msg = target.parentNode.querySelector(".warning_msg");
    if(result){
        msg.style.visibility = "hidden";
        target.dataset.flag = 1;

    } else {
        msg.style.visibility = "visible";
        target.dataset.flag = 0;
    }

}

reserveForm.prototype.emailForm = function(evt){
    let target = evt.currentTarget;
    const value = target.value;
    const result = value.match(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    let msg = target.parentNode.querySelector(".warning_msg");
    if(result){
        msg.style.visibility = "hidden";
        target.dataset.flag = 1;
    } else {
        msg.style.visibility = "visible";
        target.dataset.flag = 0;
    }

}

reserveForm.prototype.agreementForm = function(){
    let watchAgreeBtn1 = document.getElementById("watch_agree1");
    let watchAgreeBtn2 = document.getElementById("watch_agree2");
    
    watchAgreeBtn1.addEventListener('click',this.clickAgreement);
    watchAgreeBtn2.addEventListener('click',this.clickAgreement);
}
reserveForm.prototype.clickAgreement = function(evt){
    let text = evt.currentTarget.querySelector("span");
    let openClose = evt.currentTarget.closest("div");
    if (text.innerText == "보기") {
        openClose.classList.add("open");
        text.innerText = "접기";
    } else {
        openClose.classList.remove("open");
        text.innerText = "보기";
    }
}


Date.prototype.getCurrentDate = function(){
    var yyyy = this.getFullYear().toString();
    var mm = (this.getMonth() + 1).toString();
    var dd = this.getDate().toString();
    return yyyy +"-"+(mm[1] ? mm : '0'+mm[0])+"-"+(dd[1] ? dd : '0'+dd[0]);
};
