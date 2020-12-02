let reserveObj={};

document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    reserveObj.reserve.showReserveInfo();
    reserveObj.reserveForm.setFormClickListener();
    reserveObj.reserve.buttonSetListener();
});

reserveObj.reserve={
    ticketHtml :document.getElementById("ticket_body"),
    checkBox : document.getElementById("chk3"),
    reserveBtn:document.getElementById("bk_btn"),
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
        this.setPlusMinusButtons();
    },

    discountedPrice(price, discount) {
        const disprice = (100 - discount) / 100;
        return price * disprice;
    },
    setPlusMinusButtons(){
        let plusMinusBtns=document.querySelectorAll(".clearfix");
        plusMinusBtns.forEach((item)=>{
            item.addEventListener('click',(evt)=>{
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
        });

    },

    buttonSetListener(){
       
        this.checkBox.addEventListener('click',()=>{
            let colorBtn=document.getElementById("bk_btn_parent");
            if(this.checkBox.checked){
                colorBtn.className="bk_btn_wrap";
            } else{
                colorBtn.className="bk_btn_wrap disable"
            }
        });

        this.reserveBtn.addEventListener('click',()=>{
            //checkbox선택
            if(this.checkBox.checked==false){
                return;
            }
            let nameInput=document.getElementById("name");
            let telInput=document.getElementById("tel");
            let emailInput=document.getElementById("email");
            let totalCount=document.getElementById("totalCount");
            let flag=true;
            
            //이름 안적음
            if(nameInput.dataset.flag!="1"){
                let msg = nameInput.parentNode.querySelector(".warning_msg");
                msg.style.visibility="visible";
                flag=false;   
            }
            //전화번호 형식 안맞거나
            if(telInput.dataset.flag!="1"){
                let msg = telInput.parentNode.querySelector(".warning_msg");
                msg.style.visibility="visible";
                flag=false;
            }
            if(emailInput.dataset.flag!="1"){
                let msg = emailInput.parentNode.querySelector(".warning_msg");
                msg.style.visibility="visible";
                flag=false;
            }
            //상품 한개도 선택안하거나
            if(totalCount.innerText=="0"){
                alert("1개이상 예매를 해주셔야합니다.");
                flag=false;
            }
            if(flag==false){
                return;
            }
            //document.getElementById("reserve_form").submit();
            let data = {
                displayInfoId : getParameterByName("displayInfoId"),
                reservationInfoPrices : data.productPrices,
                reservationName : nameInput.value,
                reservationTel : telInput.value,
                reservationEmail : emailInput.value
            }
            
        });
    },
    
}

reserveObj.reserveForm={
    setFormClickListener(){
        let msg=document.querySelectorAll(".warning_msg");
        msg.forEach((item)=>{
            item.addEventListener('mouseover',(evt)=>{
                evt.currentTarget.style.visibility="hidden";
            })
        });
        this.agreementForm();
    },

    nameForm(evt){
        let target = evt.currentTarget;
        const value= target.value;
        const result = value.match(/^[가-힣]+$|^[a-zA-Z]+$/); //한글이면 한글만 영어면 영어만 가능 다른문자 불가
        let msg = target.parentNode.querySelector(".warning_msg");
        if(res){
            msg.style.visibility="hidden";
            target.dataset.flag=1;//형식에 맞음
        } else {
            msg.style.visibility="visible";
            target.dataset.flag=0;//형식이 안맞음
        }
    },
    telForm(evt){
        let target = evt.currentTarget;
        const value= target.value;
        const result = value.match(/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/); //00~999-000~9999-0000~9999 핸드폰,일반전화
        let msg = target.parentNode.querySelector(".warning_msg");
        if(result){
            msg.style.visibility="hidden";
            target.dataset.flag=1;

        } else {
            msg.style.visibility="visible";
            target.dataset.flag=0;
        }
    
    },
    
    emailForm(evt){
        let target = evt.currentTarget;
        const value= target.value;
        const result = value.match(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
        let msg = target.parentNode.querySelector(".warning_msg");
        if(result){
            msg.style.visibility="hidden";
            target.dataset.flag=1;
        } else {
            msg.style.visibility="visible";
            target.dataset.flag=0;
        }
    
    },
    
    agreementForm(){
        let watchAgreeBtn1=document.getElementById("watch_agree1");
        let watchAgreeBtn2=document.getElementById("watch_agree2");
        
        watchAgreeBtn1.addEventListener('click',this.clickAgreement);
        watchAgreeBtn2.addEventListener('click',this.clickAgreement);
    },
    clickAgreement(evt){
        let text = evt.currentTarget.querySelector("span");
        let openClose = evt.currentTarget.closest("div");
        if (text.innerText == "보기") {
            openClose.className = "agreement open";
            text.innerText = "접기";
        } else {
            openClose.className = "agreement";
            text.innerText = "보기";
        }
    },
    
};

